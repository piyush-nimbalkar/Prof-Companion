package com.example.diary;

import model.Course;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class EditCourseActivity extends Activity implements OnClickListener {

	private Course course;

	private EditText editTextCourseCode;
	private EditText editTextCourseName;
	private EditText editTextCourseCredits;
	private EditText editTextCourseDays;
	private EditText editTextCourseTime;

	private Button buttonEditCourseDone;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_course);
		
		editTextCourseCode = (EditText) findViewById(R.id.editTextEditCourseNumber);
		editTextCourseName = (EditText) findViewById(R.id.editTextEditCourseName);
		editTextCourseCredits = (EditText) findViewById(R.id.editTextEditCourseCredits);
		editTextCourseDays = (EditText) findViewById(R.id.editTextEditCourseDays);
		editTextCourseTime = (EditText) findViewById(R.id.editTextEditCourseTime);
		buttonEditCourseDone = (Button) findViewById(R.id.buttonEditCourseDone);
		
		course = (Course) getIntent().getParcelableExtra("Course");
		
		editTextCourseCode.setText(course.getCourseNumber());
		editTextCourseName.setText(course.getCourseTitle());
		editTextCourseCredits.setText(course.getCreditHours());
		editTextCourseDays.setText(course.getDays());
		editTextCourseTime.setText(course.getTime());
		
		buttonEditCourseDone.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.buttonEditCourseDone:
			course.setCourseNumber(editTextCourseCode.getText().toString());
			course.setCourseTitle(editTextCourseName.getText().toString());
			course.setCreditHours(editTextCourseCredits.getText().toString());
			course.setDays(editTextCourseDays.getText().toString());
			course.setTime(editTextCourseTime.getText().toString());
			finish();
			break;
		}
	}
	
	public void finish() {
		Intent data = new Intent();
		data.putExtra("Course", course);
		setResult(RESULT_OK, data);
		super.finish();		
	}
	
}
