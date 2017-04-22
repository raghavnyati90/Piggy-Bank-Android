package sicsr.proj.piggy_bank1;



import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Newaccount_Page extends Activity {

EditText accountnm,balance;
String accnm,bal;
private DatabaseHandler datasource;

public static final String EXTRA_MESSAGE = "MESSAGE";
public static final String bala = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newaccount__page);
        System.out.println("newaccount1");
        datasource = new DatabaseHandler(this);
		//datasource.open();
		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_newaccount__page, menu);
        return true;
    }
    public void saveAccount(View view)
    {
    	String error="";
    	int temp=0;
    	
    	accountnm=(EditText)findViewById(R.id.accountnm);
    	balance=(EditText)findViewById(R.id.accountbal);
    	accnm=accountnm.getText().toString();
    	bal=balance.getText().toString();
    	if(accnm.equals(""))
    	{
    		if(bal.equals(""))
        	{
    			error="Account Name is required \n";
    			error=error+"Balance is required";
    			Toast.makeText(getBaseContext(),error,Toast.LENGTH_LONG).show();
        	}
    		else
    		{
    			error="Account Name is required \n";
        		Toast.makeText(getBaseContext(),error,Toast.LENGTH_LONG).show();
    		}
    	}
    	else
    		{
    		if(bal.equals(""))
        	{
    			error=error+"Balance is required";
    			Toast.makeText(getBaseContext(),error,Toast.LENGTH_LONG).show();
        	}
    		else
    		{
    			Calendar c = Calendar.getInstance();
    			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    			String currdate = df.format(c.getTime());
		
   				Accounts acc= new Accounts(accnm,bal,currdate);
    	
   				
   				Double balanc=Double.parseDouble(bal);
   				datasource.createAccount(acc);
   				Intent intent = new Intent(this, accountspage.class);
   				startActivity(intent);
    		}
    		}
    	}
    	
    }


