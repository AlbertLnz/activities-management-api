package com.albertlnz.activitiesManagementApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.albertlnz.activitiesManagementApi.models.ActivityModel;

public interface IActivityRepository extends JpaRepository<ActivityModel, Long> {

}
