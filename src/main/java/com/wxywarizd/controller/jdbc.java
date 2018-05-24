package com.wxywarizd.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbc {
	
	public static void main(String[] args) {
		final String USERNAME = "root";
		final String PASSWORD = "root123";
		final String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT ";
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stat = conn.createStatement();
			String sql = "SELECT * FROM humans";
			
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString("userName"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        finally{
        	try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stat != null) {
					stat.close();
					stat = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				} 
			} catch (Exception e2) {
				e2.printStackTrace();
			}
        }
        
        
        
	}


}
