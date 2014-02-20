package com.example.diary;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.widget.Toast;

public class DiaryXmlParser {

	private Context context;

	public DiaryXmlParser(Context _context) {
		context = _context;
	}

	public void parse(XmlPullParser parser) throws XmlPullParserException, IOException {
        readItems(parser);
	}

	private void readItems(XmlPullParser parser) throws XmlPullParserException, IOException {

		while (parser.next() != XmlPullParser.END_DOCUMENT) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();

			if (name.equals("contact")) {
				readContactAttributes(parser);
			}
		}
	}

	private void readContactAttributes(XmlPullParser parser) {
		Toast.makeText(context, "Attribute: " + parser.getAttributeValue(null, "type"), Toast.LENGTH_SHORT).show();
		Toast.makeText(context, "Attribute: " + parser.getAttributeValue(null, "name"), Toast.LENGTH_SHORT).show();
		Toast.makeText(context, "Attribute: " + parser.getAttributeValue(null, "position"), Toast.LENGTH_SHORT).show();
		Toast.makeText(context, "Attribute: " + parser.getAttributeValue(null, "office"), Toast.LENGTH_SHORT).show();
		Toast.makeText(context, "Attribute: " + parser.getAttributeValue(null, "email"), Toast.LENGTH_SHORT).show();
		Toast.makeText(context, "Attribute: " + parser.getAttributeValue(null, "phone"), Toast.LENGTH_SHORT).show();
		Toast.makeText(context, "Attribute: " + parser.getAttributeValue(null, "office_hour"), Toast.LENGTH_SHORT).show();
		Toast.makeText(context, "Attribute: " + parser.getAttributeCount(), Toast.LENGTH_SHORT).show();
	}

}
