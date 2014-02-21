package com.example.diary;

import java.io.IOException;
import java.util.ArrayList;

import model.Contact;
import model.Course;
import model.Event;

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
	private Button contactButton, coursesButton, eventsButton, newsButton;
	private Context context;
	private Contact contact;
	private ArrayList<Course> courses = new ArrayList<Course>();
	private ArrayList<Event> events = new ArrayList<Event>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;

		contactButton = (Button) findViewById(R.id.buttonContact);
		coursesButton = (Button) findViewById(R.id.buttonCourses);
		eventsButton = (Button) findViewById(R.id.buttonEvents);
		newsButton = (Button) findViewById(R.id.buttonNews);

		contactButton.setOnClickListener(this);
		coursesButton.setOnClickListener(this);
		eventsButton.setOnClickListener(this);
		newsButton.setOnClickListener(this);

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
		courses = diaryParser.getCourses();
		events= diaryParser.getEvents();
	}

	@Override
	public void onClick(View v) {
		Intent i;

		switch(v.getId()) {
		case R.id.buttonContact:
			i = new Intent(context, ContactActivity.class);
			i.putExtra("Contact", contact);
			startActivityForResult(i, REQUEST_CODE1);
			break;
		case R.id.buttonCourses:
			i = new Intent(context, CourseActivity.class);
			i.putParcelableArrayListExtra("Courses", courses);
			startActivityForResult(i, REQUEST_CODE1);
			break;
		case R.id.buttonEvents:
			i = new Intent(context, EventActivity.class);
			i.putParcelableArrayListExtra("Events", events);
			startActivityForResult(i, REQUEST_CODE1);
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
