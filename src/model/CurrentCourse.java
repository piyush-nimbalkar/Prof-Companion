package model;

public class CurrentCourse {

	private String name;
	private String CRN;

	public CurrentCourse(String _name, String _CRN) {
		name = _name;
		CRN = _CRN;
	}

	public String getName() {
		return name;
	}

}
