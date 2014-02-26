package com.example.diary;

import java.util.ArrayList;

import model.Contact;
import model.Course;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CourseActivity extends Activity implements android.view.View.OnClickListener{

//	private ArrayList<Course> courses = new ArrayList<Course>();
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
		coursePosition = getIntent().getIntExtra("Position", 1);

		//Toast.makeText(this, course.getCourseNumber(), Toast.LENGTH_LONG).show();
		textViewCourseCode.setText(course.getCourseNumber());
		textViewCourseName.setText(course.getCourseName());
		textViewCourseCredits.setText(course.getCreditHours());
		textViewCourseDays.setText(course.getDays());
		textViewCourseTime.setText(course.getTime());
		
		buttonEditCourse.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		
		switch(v.getId()) {
		case R.id.buttonEditCourse:
			Intent i = new Intent(context, EditContactActivity.class);
			i.putExtra("Course", course);
			startActivityForResult(i, REQUEST_CODE1);
			break;
		}
	}

	public void finish() {
		Intent data = new Intent();
		data.putExtra("Course", course);
		data.putExtra("Position", coursePosition);
		setResult(RESULT_OK, data);
		super.finish();

}
