package model;

public class Course {

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

}
