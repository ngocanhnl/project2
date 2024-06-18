package com.javaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuldingDTO;
import com.javaweb.service.BuildingService;

@RestController
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;
	
	
	@GetMapping(value = "/api/building/test")
	public List<BuldingDTO> getBuiding(@RequestParam(name = "name", required = false) String name,
									   @RequestParam(name = "districtid", required = false) Long district ) {
		List<BuldingDTO> result = buildingService.finaAllBuilding(name, district);
		return result;
	}
	
	
	//djfdjfd
	
	
}