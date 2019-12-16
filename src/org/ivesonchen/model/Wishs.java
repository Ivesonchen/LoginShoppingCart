package org.ivesonchen.model;

public class Wishs {
	private int id;
	private int user_id;
	private int item_id;
	private int item_number;
	
	public Wishs() {}
	
	public Wishs(int user_id, int item_id, int item_number) {
		super();
		this.user_id = user_id;
		this.item_id = item_id;
		this.item_number = item_number;
	}
	
	public Wishs(int id, int user_id, int item_id, int item_number) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.item_id = item_id;
		this.item_number = item_number;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getItem_number() {
		return item_number;
	}
	public void setItem_number(int item_number) {
		this.item_number = item_number;
	}
	
	@Override
	public String toString() {
		return "Wishs [id=" + id + ", user_id=" + user_id + ", item_id=" + item_id + ", item_number=" + item_number
				+ "]";
	}
}
