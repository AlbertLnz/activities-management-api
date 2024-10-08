package com.albertlnz.activitiesManagementApi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.albertlnz.activitiesManagementApi.models.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {

  // JPA Query Method
  public UserModel findByNameAndSurname(String name, String surname);

  // JPA Query Method
  public UserModel findByEmail(String email);

}
