package com.example.userservice.model;

import com.example.userservice.common.model.DateAudit;
import com.example.userservice.common.model.UserDateAudit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends UserDateAudit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank(message = "Please enter  proper code")

  @Size(min = 2, max = 200, message = "The code should be between 2 and 50 characters")
  @Column(unique = true)
  private String code;

  @NotBlank(message = "Please enter  proper name")
  @Size(min = 2, max = 200, message = "The code should be between 2 and 50 characters")
  @Column(unique = true)
  private String name;
}
