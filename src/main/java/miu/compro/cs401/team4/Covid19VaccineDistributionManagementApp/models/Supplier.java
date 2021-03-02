package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models;

public class Supplier {
	private int id;
	private String name;
	private String city;
	private String phoneNumber;

	public Supplier(int id, String name, String city, String phoneNumber){
		this.id = id;
		this.name = name;
		this.city = city;
		this.phoneNumber = phoneNumber;
	}

	// START Getters
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getCity() {
		return city;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	// END Getters
}
