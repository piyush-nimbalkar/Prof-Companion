package com.example.diary;

import java.util.ArrayList;
import java.util.List;

import model.Contact;
import model.CurrentCourse;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.widget.EditText;

/* An activity which facilitates editing of the contact information and
 * returning it back to the caller Activity
 */
public class EditContactActivity extends Activity {

	private Contact contact;
	private EditText editName, editPosition, editEmail, editPhone, editOffice, editOfficeHours;
	private EditText editCourse1, editCourseNo1, editCourse2, editCourseNo2;

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
	}

	/* Menu options for the current screen on the action bar.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.edit_contact_menu, menu);
		return true;
	}

	/* Menu option on select callback for the save action button on this screen. 
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.save_contact:
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
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* Return the updated contact object back to the caller activity
	 */
	public void finish() {
		Intent data = new Intent();
		data.putExtra("Contact", contact);
		setResult(RESULT_OK, data);
		super.finish();		
	}

}
