package com.albertlnz.activitiesManagementApi.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.List;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.albertlnz.activitiesManagementApi.models.ActivityModel;
import com.albertlnz.activitiesManagementApi.models.UserModel;
import com.albertlnz.activitiesManagementApi.repositories.IActivityRepository;
import com.albertlnz.activitiesManagementApi.repositories.IUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private IActivityRepository activityRepository;

  public ArrayList<UserModel> getAllUsers() {
    return (ArrayList<UserModel>) userRepository.findAll();
  }

  public Optional<UserModel> getUserById(UUID id) {
    return this.userRepository.findById(id);
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

  public String beUpToAnActivityByEmail(String email, Long activityId) {
    Optional<UserModel> userOptional = this.userRepository.findByEmail(email);
    if (!userOptional.isPresent()) {
      return "User not found";
    }
    UserModel user = userOptional.get();

    Optional<ActivityModel> activityOptional = this.activityRepository.findById(activityId);
    if (!activityOptional.isPresent()) {
      return "Activity not found";
    }
    ActivityModel activity = activityOptional.get();

    if (!user.getActivities().contains(activity)) {
      user.getActivities().add(activity);
    } else {
      return "User is already enrolled in this activity";
    }

    this.userRepository.save(user);
    return "User has been successfully enrolled in the activity";
  }

  public void loadUsersFromJsonFile() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    List<UserModel> users = objectMapper.readValue(
        new ClassPathResource("data/users.json").getFile(),
        objectMapper.getTypeFactory().constructCollectionType(List.class, UserModel.class));

    userRepository.saveAll(users);
  }

}
