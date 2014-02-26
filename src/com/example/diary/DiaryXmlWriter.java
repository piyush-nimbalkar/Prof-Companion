package com.example.diary;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

import model.Contact;
import model.Course;
import model.CurrentCourse;
import model.Event;
import model.News;

public class DiaryXmlWriter {

	private Contact contact;
	private ArrayList<Course> courses = new ArrayList<Course>();
	private ArrayList<Event> events = new ArrayList<Event>();
	private ArrayList<News> news = new ArrayList<News>();
	
	public DiaryXmlWriter(Contact contact, ArrayList<Course> courses, ArrayList<Event> events, ArrayList<News> news) {
		this.contact = contact;
		this.courses = courses;
		this.events = events;
		this.news = news;
	}

	public String write() throws IllegalArgumentException, IllegalStateException, IOException {
		XmlSerializer xmlSerializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		xmlSerializer.setOutput(writer);

		xmlSerializer.startDocument("UTF-8", true);

		xmlSerializer.startTag("", "items");
		xmlSerializer.startTag("", "contact");
		xmlSerializer.attribute("", "name", contact.getName());
		xmlSerializer.attribute("", "email", contact.getEmail());
		xmlSerializer.attribute("", "office", contact.getOffice());
		xmlSerializer.attribute("", "office_hour", contact.getOfficeHours());
		xmlSerializer.attribute("", "phone", contact.getPhone());
		xmlSerializer.attribute("", "position", contact.getPosition());
		for (CurrentCourse cc: contact.getCurrentCourses()) {
			xmlSerializer.startTag("", "course");
			xmlSerializer.attribute("", "name", cc.getName());
			xmlSerializer.attribute("", "CRN", cc.getCRN());
			xmlSerializer.endTag("", "course");
		}
		xmlSerializer.endTag("", "contact");
		xmlSerializer.endTag("", "items");
		xmlSerializer.endDocument();

		return writer.toString();
	}

}
