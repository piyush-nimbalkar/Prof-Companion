package com.example.diary;

import java.util.List;

import model.Course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/* Custom Array Adaptor to show the list of courses
 */
public class CourseArrayAdapter extends ArrayAdapter<Course>{
	private final Context context;
	private final List<Course> values;

	public CourseArrayAdapter(Context context, List<Course> values) {
		super(context, R.layout.course_row_layout, values);
		this.context = context;
		this.values = values;
	}

	/* Override the method to return a custom view which will fill out the course
	 * information from the passed course list to the adaptor
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.course_row_layout, parent, false);
		TextView textViewTitle = (TextView) rowView.findViewById(R.id.textViewRowCourseTitle);
		TextView textViewDays = (TextView) rowView.findViewById(R.id.textViewRowDays);
		textViewTitle.setText(values.get(position).getCourseNumber() + ": " + values.get(position).getCourseTitle());
		textViewDays.setText(values.get(position).getDays());
		return rowView;
	}

}
