package net.zhuoweizhang.myglasscatfacts;

import java.util.*;

import android.app.Activity;
import android.os.Bundle;
import android.content.*;
import android.view.*;


public class MainActivity extends Activity
{

	public final static String ACTION_INSERT_OR_UPDATE = "com.google.glass.timeline.INSERT_OR_UPDATE";

	public final static Random random = new Random();

	private CharSequence[] facts;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		facts = getResources().getTextArray(R.array.facts);
	}

	public void onNewFactButtonClick(View v) {
		addNewFact();
	}

	public void addNewFact() {
		//grab new fact
		CharSequence fact = getRandomFact();
		//make an Intent to create a new Mirror API card with MyGlass
		Intent intent = new Intent(ACTION_INSERT_OR_UPDATE);
		//populate the card by adding intent extras
		intent.putExtra("timeline_item_local_id", "catfact_1234"); //we only have one item; in a real app this is probably more complicated
		intent.putExtra("project_id", "12345678"); //replace with a real project id
		intent.putExtra("token", "12345678");
		intent.putExtra("package_name", "net.zhuoweizhang.myglasscatfacts");
		intent.putExtra("timeline_item_text", fact.toString());

		
		//and send it to the MyGlass app via a Broadcast Intent
		sendBroadcast(intent);

	}

	public CharSequence getRandomFact() {
		return facts[random.nextInt(facts.length)];
	}
}
