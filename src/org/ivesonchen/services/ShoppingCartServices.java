package org.ivesonchen.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ivesonchen.model.Item;
import org.ivesonchen.model.User;
import org.ivesonchen.model.Wishs;

public class ShoppingCartServices {
	
	Connection connection;
	Statement statement;
	ItemService itemService;
	UserService userService;
	WishListService wishlistService;
	
	public ShoppingCartServices() {
		System.out.println("Connecting!");
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart","root","123");
			if(connection!= null) {
				System.out.println("Connnected to DB!");
				statement = connection.createStatement();
				
				itemService = new ItemService(connection); //init services related to Items
				userService = new UserService(connection); //init services related to Users
				wishlistService = new WishListService(connection); //init services related to wishlist
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Items operations
	 * */
	public List<Item> getAllItems(){
		
		return itemService.getAllItems();
	}
	
	public int updateImgById(int id, String imageUrl) {
		
		return itemService.updateImgById(id, imageUrl);
	}
	
	public Item getItemDetail(int itemId) {
		return itemService.getItemDetail(itemId);
	}
	
	
	/*
	 * Users operations
	 * */
	public int userAuthenticatation(User user) {
		return userService.userAuthenticatation(user);
	}
	
	public int addUserRecord(User user) {
		return userService.addUserRecord(user);
	}
	
	/*
	 * WishList operations
	 * */
	
	public List<Wishs> getAllRecordByUserId(int userId){
		return wishlistService.getAllRecordByUserId(userId);
	} 
	
	public int insertWishInfo(Wishs wish) {
		return wishlistService.insertWishInfo(wish);
	}
	
	public int updateItemNumber(Wishs wish) {
		return wishlistService.updateItemNumber(wish);
	}
	
	public int findItemByUserId_ItemId(int user_id, int item_id) {
		return wishlistService.findItemByUserId_ItemId(user_id, item_id);
	}
	
	public Map<Item, Integer> getWishListItem(int userId){
		
		Map<Item, Integer> map = new HashMap<Item, Integer>();
		List<Wishs> list = getAllRecordByUserId(userId);
		for(Wishs ele : list) {
			
			System.out.println(ele.toString());
			Item item = getItemDetail(ele.getItem_id());
			map.put(item, ele.getItem_number());
		}
		
		return map;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShoppingCartServices db = new ShoppingCartServices();
//		db.getAllItems().forEach(System.out::println);
//		System.out.println("-------------------------");
//		db.updateImgById(1, "apple.png");
//		db.getAllItems().forEach(System.out::println);
		
//		int result = db.userAuthenticatation(new User("Root","chen","root"));
//		System.out.println(result);
		
//		System.out.println(db.getItemDetail(2).toString());
		
		
//		Map<Item, Integer> map = db.getWishListItem(1);
//		
//		System.out.println(map.toString());
	}

}
