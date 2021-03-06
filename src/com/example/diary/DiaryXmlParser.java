package com.example.diary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Contact;
import model.ContactBuilder;
import model.Course;
import model.CurrentCourse;
import model.Event;
import model.News;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;

/* Xml parser for the given format of files which returns an object
 * containing the parsed objects of Contact, Course, Event and News
 */
public class DiaryXmlParser {

	private Context context;
	private Contact contact;
	private ArrayList<Course> courses = new ArrayList<Course>();
	private ArrayList<Event> events = new ArrayList<Event>();
	private ArrayList<News> news = new ArrayList<News>();

	public DiaryXmlParser(Context _context) {
		context = _context;
	}

	public void parse(XmlPullParser parser) throws XmlPullParserException, IOException {
		readItems(parser);
	}

	public Contact getContact() {
		return contact;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	public ArrayList<News> getNews() {
		return news;
	}

	private void readItems(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.next();
		while (parser.next() != XmlPullParser.END_DOCUMENT) {
			if (parser.getEventType() != XmlPullParser.START_TAG)
				continue;
			if (parser.getName().equals("contact"))
				readContact(parser);
			if (parser.getName().equals("courses"))
				courses.add(readCourse(parser));
			if (parser.getName().equals("events"))
				events.add(readEvent(parser));
			if (parser.getName().equals("news"))
				news.add(readNews(parser));
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

		/* Parse the current courses from the contact information
		 */
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

	private Event readEvent(XmlPullParser parser) {
		return new Event(parser.getAttributeValue(null, "type"),
				parser.getAttributeValue(null, "time"),
				parser.getAttributeValue(null, "day"),
				parser.getAttributeValue(null, "note"));
	}

	private News readNews(XmlPullParser parser) {
		return new News(parser.getAttributeValue(null, "title"),
				parser.getAttributeValue(null, "keyword"),
				parser.getAttributeValue(null, "highlights"));
	}

}
