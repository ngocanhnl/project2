package com.javaweb.repository.impl;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.mysql.jdbc.Connection;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository{

	
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "cun112004";
	
	
	@Override
	public DistrictEntity findNameById(Long id) {
		String sql = "SELECT d.name From district d WHERE d.id = " + id + ";";	
		
		DistrictEntity districtEntity = new DistrictEntity();
		try(Connection conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			
		){
					
			while(rs.next()) {
				
				districtEntity.setName(rs.getString("name"));
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected database failed!");
		}
		return districtEntity;
		
	}

}
