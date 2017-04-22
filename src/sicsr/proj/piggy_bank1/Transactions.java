package sicsr.proj.piggy_bank1;

public class Transactions {
	private long t_id;
	private String account_name;
	private String t_date;
	private String t_type;
	private String t_category;
	private double t_amount;
	private String t_add_info;
	
	
	public Transactions(String account_name,String t_date,String t_type,String t_category,double t_amount,String t_add_info) {
		// TODO Auto-generated constructor stub
		this.account_name=account_name;
		this.t_date=t_date;
		this.t_type=t_type;
		this.t_category=t_category;
		this.t_amount=t_amount;
		this.t_add_info=t_add_info;
	}
	public long getT_id() {
		return t_id;
	}
	public void setT_id(long t_id) {
		this.t_id = t_id;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getT_date() {
		return t_date;
	}
	public void setT_date(String t_date) {
		this.t_date = t_date;
	}
	public String getT_category() {
		return t_category;
	}
	public void setT_category(String t_category) {
		this.t_category = t_category;
	}
	public String getT_add_info() {
		return t_add_info;
	}
	public void setT_add_info(String t_add_info) {
		this.t_add_info = t_add_info;
	}
	public String getT_type() {
		return t_type;
	}
	public void setT_type(String t_type) {
		this.t_type = t_type;
	}
	public Double getT_amount() {
		return t_amount;
	}
	public void setT_amount(double t_amount) {
		this.t_amount = t_amount;
	}
	
	

}
