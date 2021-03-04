package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp;

public enum Navigations {
	LOGIN("views/login/login"),
	MASTER("views/master"),
	VACCINE("views/vaccine/vaccine"),
	VACCINE_FORM("views/vaccine/vaccine-form"),
	SUPPLIER("views/supplier/supplier"),
	SUPPLIER_FORM("views/supplier/supplier-form"),
	RECIPIENT("views/recipient");

	private String value;

	Navigations(String s) {
		this.value = s;
	}

	public String getValue() {
		return value;
	}

}
