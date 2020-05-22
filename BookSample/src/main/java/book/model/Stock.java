package book.model;

import java.util.Date;

public class Stock {

	String isbn;
	Date date;
	int warehouse_id;
	String warehouse_name;
	int incoming_amount;
	int release_amount;
	int stock;
	String title;
	String dumpDate;
	
	
	
	
	
	public String getDumpDate() {
		return dumpDate;
	}
	public void setDumpDate(String dumpDate) {
		this.dumpDate = dumpDate;
	}
	@Override
	public String toString() {
		return "Stock [isbn=" + isbn + ", date=" + date + ", warehouse_id=" + warehouse_id + ", warehouse_name="
				+ warehouse_name + ", incoming_amount=" + incoming_amount + ", release_amount=" + release_amount
				+ ", stock=" + stock + ", title=" + title + "]";
	}
	public String getWarehouse_name() {
		return warehouse_name;
	}
	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
