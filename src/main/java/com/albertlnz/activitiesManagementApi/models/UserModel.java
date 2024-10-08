package com.albertlnz.activitiesManagementApi.models;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  private String name;
  private String surname;
  private int age;
  private int email;

  @OneToMany(cascade = CascadeType.ALL)
  private List<ActivityModel> activities = new ArrayList<>();

}
