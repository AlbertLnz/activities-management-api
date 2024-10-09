package com.albertlnz.activitiesManagementApi.controllers;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albertlnz.activitiesManagementApi.dto.ActivityDTO;
import com.albertlnz.activitiesManagementApi.models.ActivityModel;
import com.albertlnz.activitiesManagementApi.services.ActivityService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

  @Autowired
  private ActivityService activityService;

  @GetMapping
  public ArrayList<ActivityModel> getAllActivities() {
    return this.activityService.getAllActivities();
  }

  @GetMapping("/{name}/{surname}")
  public Set<ActivityDTO> getMethodName(@PathVariable String name, @PathVariable String surname) {
    return this.activityService.getActivitiesByUserNameAndSurname(name, surname);
  }

  @PostMapping
  public ActivityModel createNewActivity(@RequestBody ActivityModel activity) {
    return this.activityService.createOneActivity(activity);
  }

  @PutMapping("/{id}")
  public Optional<ActivityModel> updateActivityById(@PathVariable Long id, @RequestBody ActivityModel activity) {
    return this.activityService.updateActivityById(id, activity);
  }

  @DeleteMapping("/{id}")
  public String deleteUserById(@PathVariable Long id) {
    return this.activityService.deleteOneActivityById(id);
  }

  @PostMapping("/upload-json")
  public ResponseEntity<String> uploadUsers() {
    try {
      activityService.loadActivitiesFromJsonFile();
      return ResponseEntity.ok("Actividades cargadas exitosamente desde el archivo JSON.");
    } catch (IOException e) {
      return ResponseEntity.status(500).body("Error al cargar las actividades: " + e.getMessage());
    }
  }

}
