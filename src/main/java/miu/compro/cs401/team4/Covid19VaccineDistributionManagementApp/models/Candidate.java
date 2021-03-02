package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models;

import java.time.LocalDate;

public class Candidate {
	private Integer id;
	private String firstName;
	private String lastName;
	private Byte age;
	private String ssn;
	private String status;
	private LocalDate dateOfApplication;
	private VaccinationSite vaccinationSite;

	// START Constructors
	public Candidate() { }

	public Candidate(Integer id, String firstName, String lastName, Byte age, String ssn, String status, LocalDate dateOfApplication, VaccinationSite vaccinationSite) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.ssn = ssn;
		this.status = status;
		this.dateOfApplication = dateOfApplication;
		this.vaccinationSite = vaccinationSite;
	}
	// END Constructors

	// START Getters
	public Integer getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public Byte getAge() {
		return age;
	}
	public String getSsn() {
		return ssn;
	}
	public String getStatus() {
		return status;
	}
	public LocalDate getDateOfApplication() {
		return dateOfApplication;
	}
	public VaccinationSite getVaccinationSite() {
		return vaccinationSite;
	}
	// END Getters
}
