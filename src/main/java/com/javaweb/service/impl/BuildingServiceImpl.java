package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuldingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;


@Service
public class BuildingServiceImpl implements BuildingService{

	@Autowired
	private BuildingRepository buildingRepository;
	@Override
	public List<BuldingDTO> finaAllBuilding(String name, Long districtId) {
		
		List<BuildingEntity> buildingEntities = buildingRepository.finaAllBuilding(name,districtId);
		List<BuldingDTO> result = new ArrayList<BuldingDTO>();
		for(BuildingEntity item : buildingEntities) {
			BuldingDTO newBuilding = new BuldingDTO();
			newBuilding.setName(item.getName());
			newBuilding.setAddress(item.getStreet() + "," + item.getWard());
			
			newBuilding.setNumberOfBasement(item.getNumberOfBasementInteger());
			
			result.add(newBuilding);
		}
		
		
		return result;
	}

}
