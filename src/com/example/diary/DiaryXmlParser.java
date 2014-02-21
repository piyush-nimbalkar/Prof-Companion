package com.example.diary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Contact;
import model.ContactBuilder;
import model.Course;
import model.CurrentCourse;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.widget.Toast;

public class DiaryXmlParser {

	private Context context;
	private Contact contact;
	private List<Course> courses = new ArrayList<Course>();

	public DiaryXmlParser(Context _context) {
		context = _context;
	}

	public void parse(XmlPullParser parser) throws XmlPullParserException, IOException {
		readItems(parser);
	}

	public Contact getContact() {
		return contact;
	}

	private void readItems(XmlPullParser parser) throws XmlPullParserException, IOException {
		while (parser.next() != XmlPullParser.END_DOCUMENT) {
			if (parser.getEventType() != XmlPullParser.START_TAG)
				continue;
			if (parser.getName().equals("contact"))
				readContact(parser);
			if (parser.getName().equals("courses"))
				courses.add(readCourse(parser));
		}
	}

	private void readContact(XmlPullParser parser) throws XmlPullParserException, IOException {
		List<CurrentCourse> currentCourses = new ArrayList<CurrentCourse>();

		contact = ContactBuilder.contact()
				.setType(parser.getAttributeValue(null, "type"))
				.setName(parser.getAttributeValue(null, "name"))
				.setPosition(parser.getAttributeValue(null, "position"))
				.setEmail(parser.getAttributeValue(null, "email"))
				.setPhone(parser.getAttributeValue(null, "phone"))
				.setOffice(parser.getAttributeValue(null, "office"))
				.setOfficeHour(parser.getAttributeValue(null, "office_hour"))
				.build();

		while (parser.next() != XmlPullParser.END_DOCUMENT) {
			if (parser.getEventType() != XmlPullParser.START_TAG)
				continue;
			if (!parser.getName().equals("course"))
				break;
			currentCourses.add(readCurrentCourseAttributes(parser));
		}
		contact.setCurrentCourses(currentCourses);
	}

	private CurrentCourse readCurrentCourseAttributes(XmlPullParser parser) {
		return new CurrentCourse(parser.getAttributeValue(null, "name"), parser.getAttributeValue(null, "CRN"));
	}

	private Course readCourse(XmlPullParser parser) {
		return new Course(parser.getAttributeValue(null, "CN"),
				parser.getAttributeValue(null, "courseTitle"),
				parser.getAttributeValue(null, "creditHours"),
				parser.getAttributeValue(null, "Days"),
				parser.getAttributeValue(null, "Time"));
	}
}
