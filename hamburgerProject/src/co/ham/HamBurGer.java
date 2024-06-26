package co.ham;

public class HamBurGer {
	private int ham_No;
	private String ham_Name;
	private int ham_Kcal;
	private String ham_Hire_Date;
	private int ham_Price;
	private String ham_Stuff;
	
	private int ham_type;
	private String ham_Name_modify;
	
	public String getHam_Name_modify() {
		return ham_Name_modify;
	}
	public void setHam_Name_modify(String ham_Name_modify) {
		this.ham_Name_modify = ham_Name_modify;
	}
	
	public int getHam_type() {
		return ham_type;
	}
	public void setHam_type(int ham_type) {
		this.ham_type = ham_type;
	}

	
	public int getHam_No() {
		return ham_No;
	}
	public void setHam_No(int ham_No) {
		this.ham_No = ham_No;
	}
	public String getHam_Name() {
		return ham_Name;
	}
	public void setHam_Name(String ham_Name) {
		this.ham_Name = ham_Name;
	}
	public int getHam_Kcal() {
		return ham_Kcal;
	}
	public void setHam_Kcal(int ham_Kcal) {
		this.ham_Kcal = ham_Kcal;
	}
	public String getHam_Hire_Date() {
		return ham_Hire_Date;
	}
	public void setHam_Hire_Date(String ham_Hire_Date) {
		this.ham_Hire_Date = ham_Hire_Date;
	}
	public int getHam_Price() {
		return ham_Price;
	}
	public void setHam_Price(int ham_Price) {
		this.ham_Price = ham_Price;
	}
	public String getHam_Stuff() {
		return ham_Stuff;
	}
	public void setHam_Stuff(String ham_Stuff) {
		this.ham_Stuff = ham_Stuff;
	}
	
	@Override
	public String toString() {
		
		return String.format("%-6d%-10s\t%d(kcal)\t %s\t %d원",ham_No,ham_Name,ham_Kcal,ham_Hire_Date,ham_Price);
	}
}
