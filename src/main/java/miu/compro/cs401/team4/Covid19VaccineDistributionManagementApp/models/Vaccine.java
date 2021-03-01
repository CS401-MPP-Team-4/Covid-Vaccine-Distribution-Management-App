package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models;

public class Vaccine {
	private int id;
	private String name;
	private String manufacturer;
	private int amount;

	public Vaccine(int id, String name, String manufacturer, int amount) {
		super();
		this.id = id;
		this.name = name;
		this.manufacturer = manufacturer;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
