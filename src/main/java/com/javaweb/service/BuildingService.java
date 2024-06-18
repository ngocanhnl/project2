package com.javaweb.service;

import java.util.List;

import com.javaweb.model.BuldingDTO;
import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingService {
	List<BuldingDTO> finaAllBuilding(String name, Long districtId);
}
