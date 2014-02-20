package model;

public class ContactBuilder {
	public String type;
	public String name;
	public String position;
	public String email;
	public String phone;
	public String office;
	public String officeHour;

	public static ContactBuilder contact() {
		return new ContactBuilder();
	}

	public Contact build() {
		return new Contact(this);
	}

	public ContactBuilder setType(String _type) {
		type = _type;
		return this;
	}

	public ContactBuilder setName(String _name) {
		name = _name;
		return this;
	}

	public ContactBuilder setPosition(String _position) {
		position = _position;
		return this;
	}

	public ContactBuilder setEmail(String _email) {
		email = _email;
		return this;
	}

	public ContactBuilder setPhone(String _phone) {
		phone = _phone;
		return this;
	}

	public ContactBuilder setOffice(String _office) {
		office = _office;
		return this;
	}

	public ContactBuilder setOfficeHour(String _officeHour) {
		officeHour = _officeHour;
		return this;
	}

}
