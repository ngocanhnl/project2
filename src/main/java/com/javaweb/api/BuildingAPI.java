package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuldingDTO;
import com.javaweb.service.BuildingService;

@RestController
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;
	
	
	@GetMapping(value = "/api/building")
	public List<BuldingDTO> getBuiding(@RequestParam Map<String, Object> params,
									   @RequestParam(name="typeCode", required = false) List<String> typeCode
		) {
		List<BuldingDTO> result = buildingService.finaAllBuilding(params, typeCode);
		return result;
	}
	
	
	
}