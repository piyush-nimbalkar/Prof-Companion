package com.example.diary;

import model.Contact;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class EditContactActivity extends Activity {

	private Contact contact;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_contact);
		
		contact = (Contact) getIntent().getSerializableExtra("Contact");
		Toast.makeText(this, contact.getEmail(), Toast.LENGTH_SHORT).show();
	}

}
