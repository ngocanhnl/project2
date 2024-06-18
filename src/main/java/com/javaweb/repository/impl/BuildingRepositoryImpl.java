package com.javaweb.repository.impl;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.mysql.jdbc.Connection;



@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "cun112004";
	@Override
	public List<BuildingEntity> finaAllBuilding(String name, Long districtId) {
		StringBuilder sql = new StringBuilder("SELECT * FROM building b WHERE 1=1 ");
		
		if(name != null && !name.equals("")) {
			sql.append("AND b.name LIKE '%" + name + "%' ");
		}
		
		if(districtId != null) {
			sql.append("AND b.districtid = " + districtId + " ");
		}
		
		
		List<BuildingEntity> result = new ArrayList<>();
		try(Connection conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			
		){
					
			while(rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setName(rs.getString("name"));
				building.setNumberOfBasementInteger(rs.getInt("numberOfBasement"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				
				result.add(building);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected database failed!");
		}
		
		
		
		
		return result;
	}

}
