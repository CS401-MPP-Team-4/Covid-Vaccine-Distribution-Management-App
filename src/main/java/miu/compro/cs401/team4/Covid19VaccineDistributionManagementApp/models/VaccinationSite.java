package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models;

public class VaccinationSite {
	private Integer id;
	private String name;
	private String city;
	private String state;
	private String zipCode;

	// START Constructors
	public VaccinationSite() { }

	public VaccinationSite(Integer id, String name, String city, String state, String zipCode) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	// END Constructors

	// START Getters
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getZipCode() {
		return zipCode;
	}
	// END Getters
}
