package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models;

abstract public class Model {
	protected Integer id;

	abstract public String toString();

//	@Override
//	public boolean equals(Object o) {
//		Model aModel = (Model) o;
//		return id.equals(aModel.id);
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
