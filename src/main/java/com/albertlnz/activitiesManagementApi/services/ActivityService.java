package com.albertlnz.activitiesManagementApi.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albertlnz.activitiesManagementApi.models.ActivityModel;
import com.albertlnz.activitiesManagementApi.repositories.IActivityRepository;

@Service
public class ActivityService {

  @Autowired
  private IActivityRepository activityRepository;

  public ArrayList<ActivityModel> getAllActivities() {
    return (ArrayList<ActivityModel>) activityRepository.findAll();
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

}
