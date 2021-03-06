package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models;

public class Staff extends Model {
	private Integer id;
	private String userName;
	private String password;
	private String jobDescription; /* (Admin) (Staff) */

	// START Constructors
	public Staff() { }

	public Staff(Integer id, String userName, String password, String jobDescription) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.jobDescription = jobDescription;
	}

	public Staff(Integer id) {
		this.id = id;
	}
	// END Constructors

	// START Getters
	public Integer getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	// END Getters
	
	

	@Override
	public String toString() {
		return String.format("Name: %s. Job Description: %s.", userName, jobDescription);
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
}
