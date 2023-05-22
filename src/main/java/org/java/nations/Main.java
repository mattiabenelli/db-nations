package org.java.nations;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.TimeZone;

import com.google.protobuf.Timestamp;

public class Main {
	
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/db-nations";
		String user = "root";
		String password = "codecode";
		
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection(url, user, password)){
		    
			String sql = " select c.name, c.country_id, r.name, c2.name \r\n"
					+ "from countries c \r\n"
					+ "join regions r \r\n"
					+ "	on r.region_id = c.region_id \r\n"
					+ "join continents c2 \r\n"
					+ "	on c2.continent_id = r.continent_id \r\n"
					+ "order by c.name ";
			
			
			
			try (PreparedStatement ps = con.prepareStatement(sql)) {
	
				
				try (ResultSet rs = ps.executeQuery()) {
					
					while(rs.next()) {
						
						final String countryName = rs.getString(1);
						final int countryId = rs.getInt(2);
						final String regionName = rs.getString(3);
						final String continentName = rs.getString(4);
						
						System.out.println(countryName + " - " + countryId + " - " 
								+ regionName + " - " + continentName);
					}
				}				
			} catch (SQLException ex) {
				System.err.println("Query not well formed");
			}
		} catch (SQLException ex) {
			System.err.println("Error during connection to db");
		}
	}
	

		
	}

