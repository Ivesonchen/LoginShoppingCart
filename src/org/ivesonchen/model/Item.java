package org.ivesonchen.model;

public class Item {
	private int id;
	private String name;
	private String imageUrl;
	private String description;
	
	public Item() {}
	
	public Item(String name, String imageUrl, String description) {
		super();
		this.name = name;
		this.imageUrl = imageUrl;
		this.description = description;
	}

	public Item(int id, String name, String imageUrl, String description) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", imageUrl=" + imageUrl + ", description=" + description + "]";
	}

}
