package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models;

import java.time.LocalDate;

public class Recipient extends Model {
	private Integer id;
	private LocalDate dateOfShot;
	private Candidate takenBy;
	private Vaccine vaccine;
	private Staff administeredBy;

	// START Constructors
	public Recipient() { }

	public Recipient(Integer id, LocalDate dateOfShot, Candidate takenBy, Vaccine vaccine, Staff administeredBy) {
		this.id = id;
		this.dateOfShot = dateOfShot;
		this.takenBy = takenBy;
		this.vaccine = vaccine;
		this.administeredBy = administeredBy;
	}
	// END Constructors

	// START Getters
	public Integer getId() {
		return id;
	}
	public LocalDate getDateOfShot() {
		return dateOfShot;
	}
	public Candidate getTakenBy() {
		return takenBy;
	}
	public Vaccine getVaccine() {
		return vaccine;
	}
	public Staff getAdministeredBy() {
		return administeredBy;
	}
	// END Getters

	@Override
	public String toString() {
		return String.format("Candidate\n%s\nAdministered by %s. Date of Shot: %s.", takenBy, dateOfShot, administeredBy.getUserName());
	}
}
