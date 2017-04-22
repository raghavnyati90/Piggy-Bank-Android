package sicsr.proj.piggy_bank1;

public class Accounts {
	private long account_id;
	private String account_name;
	private String balance;
	private String creation_date;

	
	public Accounts(String account_name,String balance,String creation_date) {
		// TODO Auto-generated constructor stub
		this.account_name=account_name;
		this.balance=balance;
		this.creation_date=creation_date;
	}
	
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}

	
}
