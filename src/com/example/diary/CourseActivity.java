package com.example.diary;

import model.Contact;
import model.Course;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CourseActivity extends Activity implements OnClickListener {

	private final int REQUEST_CODE1 = 1;
	final Context context = this;

	private Course course;
	private int coursePosition;

	private TextView textViewCourseCode;
	private TextView textViewCourseName;
	private TextView textViewCourseCredits;
	private TextView textViewCourseDays;
	private TextView textViewCourseTime;

	private Button buttonEditCourse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course);

		textViewCourseCode = (TextView) findViewById(R.id.textViewCourseNumber);
		textViewCourseName = (TextView) findViewById(R.id.textViewCourseName);
		textViewCourseCredits = (TextView) findViewById(R.id.textViewCourseCredits);
		textViewCourseDays = (TextView) findViewById(R.id.textViewCourseDays);
		textViewCourseTime = (TextView) findViewById(R.id.textViewCourseTime);
		buttonEditCourse = (Button) findViewById(R.id.buttonEditCourse);

		course = getIntent().getParcelableExtra("Course");
		coursePosition = getIntent().getIntExtra("Position", 0);

		textViewCourseCode.setText(course.getCourseNumber());
		textViewCourseName.setText(course.getCourseTitle());
		textViewCourseCredits.setText(course.getCreditHours());
		textViewCourseDays.setText(course.getDays());
		textViewCourseTime.setText(course.getTime());

		buttonEditCourse.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.buttonEditCourse:
			Intent i = new Intent(context, EditCourseActivity.class);
			i.putExtra("Course", course);
			startActivityForResult(i, REQUEST_CODE1);
			break;
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE1 && resultCode == RESULT_OK){
				course = (Course) data.getExtras().get("Course");

				textViewCourseCode.setText(course.getCourseNumber());
				textViewCourseName.setText(course.getCourseTitle());
				textViewCourseCredits.setText(course.getCreditHours());
				textViewCourseDays.setText(course.getDays());
				textViewCourseTime.setText(course.getCreditHours());
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	public void finish() {
		Intent data = new Intent();
		data.putExtra("Course", course);
		data.putExtra("Position", coursePosition);
		setResult(RESULT_OK, data);
		super.finish();
	}

}
