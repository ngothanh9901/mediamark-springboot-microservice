package com.example.orderservice.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class UserDateAudit extends DateAudit {
  @CreatedBy
  @JsonIgnore
  private Long createdBy;

  @LastModifiedBy
  @JsonIgnore
  private Long updatedBy;

  private boolean deleted;
}
