package model;

import android.os.Parcel;
import android.os.Parcelable;

public class Course implements Parcelable {

	private String courseNumber;
	private String courseTitle;
	private String creditHours;
	private String days;
	private String time;

	public Course(String _number, String _title, String _hours, String _days, String _time) {
		courseNumber = _number;
		courseTitle = _title;
		creditHours = _hours;
		days = _days;
		time = _time;
	}

	public Course(Parcel in) {
		courseNumber = in.readString();
		courseTitle = in.readString();
		creditHours = in.readString();
		days = in.readString();
		time = in.readString();
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(courseNumber);
		dest.writeString(courseTitle);
		dest.writeString(creditHours);
		dest.writeString(days);
		dest.writeString(time);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static final Parcelable.Creator<Course> CREATOR = new Parcelable.Creator<Course>() {

		public Course createFromParcel(Parcel in) {
			return new Course(in);
		}

		public Course[] newArray(int size) {
			return new Course[size];
		}
	};

}
