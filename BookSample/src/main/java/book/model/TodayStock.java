package book.model;

import java.util.Date;

public class TodayStock {

	String isbn;
	Date date;
	int warehouse_id;
	int incoming_scheduled_amount;
	int release_scheduled_amount;
	int incoming_amount;
	int release_amount;
	int stock;
	String title;
	String warehouse_name;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWarehouse_name() {
		return warehouse_name;
	}
	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getWarehouse_id() {
		return warehouse_id;
	}
	public void setWarehouse_id(int warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
	public int getIncoming_scheduled_amount() {
		return incoming_scheduled_amount;
	}
	public void setIncoming_scheduled_amount(int incoming_scheduled_amount) {
		this.incoming_scheduled_amount = incoming_scheduled_amount;
	}
	public int getRelease_scheduled_amount() {
		return release_scheduled_amount;
	}
	public void setRelease_scheduled_amount(int release_scheduled_amount) {
		this.release_scheduled_amount = release_scheduled_amount;
	}
	public int getIncoming_amount() {
		return incoming_amount;
	}
	public void setIncoming_amount(int incoming_amount) {
		this.incoming_amount = incoming_amount;
	}
	public int getRelease_amount() {
		return release_amount;
	}
	public void setRelease_amount(int release_amount) {
		this.release_amount = release_amount;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
}
