package com.example.diary;

import java.io.IOException;

import model.Contact;

import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private final int REQUEST_CODE1 = 1;
	private Button contactButton;
	private Context context;
	private Contact contact;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;

		contactButton = (Button) findViewById(R.id.buttonContact);
		contactButton.setOnClickListener(this);

		XmlResourceParser parser = getResources().getXml(R.xml.diary_data);
		DiaryXmlParser diaryParser = new DiaryXmlParser(this);

		try {
			diaryParser.parse(parser);
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		contact = diaryParser.getContact();
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.buttonContact:
			Intent i = new Intent(context, ContactActivity.class);
			i.putExtra("Contact", contact);
			startActivityForResult(i, REQUEST_CODE1);
			break;
		case R.id.buttonCourses:
			Toast.makeText(context, "Here are the courses I taught!", Toast.LENGTH_LONG).show();
			break;
		case R.id.buttonEvents:
			Toast.makeText(context, "Here are my events!", Toast.LENGTH_LONG).show();
			break;
		case R.id.buttonNews:
			Toast.makeText(context, "Here are my news!", Toast.LENGTH_LONG).show();
			break;
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE1 && resultCode == RESULT_OK)
				contact = (Contact) data.getExtras().get("Contact");

		super.onActivityResult(requestCode, resultCode, data);
	}

}
