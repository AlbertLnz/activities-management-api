package com.albertlnz.activitiesManagementApi.controllers;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.albertlnz.activitiesManagementApi.models.UserModel;
import com.albertlnz.activitiesManagementApi.services.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ArrayList<UserModel> getAllUsers() {
    return this.userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public Optional<UserModel> getUserById(@PathVariable UUID id) {
    return this.userService.getUserById(id);
  }

  @PostMapping
  public UserModel createNewUser(@RequestBody UserModel user) {
    return this.userService.createOneUser(user);
  }

  @PutMapping("/{id}")
  public Optional<UserModel> updateUserByUUID(@PathVariable UUID id, @RequestBody UserModel user) {
    return this.userService.updateUserByUUID(id, user);
  }

  @DeleteMapping
  public String deleteUserByEmail(@RequestParam String email) {
    return this.userService.deleteOneUserByEmail(email);
  }

  @PostMapping("/{email}/activities/{activityId}")
  public String postMethodName(@PathVariable String email, @PathVariable Long activityId) {
    return this.userService.beUpToAnActivityByEmail(email, activityId);
  }

  @PostMapping("/upload-json")
  public ResponseEntity<String> uploadUsers() {
    try {
      userService.loadUsersFromJsonFile();
      return ResponseEntity.ok("Usuarios cargados exitosamente desde el archivo JSON.");
    } catch (IOException e) {
      return ResponseEntity.status(500).body("Error al cargar los usuarios: " + e.getMessage());
    }
  }

}
