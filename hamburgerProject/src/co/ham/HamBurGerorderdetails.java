package co.ham;

public class HamBurGerorderdetails {
	private String ham_name;
	private int ham_count;
	private int ham_price;
	private String orderer_id;

	
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
		return String.format("\t%s %25d\t %18d", ham_name, ham_count, ham_price);
	}
	
}
