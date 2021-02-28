package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp;

public enum ChildView {
	VACCINE("views/vaccine"), RECIPIENT("views/recipient");

	private String value;

	ChildView(String s) {
		this.value = s;
	}

	public String getValue() {
		return value;
	}

}
