package com.albertlnz.activitiesManagementApi.services;

import java.util.ArrayList;

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

  public UserModel updateOneUser(UserModel user) {
    return this.userRepository.save(user);
  }

  public void deleteOneUser() {

  }

}
