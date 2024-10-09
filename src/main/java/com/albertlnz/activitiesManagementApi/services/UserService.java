package com.albertlnz.activitiesManagementApi.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albertlnz.activitiesManagementApi.models.UserModel;
import com.albertlnz.activitiesManagementApi.repositories.IUserRepository;

@Service
public class UserService {

  @Autowired
  private IUserRepository userRepository;

  public ArrayList<UserModel> getAllUsers() {
    return (ArrayList<UserModel>) userRepository.findAll();
  }

  public UserModel createOneUser(UserModel user) {
    return this.userRepository.save(user);
  }

  public Optional<UserModel> updateUserByUUID(UUID id, UserModel userRequest) {
    Optional<UserModel> user = this.userRepository.findById(id);

    if (user.isPresent()) {
      userRequest.setId(user.get().getId());
      this.userRepository.save(userRequest);
      return Optional.of(userRequest);
    } else {
      return Optional.empty();
    }
  }

  public String deleteOneUserByEmail(String email) {
    Optional<UserModel> user = this.userRepository.findByEmail(email);

    if (user.isPresent()) {
      UUID userId = user.get().getId();
      this.userRepository.deleteById(userId);
      return "User with id: " + userId + ", has been deleted successfully!";
    } else {
      throw new RuntimeException("User with email " + email + " not found.");
    }
  }

}
