package com.albertlnz.activitiesManagementApi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.albertlnz.activitiesManagementApi.models.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {

}
