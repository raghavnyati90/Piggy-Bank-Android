package sicsr.proj.piggy_bank1;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Activitiespage extends ListActivity {
TextView accnm,bal;
String account,balance;
ListView lv;

private DatabaseHandler datasource;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitiespage);
    accnm=(TextView)findViewById(R.id.textView1);
    Intent intent=getIntent();
    bal=(TextView)findViewById(R.id.textView2);
    //variable initialization
    account=intent.getStringExtra("account_name");
    balance=intent.getStringExtra("account_balance");
    
    if(account==null)
    	account="default";
    accnm.setText(account.toString()+"'s transactions");
    bal.setText("Balance->"+balance);
    datasource = new DatabaseHandler(this);
    System.out.println("activitypage");
	List<String> values = datasource.getAllActivities(account);
   //datasource.getAllActivities();
	 
	// Use the SimpleCursorAdapter to show the
	// elements in a ListView
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_1, values);
	setListAdapter(adapter);
	 


	
    }
  
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_activitiespage, menu);
        return true;
    }
}
