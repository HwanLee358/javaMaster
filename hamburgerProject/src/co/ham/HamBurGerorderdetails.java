package co.ham;

public class HamBurGerorderdetails {
	private int ham_order_no;
	private String ham_name;
	private int ham_count;
	private int ham_price;
	private String orderer_id;
	private String order_date;
	
	public int getHam_order_no() {
		return ham_order_no;
	}
	public void setHam_order_no(int ham_order_no) {
		this.ham_order_no = ham_order_no;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	
	public String getOrderer_id() {
		return orderer_id;
	}
	public void setOrderer_id(String orderer_id) {
		this.orderer_id = orderer_id;
	}
	
	public String getHam_name() {
		return ham_name;
	}
	public void setHam_name(String ham_name) {
		this.ham_name = ham_name;
	}
	public int getHam_count() {
		return ham_count;
	}
	public void setHam_count(int ham_count) {
		this.ham_count = ham_count;
	}
	
	public int getHam_price() {
		return ham_price;
	}
	public void setHam_price(int ham_price) {
		this.ham_price = ham_price;
	}
	
	@Override
	public String toString() {
		return String.format("\t%-25s\t%d\t\t\t%d", ham_name, ham_count, ham_price);
	}
	
	public String orderString() {
		return String.format("%-5d%-10s\t%d\t%-5d\t%-10s\t%s", ham_order_no, ham_name, ham_count, ham_price, orderer_id, order_date);
	}
	
}
