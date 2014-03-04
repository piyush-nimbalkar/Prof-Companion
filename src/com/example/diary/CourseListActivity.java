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

/* An activity in which you can see a list of all the courses and returns
 * back the updated list of courses to the caller activity
 */
public class CourseListActivity extends Activity implements OnItemClickListener {

	private final int REQUEST_EDIT = 1;
	private final int RESULT_DELETED = 3;

	private Context context;
	private ArrayList<Course> courses = new ArrayList<Course>();
	private ListView listview;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_list);
		context = this;
		listview = (ListView) findViewById(R.id.listViewCourses);
		courses = getIntent().getParcelableArrayListExtra("Courses");

		/* Using an adaptor to show the list of courses in a custom layout
		 */
		final CourseArrayAdapter adapter = new CourseArrayAdapter(this, courses);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
	}

	/* On click of the item in the list, it will open an activity to view the course
	 * and pass the current course to it with its position in the list
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Course course = (Course) parent.getItemAtPosition(position);
		Intent i = new Intent(context, CourseActivity.class);
		i.putExtra("Course", course);
		i.putExtra("Position", position);
		startActivityForResult(i, REQUEST_EDIT);
	}

	/* Update the list of courses depending on the results from the returned activities
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		int position = data.getIntExtra("Position", 0);
		if (requestCode == REQUEST_EDIT && resultCode == RESULT_OK) {
			Course course = (Course) data.getExtras().get("Course");
			courses.remove(position);
			courses.add(position, course);
		} else if (requestCode == REQUEST_EDIT && resultCode == RESULT_DELETED) {
			/* In case of a delete request it remove the object from its position
			 */
			courses.remove(position);
		}
		final CourseArrayAdapter adapter_refresh = new CourseArrayAdapter(this, courses);
		listview.setAdapter(adapter_refresh);
		listview.setOnItemClickListener(this);
		super.onActivityResult(requestCode, resultCode, data);
	}

	/* Return the updated course object back to the caller activity
	 */
	public void finish() {
		Intent data = new Intent();
		data.putParcelableArrayListExtra("Courses", courses);
		setResult(RESULT_OK, data);
		super.finish();
	}

}
