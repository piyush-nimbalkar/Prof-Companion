package com.example.diary;

import model.Course;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/* An activity to display the course information
 */
public class CourseActivity extends Activity {

	private final int REQUEST_EDIT = 1;
	private final int RESULT_DELETED = 3;

	final Context context = this;

	private Course course;
	private int coursePosition;

	private TextView textViewCourseCode;
	private TextView textViewCourseName;
	private TextView textViewCourseCredits;
	private TextView textViewCourseDays;
	private TextView textViewCourseTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course);

		textViewCourseCode = (TextView) findViewById(R.id.textViewCourseNumber);
		textViewCourseName = (TextView) findViewById(R.id.textViewCourseName);
		textViewCourseCredits = (TextView) findViewById(R.id.textViewCourseCredits);
		textViewCourseDays = (TextView) findViewById(R.id.textViewCourseDays);
		textViewCourseTime = (TextView) findViewById(R.id.textViewCourseTime);

		course = getIntent().getParcelableExtra("Course");
		coursePosition = getIntent().getIntExtra("Position", 0);

		/* Fill out the course information on the screen from the received course object
		 */
		textViewCourseCode.setText(course.getCourseNumber());
		textViewCourseName.setText(course.getCourseTitle());
		textViewCourseCredits.setText(course.getCreditHours());
		textViewCourseDays.setText(course.getDays());
		textViewCourseTime.setText(course.getTime());
	}

	/* Fill in the course information from the course object that is passed
	 * from edit course activity i.e. the updated course information. Also,
	 * store the updated course object in the local course object.
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_EDIT && resultCode == RESULT_OK){
				course = (Course) data.getExtras().get("Course");
				textViewCourseCode.setText(course.getCourseNumber());
				textViewCourseName.setText(course.getCourseTitle());
				textViewCourseCredits.setText(course.getCreditHours());
				textViewCourseDays.setText(course.getDays());
				textViewCourseTime.setText(course.getCreditHours());
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/* Menu options for the current screen on the action bar.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.course_details_menu, menu);
		return true;
	}

	/* Menu options on select callback for this screen. Currently, it only
	 * lets you edit or delete the course
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.edit_course:
			Intent i = new Intent(context, EditCourseActivity.class);
			i.putExtra("Course", course);
			startActivityForResult(i, REQUEST_EDIT);
			return true;
		case R.id.delete_course:
			course = null;
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* Return the course object and its position in the list of course objects
	 * back to the caller activity in-case it has been modified in the work flow 
	 */
	public void finish() {
		Intent data = new Intent();
		data.putExtra("Course", course);
		data.putExtra("Position", coursePosition);
		int resultCode = (course == null) ? RESULT_DELETED : RESULT_OK;
		setResult(resultCode, data);
		super.finish();
	}

}
