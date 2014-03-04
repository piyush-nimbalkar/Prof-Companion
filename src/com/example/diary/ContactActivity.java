package com.example.diary;

import model.Contact;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/* An activity to display the contact information of the person / professor
 */
public class ContactActivity extends Activity {

	private final int REQUEST_CODE1 = 1;

	final Context context = this;
	private Contact contact;
	private TextView textViewName, textViewPosition, textViewEmail, textViewPhone;
	private TextView textViewOffice, textViewOfficeHours, textViewCourse1, textViewCourse2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);

		textViewName = (TextView) findViewById(R.id.textViewName);
		textViewPosition = (TextView) findViewById(R.id.textViewPosition);
		textViewEmail = (TextView) findViewById(R.id.textViewEventNote);
		textViewPhone = (TextView) findViewById(R.id.textViewPhone);
		textViewOffice = (TextView) findViewById(R.id.textViewOffice);
		textViewOfficeHours = (TextView) findViewById(R.id.textViewOfficeHours);
		textViewCourse1 = (TextView) findViewById(R.id.labelEditContactCourse1);
		textViewCourse2 = (TextView) findViewById(R.id.labelEditContactCourse2);

		contact = (Contact) getIntent().getSerializableExtra("Contact");
		
		/* Fill in the contact information from the contact object that is passed
		 * from the main screen
		 */
		textViewName.setText(contact.getName());
		textViewPosition.setText(contact.getPosition());
		textViewEmail.setText(contact.getEmail());
		textViewPhone.setText(contact.getPhone());
		textViewOffice.setText(contact.getOffice());
		textViewOfficeHours.setText(contact.getOfficeHours());
		textViewCourse1.setText(courseInformation(contact, 0));
		textViewCourse2.setText(courseInformation(contact, 1));
	}

	/* Menu options for the current screen on the action bar.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.contact_details_menu, menu);
		return true;
	}

	/* Menu options on select callback for this screen. Currently, it only
	 * lets you edit the contact information
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.edit_contact:
			Intent i = new Intent(context, EditContactActivity.class);
			i.putExtra("Contact", contact);
			startActivityForResult(i, REQUEST_CODE1);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* Fill in the contact information from the contact object that is passed
	 * from edit contact activity i.e. the updated contact information. Also,
	 * store the updated contact object in the local contact object.
	 */
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

	/* Return the contact object back to the Main Activity in-case it has
	 * been modified in the work flow 
	 */
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
