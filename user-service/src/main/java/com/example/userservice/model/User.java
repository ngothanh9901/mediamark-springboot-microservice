package com.example.userservice.model;

import com.example.userservice.common.model.UserDateAudit;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends UserDateAudit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message ="Please enter  proper name")
  @Size(min=2,max=200,message ="The name should be between 2 and 200 characters")
  private String name;

  @NotBlank(message ="Please enter  proper email")
  @Size(min=2,max=200,message ="The name should be between 2 and 200 characters")
  @Email(message ="Please enter the correct email format")
  private String email;

  @NotBlank(message ="Please enter  proper username")
  @Size(min=5,max=200,message ="The password should be between 5 and 200 characters")
  private String username;

  @NotBlank(message ="Please enter  proper password")
  @Size(min=5,max=200,message ="The password should be between 5 and 200 characters")
  private String password;

  @Column(name = "login_times")
  private Integer loginTimes = 0;

  private int loginFailedTimes = 0;

  @ManyToMany(fetch = FetchType.EAGER)
  private Collection<Role> roles = new ArrayList<>();
}
