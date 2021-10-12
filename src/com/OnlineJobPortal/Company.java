package com.OnlineJobPortal;


import java.util.LinkedHashMap;

public class Company {
    private String name;
    private String jobTitle;
//    private int vacancy;
  //  private String eligibilityCriteria;
    private String city;
    private String userName;
    private String password;
    private LinkedHashMap<String,EligibilityCriteria> jopDetails;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

//    public int getVacancy() {
//        return vacancy;
//    }
//
//    public void setVacancy(int vacancy) {
//        this.vacancy = vacancy;
//    }

//    public String getEligibilityCriteria() {
//        return eligibilityCriteria;
//    }
//
//    public void setEligibilityCriteria(String eligibilityCriteria) {
//        this.eligibilityCriteria = eligibilityCriteria;
//    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LinkedHashMap<String, EligibilityCriteria> getJopDetails() {
        return jopDetails;
    }

    public void setJopDetails(LinkedHashMap<String, EligibilityCriteria> jopDetails) {
        this.jopDetails = jopDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
