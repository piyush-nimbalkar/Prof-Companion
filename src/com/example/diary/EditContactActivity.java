package com.example.diary;

import java.util.ArrayList;
import java.util.List;

import model.Contact;
import model.CurrentCourse;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditContactActivity extends Activity implements OnClickListener {

	private Contact contact;
	private EditText editName, editPosition, editEmail, editPhone, editOffice, editOfficeHours;
	private EditText editCourse1, editCourseNo1, editCourse2, editCourseNo2;
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
		editCourseNo1 = (EditText) findViewById(R.id.editTextCourseNo1);
		editCourse2 = (EditText) findViewById(R.id.editTextCourse2);
		editCourseNo2 = (EditText) findViewById(R.id.editTextCourseNo2);
		doneEditing = (Button) findViewById(R.id.buttonDone);
		
		contact = (Contact) getIntent().getSerializableExtra("Contact");
		
		editName.setText(contact.getName());
		editPosition.setText(contact.getPosition());
		editEmail.setText(contact.getEmail());
		editPhone.setText(contact.getPhone());
		editOffice.setText(contact.getOffice());
		editOfficeHours.setText(contact.getOfficeHours());
		editCourse1.setText(contact.getCurrentCourses().get(0).getName());
		editCourseNo1.setText(contact.getCurrentCourses().get(0).getCRN());
		editCourse2.setText(contact.getCurrentCourses().get(1).getName());
		editCourseNo2.setText(contact.getCurrentCourses().get(1).getCRN());
		
		doneEditing.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.buttonDone:
			contact.setName(editName.getText().toString());
			contact.setPosition(editPosition.getText().toString());
			contact.setEmail(editEmail.getText().toString());
			contact.setPhone(editPhone.getText().toString());
			contact.setOffice(editOffice.getText().toString());
			contact.setOfficeHours(editOfficeHours.getText().toString());
			List<CurrentCourse> courses = new ArrayList<CurrentCourse>();
			courses.add(new CurrentCourse(editCourse1.getText().toString(), editCourseNo1.getText().toString()));
			courses.add(new CurrentCourse(editCourse2.getText().toString(), editCourseNo2.getText().toString()));
			contact.setCurrentCourses(courses);
			finish();
			break;
		}
	}
	
	public void finish() {
		Intent data = new Intent();
		data.putExtra("Contact", contact);
		setResult(RESULT_OK, data);
		super.finish();		
	}

}
