package com.OnlineJobPortal;


import java.util.*;

public class UserInterface {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LogicLayer logic = new LogicLayer();
        boolean repeat = true;
        while(repeat) {
            System.out.println("1. Applicant portal");
            System.out.println("2. Company Portal");
            System.out.println("3. Exit");
            int choice = scan.nextInt();
            switch (choice){
                case 1 :{
                    boolean repeat1 = true;
                    while(repeat1){
                        System.out.println("1.Registration");
                        System.out.println("2. Login");
                        System.out.println("3. Back");
                        int choice1 = scan.nextInt();
                        if(choice1 == 1){
                            System.out.println("Enter name");
                            String name = scan.nextLine();
                            scan.nextLine();
                            System.out.println("Enter Year of Birth");
                            int year = scan.nextInt();
                            scan.nextLine();
                            System.out.println("Enter Qualification with your Branch");
                            String qualification = scan.nextLine();
                            System.out.println("Enter your final mark in percentage");
                            float mark = scan.nextFloat();
                            scan.nextLine();
                            System.out.println("Enter college name");
                            String college = scan.nextLine();
                            System.out.println("Enter your home City");
                            String city = scan.nextLine();
                            System.out.println("Please set your UserName for a Login purpose");
                            String userName = scan.next();
                            while(logic.isAvailable(userName)){
                                System.out.println("Username not available please enter another one");
                                userName = scan.next();
                            }
                            System.out.println("Enter Your password for login purpose");
                            String password = scan.next();

                            Applicant applicant = new Applicant();
                            applicant.setName(name);
                            applicant.setBirthYear(year);
                            applicant.setQualification(qualification);
                            applicant.setMark(mark);
                            applicant.setCollegeName(college);
                            applicant.setHomeCity(city);
                            applicant.setUserName(userName);
                            applicant.setPassword(password);

                            String message = logic.storeApplicantData(applicant);
                            System.out.println(message);
                        }
                        else if(choice1 == 2){
                            System.out.println("Enter your Username");
                            String userName = scan.next();
                            System.out.println("Enter your Password");
                            String password = scan.next();
                            if(logic.isAvailable(userName)) {
                                if (logic.applicantLoginCheck(userName, password)) {
                                    System.out.println("Login Successfully");
                                    boolean repeat5 = true;
                                    while(repeat5) {
                                        System.out.println("1. Job Search");
                                        System.out.println("2. View Application Status");
                                        System.out.println("3. Back");
                                        int choice6 = scan.nextInt();
                                        if(choice6 == 1){
                                            scan.nextLine();
                                            System.out.println("Enter the Job title");
                                            String jobTitle = scan.nextLine();
                                            ArrayList<String> list = logic.searchJob(jobTitle);
                                            for(String i : list){
                                                System.out.println(i);
                                            }
                                            System.out.println("Do you want to apply any job press 1");
                                            System.out.println("2. Back");
                                            int choice7 = scan.nextInt();
                                            if(choice7 == 1){
                                                System.out.println("Please enter the job number");
                                                int jobNo = scan.nextInt();
                                                jobNo = jobNo - 1;

                                            }
                                            else if(choice7 == 2){

                                            }
                                        }
                                        else if(choice6 == 2){

                                        }
                                        else if(choice6 == 3){
                                            repeat5 = false;
                                        }
                                    }
                                }
                                else{
                                    System.out.println("Please Enter correct password");
                                }
                            }
                            else{
                                System.out.println("Please provide correct details");
                            }
                        }
                        else if(choice1 == 3){
                            repeat1 = false;
                        }
                    }
                    break;
                }
                case 2 : {
                    boolean repeat2 = true;
                    while (repeat2) {
                        System.out.println("1. Registration");
                        System.out.println("2. Login");
                        System.out.println("3. Back");
                        int choice2 = scan.nextInt();
                        if (choice2 == 1) {
                            scan.nextLine();
                            System.out.println("Enter Company Name");
                            String name = scan.nextLine();
                            System.out.println("Enter city");
                            String city = scan.next();
                            System.out.println("Please set your UserName for a Login purpose");
                            String userName1 = scan.next();
                            while(logic.isAvailableForCompany(userName1)){
                                System.out.println("Username not available please enter another one");
                                userName1 = scan.next();
                            }
                            System.out.println("Enter Your password for login purpose");
                            String password = scan.next();
                            LinkedHashMap<String,EligibilityCriteria> temp = new LinkedHashMap<>();
                            System.out.println("Please Enter number of job titles you want to enter");
                            int numTitle = scan.nextInt();
                            scan.nextLine();
                            for(int i=0;i<numTitle;i++){
                                System.out.println("Enter job title");
                                String title = scan.nextLine();
                                System.out.println("Enter number of vacancies");
                                int vacancy = scan.nextInt();
                                scan.nextLine();
                                System.out.println("Enter Eligibility Criteria");
                                String eligibility = scan.nextLine();
                                EligibilityCriteria criteria = new EligibilityCriteria();
                                criteria.setVacancy(vacancy);
                                criteria.setEligibility(eligibility);
                                temp.put(title,criteria);
                            }

                            Company company = new Company();
                            company.setName(name);
                            company.setCity(city);
                            company.setJopDetails(temp);
                            company.setUserName(userName1);
                            company.setPassword(password);

                            String message  = logic.storeCompanyData(company);
                            System.out.println(message);
                        }
                        else if(choice2 ==  2){
                            System.out.println("Enter your Username");
                            String userName = scan.next();
                            System.out.println("Enter your Password");
                            String password = scan.next();
                            if(logic.isAvailableForCompany(userName)) {
                                if (logic.CompanyLoginCheck(userName, password)) {
                                    System.out.println("Login Successfully");
                                    boolean repeat3 = true;
                                    while(repeat3) {
                                        System.out.println("1. Add vacancy Details");
                                        System.out.println("2. Display Vacancy Details");
                                        System.out.println("3. Back");
                                        int choice3 = scan.nextInt();
                                        if(choice3 == 1) {
                                            boolean repeat4 = true;
                                            while (repeat4) {
                                                System.out.println("1. You have update the old job");
                                                System.out.println("2. Add new Job title");
                                                System.out.println("3. Back");
                                                int choice4 = scan.nextInt();
                                                if (choice4 == 1) {
                                                    scan.nextLine();
                                                    System.out.println("Enter job title you want to update");
                                                    String title = scan.nextLine();
                                                    System.out.println("Enter number of openings");
                                                    int open = scan.nextInt();
                                                    String message = logic.updateJobDetails(title,userName,open);
                                                    System.out.println(message);
                                                }
                                                if (choice4 == 2) {
                                                    scan.nextLine();
                                                    System.out.println("Enter Job Title");
                                                    String title1 = scan.nextLine();
                                                    System.out.println("Enter number of vacancies");
                                                    int vacancy = scan.nextInt();
                                                    scan.nextLine();
                                                    System.out.println("Enter Eligibility Criteria");
                                                    String eligibility = scan.nextLine();
                                                    String message2 = logic.addNewJobs(userName,title1,vacancy,eligibility);
                                                    System.out.println(message2);
                                                }
                                                if (choice4 == 3) {
                                                    repeat4 = false;
                                                }
                                            }
                                        }
                                        else if(choice3 == 2){
                                            HashMap<String,EligibilityCriteria> temp1 = logic.displayAdmin(userName);
                                            for(Map.Entry<String,EligibilityCriteria> map : temp1.entrySet()){
                                                System.out.println("Job Title : "+map.getKey()+"-----"+"Number of Vacancies : "+map.getValue().getVacancy()+"-----"+"Eligibility Criteria : "+map.getValue().getEligibility());
                                            }
                                        }
                                        else if (choice3 == 3){
                                            repeat3 = false;
                                        }
                                    }

                                }
                                else{
                                    System.out.println("Please Enter correct password");
                                }
                            }
                            else{
                                System.out.println("Please provide correct details");
                            }
                        }
                        else if(choice2 == 3){
                            repeat2 = false;
                        }
                    }
                    break;
                }
                case 3 :{
                    repeat = false;
                }
            }
        }
    }
}
