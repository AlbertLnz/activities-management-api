package com.albertlnz.activitiesManagementApi.repositories;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.albertlnz.activitiesManagementApi.models.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {

  // JPA Query Method
  public Optional<UserModel> findByEmail(String email);

  // JPA Query Method
  public Optional<UserModel> findByNameAndSurname(String name, String surname);

}
