package com.albertlnz.activitiesManagementApi.models;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "activities")
@Getter
@Setter
public class ActivityModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;
  private int maxCapacity;

  @ManyToMany(mappedBy = "activities")
  private Set<UserModel> users = new HashSet<>();
}
