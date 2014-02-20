package com.example.diary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Contact;
import model.ContactBuilder;
import model.CurrentCourse;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;

public class DiaryXmlParser {

	private Context context;

	public DiaryXmlParser(Context _context) {
		context = _context;
	}

	public Contact parse(XmlPullParser parser) throws XmlPullParserException, IOException {
		return readItems(parser);
	}

	private Contact readItems(XmlPullParser parser) throws XmlPullParserException, IOException {
		while (parser.next() != XmlPullParser.END_DOCUMENT) {
			if (parser.getEventType() != XmlPullParser.START_TAG)
				continue;

			String name = parser.getName();
			if (name.equals("contact"))
				return readContact(parser);
		}
		return null;
	}

	private Contact readContact(XmlPullParser parser) throws XmlPullParserException, IOException {
		List<CurrentCourse> courses = new ArrayList<CurrentCourse>();

		Contact contact = ContactBuilder.contact()
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

			String name = parser.getName();
			if (name.equals("course"))
				courses.add(readCurrentCourseAttributes(parser));
		}
		contact.setCurrentCourses(courses);
		return contact;
	}

	private CurrentCourse readCurrentCourseAttributes(XmlPullParser parser) {
		return new CurrentCourse(parser.getAttributeValue(null, "name"), parser.getAttributeValue(null, "CRN"));
	}

}
