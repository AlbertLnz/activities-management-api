package com.albertlnz.activitiesManagementApi.models;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserModel {

  // This works fine only with Non-SQL like MongoDB:
  // @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  // private UUID id;

  // For SQL DB like MariaDB or MySQL:
  @Id
  @GeneratedValue
  @UuidGenerator
  private UUID id;

  private String name;
  private String surname;
  private int age;

  @Column(nullable = false, unique = true)
  private String email;

  @OneToMany(cascade = CascadeType.ALL)
  private List<ActivityModel> activities = new ArrayList<>();

}
