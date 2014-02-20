package com.example.diary;

import java.io.IOException;

import model.Contact;

import org.xmlpull.v1.XmlPullParserException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ContactActivity extends Activity implements OnClickListener {

	private Button editContact; //editPosition, editEmail, editPhone, editOffice, editOfficeHours, editCourse1;
	private TextView update, textViewName, textViewPosition, textViewEmail, textViewPhone, textViewOffice, textViewOfficeHours, textViewCourse1;

	final Context context = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);

		//creating objects for UI elements
		editContact = (Button) findViewById(R.id.buttonEdit);
		textViewName = (TextView) findViewById(R.id.TextViewName);
		textViewPosition = (TextView) findViewById(R.id.TextViewPosition);
		textViewEmail = (TextView) findViewById(R.id.TextViewEmail);
		textViewPhone = (TextView) findViewById(R.id.TextViewPhone);
		textViewOffice = (TextView) findViewById(R.id.TextViewOffice);
		textViewOfficeHours = (TextView) findViewById(R.id.textViewOfficeHours);
		textViewCourse1 = (TextView) findViewById(R.id.TextViewCourse1);

		//parsing xml data
		XmlResourceParser parser = getResources().getXml(R.xml.diary_data);
		DiaryXmlParser diaryParser = new DiaryXmlParser(this);
		Contact c = null;

		try {
			c = diaryParser.parse(parser);
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		textViewName.setText(c.getName());
		textViewPosition.setText(c.getPosition());
		textViewEmail.setText(c.getEmail());
		textViewPhone.setText(c.getPhone());
		textViewOffice.setText(c.getOffice());
		textViewOfficeHours.setText(c.getOfficeHour());
		//textViewOfficeHours.setText(c.getOfficeHour());
	}	
	
/*	@Override
	public boolean onTouch(View v, MotionEvent event) {

		final EditText input;
		TextView title;

		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View promptView = layoutInflater.inflate(R.layout.dialog_edit_name, null);

		switch(v.getId()) {
		case R.id.buttonEditName:
			title = (TextView) promptView.findViewById(R.id.textViewDialog);
			title.setText("Edit Name");
			update = (TextView) findViewById(R.id.TextViewName);
			break;
		case R.id.buttonEditPosition:
			title = (TextView) promptView.findViewById(R.id.textViewDialog);
			title.setText("Edit Position");
			update = (TextView) findViewById(R.id.TextViewPosition);
			break;
		case R.id.buttonEditEmail:
			title = (TextView) promptView.findViewById(R.id.textViewDialog);
			title.setText("Edit Email");
			update = (TextView) findViewById(R.id.TextViewEmail);
			break;
		case R.id.buttonEditPhone:
			title = (TextView) promptView.findViewById(R.id.textViewDialog);
			title.setText("Edit Phone");
			update = (TextView) findViewById(R.id.TextViewPhone);
			break;
		case R.id.buttonEditOffice:
			title = (TextView) promptView.findViewById(R.id.textViewDialog);
			title.setText("Edit Office");
			update = (TextView) findViewById(R.id.TextViewOffice);
			break;
		case R.id.buttonEditOfficeHours:
			title = (TextView) promptView.findViewById(R.id.textViewDialog);
			title.setText("Edit Office Hours");
			update = (TextView) findViewById(R.id.TextViewOfficeHours);
			break;
		case R.id.buttonEditCourse1:
			title = (TextView) promptView.findViewById(R.id.textViewDialog);
			title.setText("Edit Course 1");
			update = (TextView) findViewById(R.id.TextViewCourse1);
			break;
		}

		input = (EditText) promptView.findViewById(R.id.userInputDialog);

		//get edit_name.xml view
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

		// set edit_name.xml to be the layout file of the alertdialog builder
		alertDialogBuilder.setView(promptView);

		// setup a dialog window
		alertDialogBuilder
		.setCancelable(true)
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// get user input and set it to result
				update.setText(input.getText());
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
*/
	@Override
	public void onClick(DialogInterface dialog, int which) {
		
		
	}
}
