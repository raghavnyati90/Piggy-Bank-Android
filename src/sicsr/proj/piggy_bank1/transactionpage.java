package sicsr.proj.piggy_bank1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.support.v4.app.NavUtils;

public class transactionpage extends Activity {
RadioButton income,expense;
Spinner categorySpinner,day,month,year;
EditText t_date,t_amount,t_add_info;
TextView Accnm;
String account,date,type,category,add_info;
double amount;
private DatabaseHandler datasource;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactionpage);
        Intent intent=getIntent();
        //variable initialization
        datasource = new DatabaseHandler(this);
       
        account=intent.getStringExtra("account_name");
        if(account==null)
        	account="default";
        day=(Spinner)findViewById(R.id.spinner1);
        month=(Spinner)findViewById(R.id.spinner2);
        year=(Spinner)findViewById(R.id.spinner3);
		  t_amount=(EditText)findViewById(R.id.t_amount);
	        t_add_info=(EditText)findViewById(R.id.t_add_info);
        Accnm=(TextView)findViewById(R.id.tran_accnm);
        income=(RadioButton)findViewById(R.id.income);
        expense=(RadioButton)findViewById(R.id.expense);
        categorySpinner=(Spinner)findViewById(R.id.category);
        Accnm.setText(account.toString());
    }

	public void saveTransaction(View view)
	{
						
		try{
			date=String.valueOf(day.getSelectedItem())+"/"+String.valueOf(month.getSelectedItem()+"/"+String.valueOf(year.getSelectedItem()));
			amount=Double.parseDouble(t_amount.getText().toString());
			add_info=t_add_info.getText().toString();
			
			category=String.valueOf(categorySpinner.getSelectedItem());
	        if(income.isChecked())
	        	type="income";
	        else if(expense.isChecked())
	        	type="expense";
	       //t_add_info.setText(category);
		
	       Transactions transaction=new Transactions(account,date,type,category,amount,add_info);
	       Accounts account1=datasource.getAccount(account);
	        datasource.createTransation(transaction);
	        datasource.updateAccount(account1, transaction);
	        Intent intent = new Intent(this, MainPage.class);
	        intent.putExtra("account", account);
	    	startActivity(intent);
		}
		catch(Exception e)
		{
			Toast.makeText(getBaseContext(),"Amount is Required",Toast.LENGTH_LONG).show();
		}
	       
	}
	public void resetValues(View view)
	{
		t_amount.setText("");
		t_add_info.setText("");
		Intent intent=new Intent(this,MainPage.class);
		startActivity(intent);
		
		
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_transactionpage, menu);
        return true;
    }

    
}
