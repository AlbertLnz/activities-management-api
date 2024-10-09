package com.albertlnz.activitiesManagementApi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityDTO {
  private Long id;
  private String name;
  private String description;
  private int maxCapacity;

  public ActivityDTO(Long id, String name, String description, int maxCapacity) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.maxCapacity = maxCapacity;
  }
}
