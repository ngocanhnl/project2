package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuldingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {

	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	public BuldingDTO toBuildingDTO(BuildingEntity item) {
		BuldingDTO newBuilding = new BuldingDTO();
		newBuilding.setName(item.getName());
		
		DistrictEntity districtEntity = districtRepository.findNameById(item.getDistrictid());
		
		
		newBuilding.setAddress(item.getStreet() + "," + item.getWard() + ", " + districtEntity.getName());
		
		List<RentAreaEntity> rentareas = rentAreaRepository.getValueByBuildingId(item.getId());
		String areaRentString = rentareas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		newBuilding.setRentArea(areaRentString);
		
		return newBuilding;
	}
	
}
