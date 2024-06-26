package com.javaweb.repository.impl;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;
import com.mysql.jdbc.Connection;



@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "cun112004";
	
	
	public static void joinTable(Map<String, Object> params, List<String> typeCode, StringBuilder sql) {
		String staffid = (String)params.get("staffid");
		if(StringUtil.checkString(staffid) == true) {
			sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
		}
		
		if(typeCode != null && typeCode.size() != 0) {
			sql.append(" INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
			sql.append(" INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeid ");
		}
		
		
		String rentAreaTo = (String)params.get("areaTo");
		String rentAreaFrom = (String)params.get("areaFrom");
		if(StringUtil.checkString(rentAreaFrom) == true ||StringUtil.checkString(rentAreaTo) == true) {
			sql.append(" INNER JOIN rentarea ON b.id = rentarea.buildingid ");
		}
		
	}
	
	public static void queryNormal(Map<String, Object> params, StringBuilder where) {
		for(Map.Entry<String, Object> it : params.entrySet()) {
			if(!it.getKey().equals("staffid") && !it.getKey().equals("typeCode")
					&& !it.getKey().startsWith("area") &&!it.getKey().startsWith("rentPrice")
					) {
				String value = it.getValue().toString();
				if(StringUtil.checkString(value)==true) {
					if(NumberUtil.isNumber(value) == true) { 
						where.append(" AND b." + it.getKey() + " = " + value);
					}
					else {
						where.append(" AND b." + it.getKey() + " LIKE '%" + value + "%' ");
					}
				}
				
			}
		}
	}
	
	
	public static void querySpecial(Map<String, Object> params, List<String> typeCode, StringBuilder where) {
		String staffId = (String) params.get("staffid");
		if(StringUtil.checkString(staffId) == true) {
			where.append(" AND assignmentbuilding.staffid = " + staffId);
		}
		
		//  Rent Area 
		String rentAreaFrom = (String)params.get("areaFrom");
		String rentAreaTo = (String)params.get("areaTo");
		if(StringUtil.checkString(rentAreaTo)== true || StringUtil.checkString(rentAreaFrom)==true) {
			if(StringUtil.checkString(rentAreaFrom)) {
				where.append(" AND rentarea.value >= " + rentAreaFrom);
			}
			if(StringUtil.checkString(rentAreaTo)) {
				where.append(" AND rentarea.value <= " + rentAreaTo);
			}
		}
		
		// Rent Price
		String rentPriceFrom = (String)params.get("rentPriceFrom");
		String rentPriceTo = (String)params.get("rentPriceTo");
		if(StringUtil.checkString(rentPriceFrom)== true || StringUtil.checkString(rentPriceTo)==true) {
			if(StringUtil.checkString(rentPriceFrom)) {
				where.append(" AND b.rentprice >= " + rentPriceFrom);
			}
			if(StringUtil.checkString(rentPriceTo)) {
				where.append(" AND b.rentprice <= " + rentPriceTo);
			}
		}
		
		if(typeCode != null && typeCode.size() != 0) {
			List<String> code = new ArrayList<>();
			for(String item : typeCode) {
				code.add("'" + item + "'");
			}
			where.append(" AND renttype.code IN (" + String.join(", ", code) + ") ");
		}
	}
	
	
	@Override
	public List<BuildingEntity> finaAllBuilding(Map<String, Object> params, List<String> typeCode){
		
		StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.districtid, b.street, b.ward, b.numberofbasement,"
				+ " b.floorarea, b.rentprice, b.managername, b.managerphonenumber, b.servicefee, b.brokeragefee "
				+ "\nFROM building b ");
		joinTable(params, typeCode, sql);
		StringBuilder where = new StringBuilder("WHERE 1 = 1 ");
		queryNormal(params, where);
		querySpecial(params, typeCode, where);
		where.append(" GROUP BY b.id; ");
		sql.append(where);
		
		List<BuildingEntity> result = new ArrayList<>();
		try(Connection conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			
		){
					
			while(rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getLong("b.id"));
				building.setName(rs.getString("b.name"));
				building.setStreet(rs.getString("b.street"));
				building.setWard(rs.getString("b.ward"));
				building.setDistrictid(rs.getLong("b.districtid"));
				building.setFloorArea(rs.getLong("b.floorarea"));
				building.setRentPrice(rs.getLong("b.rentprice"));
				building.setServiceFee(rs.getString("b.servicefee"));
				building.setBrokerageFee(rs.getLong("b.brokeragefee"));
				building.setManagerName(rs.getString("b.managername"));
				building.setManagerPhoneNumber(rs.getString("b.managerphonenumber"));
				
				result.add(building);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected database failed!");
		}
		
		
		
		
		return result;
	}

}
