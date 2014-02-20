package com.example.diary;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.Toast;

public class ContactActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);

		XmlResourceParser parser = getResources().getXml(R.xml.diary_data);
		DiaryXmlParser diaryParser = new DiaryXmlParser(this);
		try {
			diaryParser.parse(parser);
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
