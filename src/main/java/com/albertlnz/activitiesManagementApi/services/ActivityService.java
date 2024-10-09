package com.albertlnz.activitiesManagementApi.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.albertlnz.activitiesManagementApi.dto.ActivityDTO;
import com.albertlnz.activitiesManagementApi.models.ActivityModel;
import com.albertlnz.activitiesManagementApi.models.UserModel;
import com.albertlnz.activitiesManagementApi.repositories.IActivityRepository;
import com.albertlnz.activitiesManagementApi.repositories.IUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ActivityService {

  @Autowired
  private IActivityRepository activityRepository;

  @Autowired
  private IUserRepository userRepository;

  public ArrayList<ActivityModel> getAllActivities() {
    return (ArrayList<ActivityModel>) activityRepository.findAll();
  }

  public Set<ActivityDTO> getActivitiesByUserNameAndSurname(String name, String surname) {
    Optional<UserModel> userOptional = this.userRepository.findByNameAndSurname(name, surname);

    if (userOptional.isPresent()) {
      UserModel user = userOptional.get();
      return user.getActivities().stream()
          .map(activity -> new ActivityDTO(activity.getId(), activity.getName(), activity.getDescription(),
              activity.getMaxCapacity()))
          .collect(Collectors.toSet());
    } else {
      throw new RuntimeException("User not found");
    }
  }

  public ActivityModel createOneActivity(ActivityModel activity) {
    return this.activityRepository.save(activity);
  }

  public Optional<ActivityModel> updateActivityById(Long id, ActivityModel activtyRequest) {
    Optional<ActivityModel> activity = this.activityRepository.findById(id);

    if (activity.isPresent()) {
      activtyRequest.setId(activity.get().getId());
      this.activityRepository.save(activtyRequest);
      return Optional.of(activtyRequest);
    } else {
      return Optional.empty();
    }
  }

  public String deleteOneActivityById(Long id) {
    Optional<ActivityModel> activity = this.activityRepository.findById(id);

    if (activity.isPresent()) {
      this.activityRepository.deleteById(id);
      return "Activity with id: " + id + ", has been deleted successfully!";
    } else {
      throw new RuntimeException("Activity with id " + id + " not found.");
    }
  }

  public void loadActivitiesFromJsonFile() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    List<ActivityModel> activities = objectMapper.readValue(
        new ClassPathResource("data/activities.json").getFile(),
        objectMapper.getTypeFactory().constructCollectionType(List.class, ActivityModel.class));

    activityRepository.saveAll(activities);
  }

}
