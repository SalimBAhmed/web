package com.web;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.web.SQLConnection.DBConnection;
import com.web.model.City;

import javax.ws.rs.*;

@Path("show")
public class main {
	private static Statement stmt;
	private static ResultSet results;
	//  Check if table exists, Create table if not Create a new one
	/*@GET
	@Path("CDB")
	public void checkTable(String table_name){
		Connection connection = DBConnection.createNewDBConnection();
		if (!tableExists(table_name,connection){
			connection.createStatement().executeUpdate("create table city (id int NOT NULL PRIMARY KEY AUTO_INCREMENT,city_name varchar(80) NOT NULL, img varchar(80) NOT NULL, city_desc varchar(1500) NOT NULL, places varchar(255), places_desc varchar(2000), places_img varchar(255) ");
		}
	}
	public String tableExists(String table_name, Connection connection) throws SQLException {
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		ResultSet resultSet = databaseMetaData.getTables(null, null, tableName, new String[] {"TABLE"});
		while (resultSet.next()) {
		    String name = resultSet.getString("TABLE_NAME");
		    if(name == table_name) {
		    	return true;
		    }
		}
		return false;
	}
	*/
	@GET
    @Path("city")
    @Produces("application/json")
    public List<City> getAllCities() {
		String sql_select = "Select * From city";
		
		List<City> cityList = new ArrayList<City>();

		try(Connection conn = DBConnection.createNewDBConnection()){
			
			stmt = conn.createStatement();
			results = stmt.executeQuery(sql_select);
			
						
			
			 while (results.next()) {
				int id = Integer.valueOf(results.getString("id"));
				String name = results.getString("city_name");
				String img = results.getString("img");
				String desc = results.getString("city_desc");
				City city = new City(id,name,img,desc);
				
				cityList.add(city);
			 }
			
			ObjectMapper mapper = new ObjectMapper();
		    String JSONOutput = mapper.writeValueAsString(cityList);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return cityList;
    }
	

	@GET
    @Path("city/{id}")
	@Produces("application/json")
	public City getCity(@PathParam("id") final int id) {
		// get city from DataBase and return it
		String sql_select = "Select * From city WHERE (id = "+ Integer.toString(id) + ")";
		City city = new City();
		try(Connection conn = DBConnection.createNewDBConnection()){
			
			stmt = conn.createStatement();
			results = stmt.executeQuery(sql_select);
			
			if(results.next()) {
				String name = results.getString("city_name");
				String img = results.getString("img");
				String desc = results.getString("city_desc");
				
				city.setCityId(id);
				city.setCityName(name);
				city.setImg(img);
				city.setCityDesc(desc);
				
				ObjectMapper mapper = new ObjectMapper();
			    String JSONOutput = mapper.writeValueAsString(city);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	    return city;
	}
	
	@POST
	@Path("/addCity")
	public City addCity(){
		//String input = "{\"img\":\"tunis.png\",\"places\":[],\"cityName\":\"Tunis\",\"cityDesc\":\"hello\",\"placesDesc\":[],\"placesImg\":[],\"cityId\":0}";
		String input = "{\"img\": \"sousse.jpg\", \"cityName\" : \"Sousse\", \"cityDesc\" : \"SOUSSE \", \"places\":[\"MÉDINA DE SOUSSE\",\"PORT KANTAOUI\",\"GOLF\"],\"placesDesc\":[],\"placesImg\":[]}";
		City city = new City();
		try{
			ObjectMapper objectMapper = new ObjectMapper();
			city = objectMapper.readValue(input, City.class);
		} catch ( Exception e){
			e.printStackTrace();
		}

		return city;
	}

}
