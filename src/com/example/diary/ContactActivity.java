package com.example.diary;

import model.Contact;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ContactActivity extends Activity implements OnClickListener {

	private final int REQUEST_CODE1 = 1;
	final Context context = this;
	private Contact contact;
	private Button buttonEditContact;
	private TextView textViewName, textViewPosition, textViewEmail, textViewPhone;
	private TextView textViewOffice, textViewOfficeHours, textViewCourse1, textViewCourse2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);

		buttonEditContact = (Button) findViewById(R.id.buttonEdit);
		textViewName = (TextView) findViewById(R.id.textViewName);
		textViewPosition = (TextView) findViewById(R.id.textViewPosition);
		textViewEmail = (TextView) findViewById(R.id.textViewEmail);
		textViewPhone = (TextView) findViewById(R.id.textViewPhone);
		textViewOffice = (TextView) findViewById(R.id.textViewOffice);
		textViewOfficeHours = (TextView) findViewById(R.id.textViewOfficeHours);
		textViewCourse1 = (TextView) findViewById(R.id.labelEditContactCourse1);
		textViewCourse2 = (TextView) findViewById(R.id.labelEditContactCourse2);

		contact = (Contact) getIntent().getSerializableExtra("Contact");

		textViewName.setText(contact.getName());
		textViewPosition.setText(contact.getPosition());
		textViewEmail.setText(contact.getEmail());
		textViewPhone.setText(contact.getPhone());
		textViewOffice.setText(contact.getOffice());
		textViewOfficeHours.setText(contact.getOfficeHours());
		textViewCourse1.setText(courseInformation(contact, 0));
		textViewCourse2.setText(courseInformation(contact, 1));
		buttonEditContact.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.buttonEdit:
			Intent i = new Intent(context, EditContactActivity.class);
			i.putExtra("Contact", contact);
			startActivityForResult(i, REQUEST_CODE1);
			break;
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE1 && resultCode == RESULT_OK){
				contact = (Contact) data.getExtras().get("Contact");

				textViewName.setText(contact.getName());
				textViewPosition.setText(contact.getPosition());
				textViewEmail.setText(contact.getEmail());
				textViewPhone.setText(contact.getPhone());
				textViewOffice.setText(contact.getOffice());
				textViewOfficeHours.setText(contact.getOfficeHours());
				textViewCourse1.setText(courseInformation(contact, 0));
				textViewCourse2.setText(courseInformation(contact, 1));
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void finish() {
		Intent data = new Intent();
		data.putExtra("Contact", contact);
		setResult(RESULT_OK, data);
		super.finish();
	}

	private String courseInformation(Contact _contact, int index) {
		return _contact.getCurrentCourses().get(index).getName() + " (" + _contact.getCurrentCourses().get(index).getCRN() + ")";
	}

}
