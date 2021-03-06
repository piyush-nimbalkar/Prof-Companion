package com.example.diary;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import model.Contact;
import model.Course;
import model.Event;
import model.News;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/* The activity linked to the main screen of the application. It allow navigation
 * to the four features and also reads and writes the XML file used for data storage
 */
public class MainActivity extends Activity implements OnClickListener {

	private final int REQUEST_CODE_CONTACT = 1;
	private final int REQUEST_CODE_COURSE = 2;
	private final int REQUEST_CODE_EVENT = 3;
	private final int REQUEST_CODE_NEWS = 4;
	private Button contactButton, coursesButton, eventsButton, newsButton;
	private Context context;
	private String storedXmlFile = "diary_data.xml";

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

		/* Get the parser depending on the input type. If there is an existing
		 * XML file in the private storage of the application, then read that file,
		 * else read the file from the res folder.
		 */
		DiaryXmlParser diaryParser = new DiaryXmlParser(this);
		XmlPullParser parser = getXmlParser();

		try {
			diaryParser.parse(parser);
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* Get the parsed objects from the DiaryXmlParser
		 */
		contact = diaryParser.getContact();
		courses = diaryParser.getCourses();
		events = diaryParser.getEvents();
		news = diaryParser.getNews();
	}

	private XmlPullParser getXmlParser() {
		File file = getBaseContext().getFileStreamPath(storedXmlFile);
		XmlPullParser parser = null;

		if (!file.exists())
			parser = getResources().getXml(R.xml.diary_data);
		else
			parser = getParserForStoredFile();
		return parser;
	}

	/* On click of the buttons on the home screen they should open
	 * respective activities with the corresponding object passed to it.
	 */
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

	/* Store back the objects received from the opened activities as they
	 * might have been changed in the work flow.
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE_CONTACT && resultCode == RESULT_OK)
			contact = (Contact) data.getExtras().get("Contact");
		else if (requestCode == REQUEST_CODE_COURSE && resultCode == RESULT_OK)
			courses = data.getParcelableArrayListExtra("Courses");
		else if (requestCode == REQUEST_CODE_EVENT && resultCode == RESULT_OK)
			events = data.getParcelableArrayListExtra("Events");
		else if (requestCode == REQUEST_CODE_NEWS && resultCode == RESULT_OK)
			news = data.getParcelableArrayListExtra("News");
		super.onActivityResult(requestCode, resultCode, data);
	}

	/* When the Main Activity is closed, which means if the application is closed,
	 * it will store the objects in an XML file in the private internal storage.
	 */
	@Override
	public void finish() {
		DiaryXmlWriter writer = new DiaryXmlWriter(contact, courses, events, news);
		try {
			String string = writer.write();
			FileOutputStream fos;
			try {
				fos = openFileOutput(storedXmlFile, Context.MODE_PRIVATE);
				fos.write(string.getBytes());
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		super.finish();
	}

	/* Create and return a new XmlPullParser object for the stored file
	 */
	private XmlPullParser getParserForStoredFile() {
		XmlPullParser parser = null;
		InputStream in = readStoredXmlFile();

		try {
			parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(in, null);
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return parser;
	}

	/* Read and return the contents of the stored XML file
	 */
	private InputStream readStoredXmlFile() {
		FileInputStream fin;
		StringBuffer fileContent = new StringBuffer("");
		byte[] buffer = new byte[1024];

		try {
			fin = openFileInput(storedXmlFile);
			while (fin.read(buffer) != -1) {
				fileContent.append(new String(buffer));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		InputStream in = new ByteArrayInputStream(fileContent.toString().getBytes());
		return in;
	}

}
