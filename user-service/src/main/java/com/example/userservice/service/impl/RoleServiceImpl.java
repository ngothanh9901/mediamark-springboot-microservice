package com.example.userservice.service.impl;

import com.example.userservice.model.Role;
import com.example.userservice.repository.RoleRepository;
import com.example.userservice.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
  private final RoleRepository roleRepository;

  @Override
  public Role save(Role role) {
    return roleRepository.save(role);
  }
}
