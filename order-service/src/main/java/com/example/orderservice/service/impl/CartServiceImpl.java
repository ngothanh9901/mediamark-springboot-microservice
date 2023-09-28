package com.example.orderservice.service.impl;

import com.example.orderservice.dto.ProductDto;
import com.example.orderservice.dto.ProductItemCartDto;
import com.example.orderservice.dto.response.CartResponse;
import com.example.orderservice.dto.resquest.AddToCartRequest;
import com.example.orderservice.dto.resquest.GetProductByIdsRequest;
import com.example.orderservice.feign.ProductClient;
import com.example.orderservice.model.OrderDetailItem;
import com.example.orderservice.model.Orders;
import com.example.orderservice.repository.OrderDetailItemRepository;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
  private final OrderRepository orderRepository;
  private final OrderDetailItemRepository orderDetailItemRepository;
  private final ProductClient productClient;

  public CartServiceImpl(OrderRepository orderRepository, OrderDetailItemRepository orderDetailItemRepository, ProductClient productClient) {
    this.orderRepository = orderRepository;
    this.orderDetailItemRepository = orderDetailItemRepository;
    this.productClient = productClient;
  }

  @Transactional
  @Override
  public CartResponse addToCart(AddToCartRequest request) {
//    get cart or create cart
    Orders cart = orderRepository.existsByStatusAndUserId(false, request.getUserId()) ?
        orderRepository.findByStatusAndUserId(false, request.getUserId()) : orderRepository.save(new Orders(request.getUserId()));

//    get detail item or create
    boolean checkProductInCart = orderDetailItemRepository.existsByProductIdAndOrders(request.getProductId(), cart);
    OrderDetailItem orderDetail = checkProductInCart ?
        orderDetailItemRepository.findByProductIdAndOrders(request.getProductId(), cart) : new OrderDetailItem(request.getProductId(), cart, 0L);

    orderDetail.setQuantity(orderDetail.getQuantity() + 1);

//    save into database
    orderDetailItemRepository.save(orderDetail);
    cart.getOrderDetailList().add(orderDetail);

    return null;
  }

  private CartResponse cart(Orders order) {
//    get list product id
    List<OrderDetailItem> orderDetailItems = order.getOrderDetailList();
    List<Long> productIds = orderDetailItems.stream().map(OrderDetailItem::getProductId).collect(Collectors.toList());

//    get product by ids through product-service ( feign)
    GetProductByIdsRequest getProductByIdsRequest = new GetProductByIdsRequest(productIds);
    List<ProductDto> products = productClient.getProductByIds(getProductByIdsRequest);


    List<ProductItemCartDto> productItemCarts = products.stream().map(x -> {
      ProductItemCartDto productItemCartDto = new ProductItemCartDto(x);
      productItemCartDto.setProductCartId(order.getId());

      OrderDetailItem productItem = orderDetailItems.stream().filter(y->y.getProductId().equals(x.getId())).findFirst().get();
      productItemCartDto.setQuantity(productItem.getQuantity());

      return productItemCartDto;
    }).collect(Collectors.toList());

    Long sum = productItemCarts.stream().mapToLong(p->{return p.getQuantity()*p.getPrice();}).sum();

    return new CartResponse(productItemCarts,sum,order.getId());
  }
}
