package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CurrentCourse implements Serializable {

	private String name;
	private String CRN;

	public CurrentCourse(String _name, String _CRN) {
		name = _name;
		CRN = _CRN;
	}

	public String getName() {
		return name;
	}

	public String getCRN() {
		return CRN;
	}
}
