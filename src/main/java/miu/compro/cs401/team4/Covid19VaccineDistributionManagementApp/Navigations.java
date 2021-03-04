package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp;

public enum Navigations {
	LOGIN("views/login/login"),
	MASTER("views/master"),
	VACCINE("views/vaccine/vaccine"),
	VACCINE_FORM("views/vaccine/vaccine-form"),
	SUPPLIER("views/supplier/supplier"),
	SUPPLIER_FORM("views/supplier/supplier-form"),
	VACCINATION_SITE("views/vaccinationsite/vaccination-site"),
	VACCINATION_SITE_FORM("views/vaccinationsite/vaccination-site-form"),
	RECIPIENT("views/recipient/recipient"),
	CANDIDATE("views/candidate/candidate"),
	REGISTER_CANDIDATE("views/candidate/register-candidate"),
	CHECK_CANDIDATE_STATUS("views/candidate/check-candidate-status");

	private String value;

	Navigations(String s) {
		this.value = s;
	}

	public String getValue() {
		return value;
	}

}
