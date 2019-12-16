package org.ivesonchen.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.ivesonchen.model.Item;

public class ItemService {
	Connection con;
	
	public ItemService(Connection con) {
		this.con = con;
	}
	
	/*
	 * Deal with items table
	 * */
	public List<Item> getAllItems(){
		List<Item> list = new ArrayList<Item>();
		
		try {
			PreparedStatement ps = con.prepareStatement("select * from items");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Item item = new Item(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
				
				list.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Item getItemDetail(int itemId) {
		Item item = null;
		
		try {
			PreparedStatement ps = con.prepareStatement("select * from items where id = ?");
			ps.setInt(1, itemId);
			ResultSet rs = ps.executeQuery();
			
		    rs.first();			
			item = new Item(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}
	
	
	
	public int updateImgById(int id, String imageUrl) {
		try {
			PreparedStatement ps = con.prepareStatement("update items set imageUrl = ? where id = ?");
			
			ps.setString(1, imageUrl);
			ps.setInt(2, id);
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
