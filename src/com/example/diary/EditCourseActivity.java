package com.example.diary;

import model.Course;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

/* An activity which facilitates editing of the course information and
 * returning it back to the caller Activity
 */
public class EditCourseActivity extends Activity {

	private Course course;

	private EditText editTextCourseCode;
	private EditText editTextCourseName;
	private EditText editTextCourseCredits;
	private EditText editTextCourseDays;
	private EditText editTextCourseTime;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_course);
		
		editTextCourseCode = (EditText) findViewById(R.id.editTextEditCourseNumber);
		editTextCourseName = (EditText) findViewById(R.id.editTextEditCourseName);
		editTextCourseCredits = (EditText) findViewById(R.id.editTextEditCourseCredits);
		editTextCourseDays = (EditText) findViewById(R.id.editTextEditCourseDays);
		editTextCourseTime = (EditText) findViewById(R.id.editTextEditCourseTime);
		
		course = (Course) getIntent().getParcelableExtra("Course");

		/* Fill out the course information on the screen from the received course object
		 */		
		editTextCourseCode.setText(course.getCourseNumber());
		editTextCourseName.setText(course.getCourseTitle());
		editTextCourseCredits.setText(course.getCreditHours());
		editTextCourseDays.setText(course.getDays());
		editTextCourseTime.setText(course.getTime());
	}

	/* Menu options for the current screen on the action bar.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.edit_course_menu, menu);
		return true;
	}

	/* Menu options on select callback for this screen. Currently, it only
	 * lets you edit the course
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.save_course:
			course.setCourseNumber(editTextCourseCode.getText().toString());
			course.setCourseTitle(editTextCourseName.getText().toString());
			course.setCreditHours(editTextCourseCredits.getText().toString());
			course.setDays(editTextCourseDays.getText().toString());
			course.setTime(editTextCourseTime.getText().toString());
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* Return the updated course object back to the caller activity
	 */
	public void finish() {
		Intent data = new Intent();
		data.putExtra("Course", course);
		setResult(RESULT_OK, data);
		super.finish();		
	}

}
