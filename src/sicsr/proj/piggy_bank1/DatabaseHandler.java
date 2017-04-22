package sicsr.proj.piggy_bank1;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;
	

	// Database Name
	private static final String DATABASE_NAME = "piggybank";

	// Contacts table name
	private static final String TABLE_ACCOUNTS = "accounts";
	private static final String TABLE_TRANSACTIONS = "transactions";
	
	// Contacts Table Columns names
	private static final String KEY_ID = "account_id";
	private static final String KEY_NAME = "account_name";
	private static final String KEY_balance = "balance";
	private static final String KEY_date = "creation_date";

	private static final String KEY_T_ID = "t_id";
	private static final String KEY_T_DATE = "t_date";
	private static final String KEY_T_TYPE = "t_type";
	private static final String KEY_T_CATEGORY = "t_category";
	private static final String KEY_T_AMOUNT = "t_amount";
	private static final String KEY_T_ADD_INFO = "t_add_info";
	
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + TABLE_ACCOUNTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT UNIQUE,"+ KEY_balance + " TEXT NOT NULL," +
						KEY_date + " TEXT NOT NULL)";
		String CREATE_TRANSACTION_TABLE = "CREATE TABLE " + TABLE_TRANSACTIONS + "("
				+ KEY_T_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT NOT NULL,"+ KEY_T_DATE + " TEXT NOT NULL," +
						KEY_T_TYPE + " TEXT NOT NULL,"+ KEY_T_CATEGORY +" TEXT NOT NULL,"+ KEY_T_AMOUNT +" REAL NOT NULL,"+ KEY_T_ADD_INFO+" TEXT)";
		db.execSQL(CREATE_ACCOUNTS_TABLE);
		db.execSQL(CREATE_TRANSACTION_TABLE);
		
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */
	//Adding new Account
	public void createAccount(Accounts account) {
			SQLiteDatabase db = this.getWritableDatabase();
			
			ContentValues values = new ContentValues();
			values.put(KEY_NAME, account.getAccount_name()); 
			values.put(KEY_balance, account.getBalance()); 
			values.put(KEY_date, account.getCreation_date());
			// Inserting Row
			db.insert(TABLE_ACCOUNTS, null, values);
			System.out.println(account.getBalance());
			db.close(); // Closing database connection
		}

	// Adding new Transaction
	public void createTransation(Transactions transaction) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, transaction.getAccount_name()); 
		values.put(KEY_T_DATE, transaction.getT_date()); 
		values.put(KEY_T_TYPE, transaction.getT_type());
		values.put(KEY_T_CATEGORY,transaction.getT_category());
		values.put(KEY_T_AMOUNT,transaction.getT_amount());
		values.put(KEY_T_ADD_INFO,transaction.getT_add_info());
		// Inserting Row
		System.out.println(transaction.getT_amount());
		db.insert(TABLE_TRANSACTIONS, null, values);
		db.close(); // Closing database connection
		
		//System.out.println("createtransaction");
	}

	
	// Getting balance of particular account
	public String getBalance(String name) {
			
		String selectQuery = "SELECT * FROM "+TABLE_ACCOUNTS+" WHERE ACCOUNT_NAME='"+name+"'";
		System.out.println(selectQuery);
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor != null)
			cursor.moveToFirst();
		else
			System.out.println("null cursor");

		Accounts account = new Accounts(cursor.getString(1),cursor.getString(2),cursor.getString(3));
		account.setAccount_id(Long.parseLong(cursor.getString(0)));
		cursor.close();
		db.close(); 
		return account.getBalance();
		
		
	}
	
	//Getting a single account
	public Accounts getAccount(String accnm) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_ACCOUNTS, new String[] { KEY_ID,
				KEY_NAME, KEY_balance,KEY_date }, KEY_NAME + "=?",
				new String[] { String.valueOf(accnm) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Accounts account = new Accounts(cursor.getString(1), cursor.getString(2),cursor.getString(3));
		// return contact
		return account;
	}
	
	// Getting All Contacts
	public List<String> getAllAccounts() {
		List<String> contactList = new ArrayList<String>();
		// Select All Query
		String selectQuery = "SELECT * FROM " + TABLE_ACCOUNTS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Accounts account = new Accounts(cursor.getString(1),cursor.getString(2),cursor.getString(3));
				account.setAccount_id(Long.parseLong(cursor.getString(0)));
				// Adding contact to list
				contactList.add(account.getAccount_name());
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close(); 
		return contactList;
	}
	
	public List<String> getAllActivities(String accnm) {
		List<String> TList = new ArrayList<String>();
		// Select All Query
		String selectQuery = "SELECT * FROM " + TABLE_TRANSACTIONS + " WHERE ACCOUNT_NAME='"+ accnm +"' ORDER BY T_ID DESC";
		System.out.println(selectQuery);
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Transactions transaction = new Transactions(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getDouble(5),cursor.getString(6));
				transaction.setT_id(Long.parseLong(cursor.getString(0)));
				// Adding contact to list
				String types=transaction.getT_type().substring(0,3);
				TList.add(String.valueOf(transaction.getT_date().toString()+"->"+transaction.getT_category().toString()+"->"+
				types+"->"+transaction.getT_amount().toString()+"->"+transaction.getT_add_info()));
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close(); 
		return TList;
		
	}

	// Updating single Account
	public int updateAccount(Accounts account,Transactions transaction) {
		SQLiteDatabase db = this.getWritableDatabase();

		Double balance=Double.parseDouble(account.getBalance());
		Double amount=transaction.getT_amount();
		String type=transaction.getT_type();
		System.out.println(balance);
		if(type.equals("income"))
			balance=balance+amount;
		else if(type.equals("expense"))
			balance=balance-amount;
		
		System.out.println(balance);
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, account.getAccount_name());
		values.put(KEY_balance, balance);
		values.put(KEY_date, account.getCreation_date());

		// updating row
		return db.update(TABLE_ACCOUNTS, values, KEY_NAME + " = ?",
				new String[] { String.valueOf(account.getAccount_name()) });
	}

	
	
	// Deleting single contact
	public void deleteAccount(String accnt) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_ACCOUNTS, KEY_NAME + " = ?",
				new String[] { accnt });
		db.delete(TABLE_TRANSACTIONS, KEY_NAME + " = ?",
				new String[] { accnt });
		db.close();
	}




}
