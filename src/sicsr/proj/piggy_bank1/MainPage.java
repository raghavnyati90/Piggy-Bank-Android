package sicsr.proj.piggy_bank1;

import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


@TargetApi(11)
public class MainPage extends Activity {
String message="Account name";
String balance="balance",account;
TextView main_accnm,main_bal;
private static int temp=1;
public DatabaseHandler datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        datasource = new DatabaseHandler(this);
        
        if(temp==1)
        {
		Accounts acc= new Accounts("default","000000","22-aug-2012");
    	datasource.createAccount(acc);
        }
        else
        temp++;
        
    	Intent intent=getIntent();
        account=intent.getStringExtra("account");
        if(account==null)
        {
        	account="default";
        }
        
       
        message = intent.getStringExtra(Newaccount_Page.EXTRA_MESSAGE);
      main_accnm=(TextView)findViewById(R.id.main_accnm);
      main_bal=(TextView)findViewById(R.id.main_bal);
     
     balance=datasource.getBalance(account);
     double b=Double.parseDouble(balance); 
     main_accnm.setText(account);
      main_bal.setText(balance);
      if(!(account.equals("default")))
      {
    	  if(b<=0)
    	  {
    		  note();
    	  }
      }
       // createDB();
    }

  //Notification Method
    public void note()
    {
    	NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

	    int icon = R.drawable.ic_launcher;
	    CharSequence text = account+" is out of money";
	    CharSequence contentTitle = "Piggy Bank";
	    CharSequence contentText = account+" please dont spend too much";
	    long when = System.currentTimeMillis();
	 
	   Notification notification = new Notification(icon, text, when); 	
	   Intent intent=new Intent(this,MainPage.class);
	  final int HELLO_ID = 100;
	  PendingIntent contentIntent = PendingIntent.getActivity(this, 0,intent, 0);

      notification.defaults= Notification.DEFAULT_SOUND;
      
     // notification.defaults= Notification.DEFAULT_VIBRATE;
      
	  notification.setLatestEventInfo(getBaseContext(), contentTitle, contentText, contentIntent);

	 notificationManager.notify(HELLO_ID, notification);
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_page, menu);
        return true;
    }
    public void NewAccount(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, Newaccount_Page.class);
    	startActivity(intent);
    }
    public void NewTransaction(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, transactionpage.class);
    	 intent.putExtra("account_name", account);
    	startActivity(intent);
    }
    public void showAccounts(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this,accountspage.class);
    	startActivity(intent);
    }
    public void showActivities(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, Activitiespage.class);
   	 intent.putExtra("account_name", account);
   	intent.putExtra("account_balance", balance);
   	startActivity(intent);
    } 																																																																																																																																																																																																																																																																																																																																																																																																																																																																	
    public void deleteAccount(View view) {
        // Do something in response to button
    	if(account.equals("default"))
    	{
    		Toast.makeText(this,"Default account cannot be deleted", Toast.LENGTH_LONG).show();
    	}
    	else
    	{
    	datasource.deleteAccount(account);
    	Intent intent = new Intent(this,accountspage.class);
    	startActivity(intent);
    	}
    }
public void onPause()
{
	super.onPause();
}
public void onResume()
{
	super.onResume();
}

    
}
