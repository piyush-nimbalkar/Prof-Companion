package com.example.diary;

import java.util.ArrayList;

import model.Course;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class CourseActivity extends Activity {

	private ArrayList<Course> courses = new ArrayList<Course>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course);

		courses = getIntent().getParcelableArrayListExtra("Courses");

		Toast.makeText(this, courses.get(0).getCourseNumber(), Toast.LENGTH_LONG).show();
	}

}
