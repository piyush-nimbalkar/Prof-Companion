package com.example.diary;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ContactActivity extends Activity {

	private ImageButton editName, editPosition, editEmail, editPhone, editOffice, editOfficeHours, editCourse1;
	private TextView textViewName, textViewPosition, textViewEmail, textViewPhone, textViewOffice, textViewOfficeHours, textViewCourse1;

	final Context context = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);

		textViewName = (TextView) findViewById(R.id.textViewName);
		textViewPosition = (TextView) findViewById(R.id.TextViewPosition);
		textViewEmail = (TextView) findViewById(R.id.TextViewEmail);
		textViewPhone = (TextView) findViewById(R.id.TextViewPhone);
		textViewOffice = (TextView) findViewById(R.id.TextViewOffice);
		textViewOfficeHours = (TextView) findViewById(R.id.TextViewOffice);
		textViewCourse1 = (TextView) findViewById(R.id.TextViewCourse1);

		editName = (ImageButton)findViewById(R.id.buttonEditName);
		editPosition = (ImageButton)findViewById(R.id.buttonEditPosition);
		editEmail = (ImageButton)findViewById(R.id.buttonEditEmail);
		editPhone = (ImageButton)findViewById(R.id.buttonEditPhone);
		editOffice = (ImageButton)findViewById(R.id.buttonEditOffice);
		editOfficeHours = (ImageButton)findViewById(R.id.buttonEditOfficeHours);
		editCourse1 = (ImageButton)findViewById(R.id.buttonEditCourse1);

		XmlResourceParser parser = getResources().getXml(R.xml.diary_data);
		DiaryXmlParser diaryParser = new DiaryXmlParser(this);
		try {
			diaryParser.parse(parser);
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		editName.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				//get edit_name.xml view
				LayoutInflater layoutInflater = LayoutInflater.from(context);
				View promptView = layoutInflater.inflate(R.layout.dialog_edit_name, null);

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

				// set edit_name.xml to be the layout file of the alertdialog builder
				alertDialogBuilder.setView(promptView);
				final EditText input = (EditText) promptView.findViewById(R.id.userInput);

				// setup a dialog window
				alertDialogBuilder
				.setCancelable(true)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// get user input and set it to result
						textViewName.setText(input.getText());
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
				//create alert dialog
				AlertDialog alertD = alertDialogBuilder.create();
				alertD.show();
				return false;
			}
		});
	}
}
