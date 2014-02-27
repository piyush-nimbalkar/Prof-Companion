package com.example.diary;

import model.Course;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class CourseActivity extends Activity {

	private final int REQUEST_EDIT = 1;
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

		textViewCourseCode.setText(course.getCourseNumber());
		textViewCourseName.setText(course.getCourseTitle());
		textViewCourseCredits.setText(course.getCreditHours());
		textViewCourseDays.setText(course.getDays());
		textViewCourseTime.setText(course.getTime());
	}

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.course_details_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.edit_course:
			Intent i = new Intent(context, EditCourseActivity.class);
			i.putExtra("Course", course);
			startActivityForResult(i, REQUEST_EDIT);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void finish() {
		Intent data = new Intent();
		data.putExtra("Course", course);
		data.putExtra("Position", coursePosition);
		setResult(RESULT_OK, data);
		super.finish();
	}

}
