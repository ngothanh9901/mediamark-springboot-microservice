package com.example.userservice.controller;

import com.example.userservice.model.Role;
import com.example.userservice.service.RoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Setter
@AllArgsConstructor
@RestController
@RequestMapping("/api/role")
public class RoleController {
  private final RoleService roleService;

  @PostMapping("/")
  public ResponseEntity<?> saveRole(@Valid @RequestBody Role role) {
    Role result = roleService.save(role);
    return ResponseEntity.ok(result);
  }
}
