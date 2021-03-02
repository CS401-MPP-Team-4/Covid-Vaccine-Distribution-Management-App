package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models;

public class Vaccine {
	private Integer id;
	private String name;
	private Supplier manufacturer;
	private int amount;

	// START Constructors
	public Vaccine() { }

	public Vaccine(int id, String name, Supplier manufacturer, int amount) {
		super();
		this.id = id;
		this.name = name;
		this.manufacturer = manufacturer;
		this.amount = amount;
	}
	// END Constructors

	// START Getters
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
	public Supplier getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Supplier manufacturer) {
		this.manufacturer = manufacturer;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	// END Getters
}
