package sicsr.proj.piggy_bank1;

import java.util.List;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts.Data;
import android.support.v7.gridlayout.R.id;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class accountspage extends ListActivity {
	
	private DatabaseHandler datasource;
	ListView lv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountspage);
    
        datasource = new DatabaseHandler(this);
		//datasource.open();
		System.out.println("accountpage");
		List<String> values = datasource.getAllAccounts();
		 
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
		
		  lv = getListView();
		 
	        // listening to single list item on click
		  lv.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position,
						long id) {
	              // selected item
	              String account = ((TextView) view).getText().toString();
	 
	              // Launching new Activity on selecting single List Item
	              Intent i = new Intent(getApplicationContext(), MainPage.class);
	              // sending data to new activity
	              i.putExtra("account", account);
	              startActivity(i);
	 
	          }

			
		
	        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_accountspage, menu);
        return true;
    }
    

    
}
