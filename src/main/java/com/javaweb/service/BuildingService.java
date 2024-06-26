package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.model.BuldingDTO;

public interface BuildingService {
	List<BuldingDTO> finaAllBuilding(Map<String, Object> params, List<String> typeCode);
}
