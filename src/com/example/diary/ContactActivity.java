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

	final Context context = this;
	private Contact contact;
	private Button buttonEditContact;
	private TextView textViewName, textViewPosition, textViewEmail, textViewPhone, textViewOffice, textViewOfficeHours, textViewCourse1;

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
		textViewCourse1 = (TextView) findViewById(R.id.TextViewCourse1);

		contact = (Contact) getIntent().getSerializableExtra("Contact");

		textViewName.setText(contact.getName());
		textViewPosition.setText(contact.getPosition());
		textViewEmail.setText(contact.getEmail());
		textViewPhone.setText(contact.getPhone());
		textViewOffice.setText(contact.getOffice());
		textViewOfficeHours.setText(contact.getOfficeHour());

		buttonEditContact.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.buttonEdit:
			Intent i = new Intent(context, EditContactActivity.class);
			i.putExtra("Contact", contact);
			startActivity(i);
			break;
		}
	}

}
