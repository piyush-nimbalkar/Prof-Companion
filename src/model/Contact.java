package model;

public class Contact {
	private String type, name, position, email, phone, office, officeHour;

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

	public String getOfficeHour() {
		return officeHour;
	}

}
