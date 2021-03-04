package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils;

public enum ROLES {
    STAFF("Staff"),
    ADMIN("Admin"),
    CANDIDATE("Candidate");

    String val;

    ROLES(String role){
        val = role;

    }
    public String getVal(){
        return val;
    }

}
