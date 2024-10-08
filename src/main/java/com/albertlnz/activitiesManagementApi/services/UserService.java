package com.albertlnz.activitiesManagementApi.services;

import java.util.ArrayList;
import java.util.Optional;

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

  public Optional<UserModel> updateOneUserByNameAndSurname(String name, String surname, UserModel userRequest) {
    Optional<UserModel> user = this.userRepository.findByNameAndSurname(name, surname);

    if (user.isPresent()) {
      this.userRepository.save(userRequest);
      return Optional.of(userRequest);
    } else {
      return Optional.empty();
    }
  }

  public void deleteOneUserByEmail(String email) {
    Optional<UserModel> user = this.userRepository.findByEmail(email);

    if (user.isPresent()) {
      this.userRepository.deleteById(user.get().getId());
    } else {
      throw new RuntimeException("User with email " + email + " not found.");
    }
  }

}
