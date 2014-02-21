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
import android.widget.Toast;

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
		textViewName = (TextView) findViewById(R.id.TextViewName);
		textViewPosition = (TextView) findViewById(R.id.TextViewPosition);
		textViewEmail = (TextView) findViewById(R.id.TextViewEmail);
		textViewPhone = (TextView) findViewById(R.id.TextViewPhone);
		textViewOffice = (TextView) findViewById(R.id.TextViewOffice);
		textViewOfficeHours = (TextView) findViewById(R.id.textViewOfficeHours);
		textViewCourse1 = (TextView) findViewById(R.id.textViewCourse1);
		textViewCourse2 = (TextView) findViewById(R.id.textViewCourse2);

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
				Contact updatedContact = (Contact) data.getExtras().get("Contact");
				textViewName.setText(updatedContact.getName());
				textViewPosition.setText(updatedContact.getPosition());
				textViewEmail.setText(updatedContact.getEmail());
				textViewPhone.setText(updatedContact.getPhone());
				textViewOffice.setText(updatedContact.getOffice());
				textViewOfficeHours.setText(updatedContact.getOfficeHours());
				textViewCourse1.setText(courseInformation(updatedContact, 0));
				textViewCourse2.setText(courseInformation(updatedContact, 1));
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private String courseInformation(Contact _contact, int index) {
		return _contact.getCurrentCourses().get(index).getName() + " (" + _contact.getCurrentCourses().get(index).getCRN() + ")";
	}
}
