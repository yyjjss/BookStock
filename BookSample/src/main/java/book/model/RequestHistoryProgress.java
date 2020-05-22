package book.model;

public class RequestHistoryProgress {

	int scheduled_amount;
	String isbn;
	String due_date;
	String title;
	int type_id;
	int warehouse_id;
	String warehouse_name;
	int incoming_amount;
	int release_amount;
	int stock;
	
	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getScheduled_amount() {
		return scheduled_amount;
	}
	public void setScheduled_amount(int scheduled_amount) {
		this.scheduled_amount = scheduled_amount;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public int getWarehouse_id() {
		return warehouse_id;
	}
	public void setWarehouse_id(int warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
	public String getWarehouse_name() {
		return warehouse_name;
	}
	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
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
	
	
	
	
}
