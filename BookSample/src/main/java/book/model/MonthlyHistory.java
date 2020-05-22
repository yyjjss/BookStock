package book.model;

import java.util.Date;

public class MonthlyHistory {

	
	String isbn;
	int warehouse_id;
	Date date;
	int incoming_amount;
	int release_amount;
	int stock;
	String title;
	String warehouse_name;
	
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getWarehouse_id() {
		return warehouse_id;
	}
	public void setWarehouse_id(int warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
