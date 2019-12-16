package org.ivesonchen.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.ivesonchen.model.User;

public class UserService {
	Connection con;
	
	public UserService(Connection con) {
		this.con = con;
	}
	
	/*
	 * @return -1 if failed , userId if success
	 * */
	public int userAuthenticatation(User user) {

		try {
			PreparedStatement ps = con.prepareStatement("select id, password from users where firstName = ? and lastName = ?",
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
				    ResultSet.CONCUR_READ_ONLY);
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			String password = user.getPassword();
			
			ps.setString(1, firstName);
			ps.setString(2, lastName);
//			ps.setString(3, password);
			ResultSet rs = ps.executeQuery();
			
//			System.out.println(rs);
			
		    rs.last();
		    int size = rs.getRow();
		    String passWordFromDB = "";
		    int userIdFromDB = -1;
		    System.out.println(size);
		    
		    if(size == 0) {
		    	return 0;
		    } else if(size == 1) {
			    rs.first();
			    passWordFromDB = rs.getString(2);
			    userIdFromDB = rs.getInt(1);
//			    System.out.println(passWordFromDB);
//			    System.out.println(userIdFromDB);
		    }
		    
		    if(passWordFromDB.equals(password)) return userIdFromDB;
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	/*
	 * @return -1 if failed, 1 if success
	 * */
	public int addUserRecord(User user) {
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String password = user.getPassword();
		
		try {
			PreparedStatement ps = con.prepareStatement("insert into users (firstName, lastName, password) values (?, ?, ?)");
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, password);
			return ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public void changePassword() {}; //later
}
