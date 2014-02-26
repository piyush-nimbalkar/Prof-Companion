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

	private final int REQUEST_CODE1 = 1;
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
		i.putExtra("Position", position);
		startActivityForResult(i, REQUEST_CODE1);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE1 && resultCode == RESULT_OK){
			Course course = (Course) data.getExtras().get("Course");
			int position = data.getIntExtra("Position", 0);
			courses.remove(position);
			courses.add(position, course);
			
		}
		final CourseArrayAdaptor adapter_refresh = new CourseArrayAdaptor(this, courses);
		listview.setAdapter(adapter_refresh);
		listview.setOnItemClickListener(this);
		
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void finish() {
		Intent data = new Intent();
		data.putParcelableArrayListExtra("Courses", courses);
		setResult(RESULT_OK, data);
		super.finish();
	}

}
