package com.example.diary;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import model.Contact;
import model.Course;
import model.CurrentCourse;
import model.Event;
import model.News;

import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private final int REQUEST_CODE_CONTACT = 1;
	private final int REQUEST_CODE_COURSE = 2;
	private final int REQUEST_CODE_EVENT = 3;
	private final int REQUEST_CODE_NEWS = 4;
	private Button contactButton, coursesButton, eventsButton, newsButton;
	private Context context;
	private Contact contact;
	private ArrayList<Course> courses = new ArrayList<Course>();
	private ArrayList<Event> events = new ArrayList<Event>();
	private ArrayList<News> news = new ArrayList<News>();

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
		events = diaryParser.getEvents();
		news = diaryParser.getNews();

		try {
			Toast.makeText(context, CreateXMLString(contact), Toast.LENGTH_LONG).show();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		Intent i;

		switch(v.getId()) {
		case R.id.buttonContact:
			i = new Intent(context, ContactActivity.class);
			i.putExtra("Contact", contact);
			startActivityForResult(i, REQUEST_CODE_CONTACT);
			break;
		case R.id.buttonCourses:
			i = new Intent(context, CourseListActivity.class);
			i.putParcelableArrayListExtra("Courses", courses);
			startActivityForResult(i, REQUEST_CODE_COURSE);
			break;
		case R.id.buttonEvents:
			i = new Intent(context, EventListActivity.class);
			i.putParcelableArrayListExtra("Events", events);
			startActivityForResult(i, REQUEST_CODE_EVENT);
			break;
		case R.id.buttonNews:
			i = new Intent(context, NewsListActivity.class);
			i.putParcelableArrayListExtra("News", news);
			startActivityForResult(i, REQUEST_CODE_NEWS);
			break;
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE_CONTACT && resultCode == RESULT_OK)
			contact = (Contact) data.getExtras().get("Contact");
		else if (requestCode == REQUEST_CODE_COURSE && resultCode == RESULT_OK)
			courses = data.getParcelableArrayListExtra("Courses");
		else if (requestCode == REQUEST_CODE_NEWS && resultCode == RESULT_OK)
			news = data.getParcelableArrayListExtra("News");
		super.onActivityResult(requestCode, resultCode, data);
	}

	private static String CreateXMLString(Contact contact) throws IllegalArgumentException, IllegalStateException, IOException {
		XmlSerializer xmlSerializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		xmlSerializer.setOutput(writer);

		xmlSerializer.startDocument("UTF-8", true);
		xmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);

		xmlSerializer.startTag("", "contact");
		xmlSerializer.attribute("", "name", contact.getName());
		xmlSerializer.attribute("", "email", contact.getName());
		xmlSerializer.attribute("", "office", contact.getName());
		xmlSerializer.attribute("", "office_hour", contact.getName());
		xmlSerializer.attribute("", "phone", contact.getName());
		xmlSerializer.attribute("", "position", contact.getName());
		for (CurrentCourse cc: contact.getCurrentCourses()) {
			xmlSerializer.startTag("", "course");
			xmlSerializer.attribute("", "name", cc.getName());
			xmlSerializer.attribute("", "CRN", cc.getCRN());
			xmlSerializer.endTag("", "course");
		}
		xmlSerializer.endTag("", "contact");
		xmlSerializer.endDocument();
		return writer.toString();
	}
}
