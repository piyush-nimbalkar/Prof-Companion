package com.example.diary;

import java.util.ArrayList;

import model.Course;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CourseListActivity extends Activity implements OnItemClickListener {

	private Context context;
	private ArrayList<Course> courses = new ArrayList<Course>();
	private ListView listview;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_list);
		context = this;
		listview = (ListView) findViewById(R.id.listViewCourses);
		courses = getIntent().getParcelableArrayListExtra("Courses");

		final CourseArrayAdaptor adapter = new CourseArrayAdaptor(this, courses);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Course course = (Course) parent.getItemAtPosition(position);
		Intent i = new Intent(context, CourseActivity.class);
		i.putExtra("Course", course);
		startActivity(i);
	}

}
