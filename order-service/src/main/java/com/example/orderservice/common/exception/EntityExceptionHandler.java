package com.example.orderservice.common.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {
  private static final Log LOG = LogFactory.getLog(EntityExceptionHandler.class);

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    LOG.error(ex.getMessage(), ex);

    Map<String, Object> objectBody = new LinkedHashMap<>();
    objectBody.put("Current Timestamp", new Date());
    objectBody.put("Status", status.value());

    // Get all errors
    List<String> exceptionalErrors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(x -> x.getDefaultMessage())
        .collect(Collectors.toList());

    objectBody.put("Errors", exceptionalErrors);

    return new ResponseEntity<>(objectBody, status);
  }


}
