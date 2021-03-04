package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models;

public class SiteStock extends Model {
	private Integer id;
	private VaccinationSite site;
	private Vaccine vaccine;
	private Integer stockAmount;

	// START Constructors
	public SiteStock() { }

	public SiteStock(Integer id, VaccinationSite site, Vaccine vaccine, Integer stockAmount) {
		this.id = id;
		this.site = site;
		this.vaccine = vaccine;
		this.stockAmount = stockAmount;
	}
	// END Constructors

	// START Getters
	public Integer getId() {
		return id;
	}
	public VaccinationSite getSite() {
		return site;
	}
	public Vaccine getVaccine() {
		return vaccine;
	}
	public Integer getStockAmount() {
		return stockAmount;
	}
	// END Getters
	
	public void setSite(VaccinationSite site) {
		this.site = site;
	}

	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
	}

	public void setStockAmount(Integer stockAmount) {
		this.stockAmount = stockAmount;
	}

	@Override
	public String toString() {
		return String.format("Vaccination Site\n%s\nVaccine\n%s\nStock amount: %d", site, vaccine, stockAmount);
	}

	
}
