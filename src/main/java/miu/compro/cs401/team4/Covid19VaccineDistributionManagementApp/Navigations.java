package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp;

public enum Navigations {
	LOGIN("views/login/login"),
	MASTER("views/master"),
	VACCINE("views/vaccine/vaccine"),
	RECIPIENT("views/recipient");

	private String value;

	Navigations(String s) {
		this.value = s;
	}

	public String getValue() {
		return value;
	}

}
