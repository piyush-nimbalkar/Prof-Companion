package com.example.diary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Course;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CourseListActivity extends Activity {

	private ArrayList<Course> courses = new ArrayList<Course>();
	private ListView listview;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_list);

		listview = (ListView) findViewById(R.id.listViewCourses);

		courses = getIntent().getParcelableArrayListExtra("Courses");

		final CourseArrayAdaptor adapter = new CourseArrayAdaptor(this, courses);
		listview.setAdapter(adapter);
	}

}
