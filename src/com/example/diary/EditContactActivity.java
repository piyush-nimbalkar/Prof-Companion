package com.example.diary;

import model.Contact;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditContactActivity extends Activity implements OnClickListener {

	private Contact contact;
	private EditText editName, editPosition, editEmail, editPhone, editOffice, editOfficeHours, editCourse1;
	private Button doneEditing;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_contact);
		
		editName = (EditText) findViewById(R.id.editTextName);
		editPosition = (EditText) findViewById(R.id.editTextPosition);
		editEmail = (EditText) findViewById(R.id.editTextEmail);
		editPhone = (EditText) findViewById(R.id.editTextPhone);
		editOffice = (EditText) findViewById(R.id.editTextOffice);
		editOfficeHours = (EditText) findViewById(R.id.editTextOfficeHours);
		editCourse1 = (EditText) findViewById(R.id.editTextCourse1);
		doneEditing = (Button) findViewById(R.id.buttonDone);
		
		contact = (Contact) getIntent().getSerializableExtra("Contact");
		Toast.makeText(this, contact.getEmail(), Toast.LENGTH_SHORT).show();
		
		//setInfo(contact);

		editName.setText(contact.getName());
		editPosition.setText(contact.getPosition());
		editEmail.setText(contact.getEmail());
		editPhone.setText(contact.getPhone());
		editOffice.setText(contact.getOffice());
		editOfficeHours.setText(contact.getOfficeHours());
		//editName.setText(contact.getCourse1());
		
		doneEditing.setOnClickListener(this);
	}

	public void onClick(View v) {
		contact.setName(editName.getText().toString());
		contact.setPosition(editPosition.getText().toString());
		contact.setEmail(editEmail.getText().toString());
		contact.setPhone(editPhone.getText().toString());
		contact.setOffice(editOffice.getText().toString());
		contact.setOfficeHours(editOfficeHours.getText().toString());
		//contact.setName(editCourse.getText());
		finish();
	}
	
	public void finish() {
		Intent data = new Intent();
		data.putExtra("Contact", contact);
		setResult(RESULT_OK, data);
		super.finish();		
	}

}
