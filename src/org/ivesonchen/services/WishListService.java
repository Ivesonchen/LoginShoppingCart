package org.ivesonchen.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.ivesonchen.model.*;


public class WishListService {
	Connection con;
	
	public WishListService(Connection con) {
		this.con = con;
	}
	
	
	public List<Wishs> getAllRecordByUserId(int userId) {
		List<Wishs> list = new ArrayList<>();

		try {
			PreparedStatement ps = con.prepareStatement("select * from wishList where user_id = ?");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Wishs wish = new Wishs(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
				list.add(wish);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	public int insertWishInfo(Wishs wish) {
		int user_id = wish.getUser_id();
		int item_id = wish.getItem_id();
		int item_number = wish.getItem_number();
		
		try {
			PreparedStatement ps = con.prepareStatement("insert into wishList (user_id, item_id, item_number) values (?,?,?)");
			ps.setInt(1, user_id);
			ps.setInt(2, item_id);
			ps.setInt(3, item_number);
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	public int updateItemNumber(Wishs wish) {
		int user_id = wish.getUser_id();
		int item_id = wish.getItem_id();
		int item_number = wish.getItem_number();
		
		try {
			PreparedStatement ps = con.prepareStatement("update wishList set item_number = ? where user_id = ? and item_id = ?");
			ps.setInt(1, item_number);
			ps.setInt(2, user_id);
			ps.setInt(3, item_id);
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public int findItemByUserId_ItemId(int user_id, int item_id) {
		
		try {
			PreparedStatement ps = con.prepareStatement("select * from wishList where user_id = ? and item_id = ?",
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
				    ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, user_id);
			ps.setInt(2, item_id);
			ResultSet rs = ps.executeQuery();
			
		    rs.last();
		    int size = rs.getRow();
		    return size;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
}
