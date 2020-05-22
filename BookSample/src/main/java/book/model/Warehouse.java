package book.model;

public class Warehouse {

	int warehouse_id;
	String zip;
	String address;
	String address_detail;
	String warehouse_name;
	int employee_count;
	int maximum_quantity;
	public int getWarehouse_id() {
		return warehouse_id;
	}
	public void setWarehouse_id(int warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress_detail() {
		return address_detail;
	}
	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}
	
	public String getWarehouse_name() {
		return warehouse_name;
	}
	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}
	
	public int getEmployee_count() {
		return employee_count;
	}
	public void setEmployee_count(int employee_count) {
		this.employee_count = employee_count;
	}
	public int getMaximum_quantity() {
		return maximum_quantity;
	}
	public void setMaximum_quantity(int maximum_quantity) {
		this.maximum_quantity = maximum_quantity;
	}
	@Override
	public String toString() {
		return "Warehouse [warehouse_id=" + warehouse_id + ", zip=" + zip + ", address=" + address + ", address_detail="
				+ address_detail + ", warehouse_name=" + warehouse_name + ", employee_count=" + employee_count
				+ ", maximum_quantity=" + maximum_quantity + "]";
	}
	
	
}
