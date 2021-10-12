package com.OnlineJobPortal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LogicLayer {
    HashMap<String,Applicant> applicantMap = new LinkedHashMap<>();
    HashMap<String,Company> companyMap = new LinkedHashMap<>();
    HashMap<String,ArrayList<Company>> appliedMap = new LinkedHashMap<>();


    public boolean isAvailable(String userName) {
        if(userName == null){
            return false;
        }
        else if(applicantMap.containsKey(userName)){
            return true;
        }
        else{
            return false;
        }
    }

    public String storeApplicantData(Applicant applicant) {
        String userName = applicant.getUserName();
        applicantMap.put(userName,applicant);
        return "Register Successfully";
    }

    public boolean applicantLoginCheck(String userName, String password) {
        Applicant applicant = applicantMap.get(userName);
        String pass = applicant.getPassword();
        if(pass.equals(password)){
            return true;
        }
        return false;
    }


    public boolean isAvailableForCompany(String userName1) {
        if(userName1 == null){
            return false;
        }
        else if(companyMap.containsKey(userName1)){
            return true;
        }
        else{
            return false;
        }
    }

    public String storeCompanyData(Company company) {
        String userName = company.getUserName();
        companyMap.put(userName,company);
        return "Register Successfully";
    }

    public boolean CompanyLoginCheck(String userName, String password) {
        Company company =  companyMap.get(userName);
        String pass = company.getPassword();
        if(pass.equals(password)){
            return true;
        }
        return false;
    }

    public HashMap<String,EligibilityCriteria> displayAdmin(String userName) {
        Company company = companyMap.get(userName);
        HashMap<String,EligibilityCriteria> temp = company.getJopDetails();
        //System.out.println(temp);
        return temp;
    }


    public String updateJobDetails(String title,String userName,int open) {
        Company company = companyMap.get(userName);
        HashMap<String,EligibilityCriteria> temp = company.getJopDetails();
        //EligibilityCriteria criteria = new EligibilityCriteria();
        EligibilityCriteria criteria = temp.get(title);
        criteria.setVacancy(open);
        temp.put(title,criteria);
        return "Job Title : "+title+"----"+"Number of Vacancies : "+open+"----"+"Eligibility Criteria : "+criteria.getEligibility();
    }

    public String addNewJobs(String userName, String title1, int vacancy, String eligibility) {
        Company company = companyMap.get(userName);
        LinkedHashMap<String,EligibilityCriteria> temp1 = company.getJopDetails();
        EligibilityCriteria criteria = new EligibilityCriteria();
        criteria.setVacancy(vacancy);
        criteria.setEligibility(eligibility);
        temp1.put(title1,criteria);
        company.setJopDetails(temp1);
        companyMap.put(userName,company);
        return "Job Title : "+title1+"---"+"Number of Vacancies : "+vacancy+"Eligibility Criteria : "+eligibility;
    }

    public ArrayList<String> searchJob(String jobTitle) {
        ArrayList<String> list = new ArrayList<>();
        int i =1;
        for(Map.Entry<String,Company> map : companyMap.entrySet()){
            HashMap<String,EligibilityCriteria> jobMap = map.getValue().getJopDetails();
            for(Map.Entry<String,EligibilityCriteria> s:jobMap.entrySet()){
                if(s.getKey().equals(jobTitle)){
                    String str = i+". "+"Job Title : "+jobTitle+"      "+"Company Name : "+map.getValue().getName()+"      "+"City : "+map.getValue().getCity()+"      "+"Vacancy : "+s.getValue().getVacancy()+"      "+"Eligibility Criteria : "+s.getValue().getEligibility();
                    list.add(str);
                    i++;
                }
            }
        }
        return list;
    }
}
