package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.model.BuldingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.service.BuildingService;


@Service
public class BuildingServiceImpl implements BuildingService{

	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private BuildingDTOConverter buildingDTOConverter;

	@Override
	public List<BuldingDTO> finaAllBuilding(Map<String, Object> params, List<String> typeCode) {
		
		List<BuildingEntity> buildingEntities = buildingRepository.finaAllBuilding(params, typeCode);
		List<BuldingDTO> result = new ArrayList<BuldingDTO>();
		for(BuildingEntity item : buildingEntities) {
			BuldingDTO newBuilding = buildingDTOConverter.toBuildingDTO(item);
			
			result.add(newBuilding);
		}
		
		
		return result;
	}

}
