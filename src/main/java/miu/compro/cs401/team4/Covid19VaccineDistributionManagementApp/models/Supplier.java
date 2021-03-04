package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models;

public class Supplier extends Model {
	private String name;
	private String address; /* address, State */
	private String phoneNumber;

	// START Constructors
	public Supplier() {
	}

	public Supplier(Integer id, String name, String address, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	// END Constructors

	// START Getters
	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	// END Getters

	@Override
	public String toString() {
		return String.format("%s %s", getName(), getAddress());
	}
}
