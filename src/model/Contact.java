package model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Contact implements Serializable {
	private String type, name, position, email, phone, office, officeHour;
	private List<CurrentCourse> currentCourses;

	public Contact(ContactBuilder builder) {
		type = builder.type;
		name = builder.name;
		position = builder.position;
		email = builder.email;
		phone = builder.phone;
		office = builder.office;
		officeHour = builder.officeHour;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getPosition() {
		return position;
	}

	public String getOffice() {
		return office;
	}

	public String getOfficeHours() {
		return officeHour;
	}

	public List<CurrentCourse> getCurrentCourses() {
		return currentCourses;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public void setOfficeHours(String officehours) {
		officeHour = officehours;
	}

	public void setCurrentCourses(List<CurrentCourse> courseList) {
		currentCourses = courseList;
	}

}
