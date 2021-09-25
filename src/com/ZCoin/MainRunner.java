package com.ZCoin;

import java.util.ArrayList;
import java.util.Scanner;

public class MainRunner {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        boolean repeat = true;
        while (repeat) {
            System.out.println("Welcome To Z_coin");
            System.out.println("1. User Registration");
            System.out.println("2. User Login");
            System.out.println("3. Employee Login");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");
            int choice = scan.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Enter your Name :");
                    String name = scan.next();
                    System.out.println("Enter your Email : ");
                    String email = scan.next();
                    System.out.println("Enter your Mobile Number : ");
                    long mobileNo = scan.nextLong();
                    System.out.println("Enter your Password : ");
                    String password = scan.next();
                    System.out.println("Enter initial Deposit Greater then 100 : ");
                    double amount = scan.nextDouble();
                    System.out.println("Enter your Unique H_Id : ");
                    long hId = scan.nextLong();

                    User user = new User();
                    user.setName(name);
                    user.setEmailId(email);
                    user.setMobileNo(mobileNo);
                    user.setPassword(password);
                    user.setAmount(amount);
                    user.sethID(hId);
                    user.setZC(0);
                    hId++;
                    LogicLayer.LOGIC.checkListFill(user);
                    break;
                }
                case 2: {
                    System.out.println("Enter your Email Id : ");
                    String email = scan.next();
                    System.out.println("Enter your Password  : ");
                    String password = scan.next();

                    User user = new User();
                    user.setEmailId(email);
                    user.setPassword(password);
                    if (LogicLayer.LOGIC.userLogin(user)) {
                        System.out.println("Login Successfully");
                        System.out.println();
                        boolean repeat1 = true;
                        while(repeat1) {
                            System.out.println("1. Account Details");
                            System.out.println("2. Transaction history of user");
                            System.out.println("3. RC transaction");
                            System.out.println("4. ZCoin Transaction");
                            System.out.println("Press any other key to back");
                            int action = scan.nextInt();
                            if (action == 1) {
                                user = LogicLayer.LOGIC.showAccountDetails(email);
                                System.out.println("Name :" + user.getName());
                                System.out.println("Email : " + user.getEmailId());
                                System.out.println("Mobile Number : " + user.getMobileNo());
                                System.out.println("H_Id : " + user.gethID());
                                System.out.println("Z_ID : " + user.getzId());
                            }
                            else if (action == 2) {
                                ArrayList<String> list = LogicLayer.LOGIC.transactionHistory(user.getzId());
                                System.out.println("-----------------RC Transaction------------------");
                                for(int j =0;j<list.size();j++){
                                    System.out.println(list.get(j));
                                }
                                System.out.println("ZC to RC, RC to ZC and ZC Transaction");
                               ArrayList<String> list1 = LogicLayer.LOGIC.transactionHistory2(user.getzId());
                                for(int j =0;j<list1.size();j++){
                                    System.out.println(list.get(j));
                                }
                            }
                            else if (action == 3) {
                                boolean repeat2 = true;
                                while(repeat2){
                                    System.out.println("1. Deposit");
                                    System.out.println("2. Withdraw");
                                    int choice4 = scan.nextInt();
                                    if(choice4 == 1){
                                        System.out.println("Enter Amount you want to Deposit");
                                        double deposit = scan.nextDouble();
                                        String status = LogicLayer.LOGIC.depositAmount(deposit,user.getzId(),email);
                                        System.out.println(status);
                                    }
                                    else if(choice4 == 2){
                                        System.out.println("Enter Amount you want to Withdraw");
                                        double deposit = scan.nextDouble();
                                        String status = LogicLayer.LOGIC.withdrawAmount(deposit,user.getzId(),email);
                                        System.out.println(status);
                                    }
                                    else if(choice4 >2){
                                        repeat2 = false;
                                    }
                                }
                            }
                            else if (action == 4) {
                                boolean repeat5 = true;
                                while(repeat5){
                                    System.out.println("1. Exchange RC to ZCoin");
                                    System.out.println("2. Exchange ZCoin to RC");
                                    System.out.println("3. ZCoin transfer for Another ZId Account");
                                    int choice5 = scan.nextInt();
                                    if(choice5 == 1){
                                        System.out.println("Enter RC you want to Exchange");
                                        double exeRC = scan.nextDouble();
                                        String str = LogicLayer.LOGIC.exchangeRCtoZC(email,user.getzId(),exeRC);
                                        System.out.println(str);
                                    }
                                    else if(choice5 == 2){
                                        System.out.println("Enter ZC you want to Exchange");
                                        double exeZC = scan.nextDouble();
                                        String str = LogicLayer.LOGIC.exchangeZCtoRC(email,user.getzId(),exeZC);
                                        System.out.println(str);
                                    }
                                    else if(choice5 == 3){
                                        System.out.println("Enter Z_ID to Transfer ZC");
                                        long z_id = scan.nextLong();
                                        System.out.println("Enter Amount ZCoin you want to Transfer");
                                        double ZC = scan.nextDouble();
                                        System.out.println("Enter Email Id of other User");
                                        String emailID = scan.next();
                                        String str = LogicLayer.LOGIC.aToaTransaction(user.getzId(),z_id,ZC,email,emailID);
                                        System.out.println(str);
                                    }
                                    else if(choice5>4){
                                        repeat5 = false;
                                    }
                                }
                            }
                            else if(action >4){
                                repeat1 = false;
                            }
                        }
                    } else {
                        System.out.println("Please check your email and password");
                    }
                    break;
                }
                case 3: {
                    Employee emp = new Employee();
                    System.out.println("Enter email : ");
                    String email = scan.next();
                    System.out.println("Enter password : ");
                    String password = scan.next();
                    emp.setEmail(email);
                    emp.setPassword(password);
                    if (LogicLayer.LOGIC.adminPortal(emp)) {
                        System.out.println("Login Successfully");
                        System.out.println("1. Check Registered user and approve or Reject");
                        System.out.println("2. Change RC rate");
                        System.out.println("3. See User Transaction details");
                        int action1 = scan.nextInt();
                        if (action1 == 1) {
                            ArrayList<User> userList = LogicLayer.LOGIC.userApprove();
                            for (int i = 0; i < userList.size(); i++) {
                                System.out.println("Name : " + userList.get(i).getName());
                                System.out.println("Email : " + userList.get(i).getEmailId());
                                System.out.println("Mobile Number : " + userList.get(i).getMobileNo());
                                System.out.println("1. Approve");
                                System.out.println("2. Reject");
                                int action = scan.nextInt();
                                if (action == 1) {
                                    long zId = LogicLayer.LOGIC.adminApprove(userList.get(i));
                                    System.out.println("Your Z_ID is : "+zId);
                                } else if (action == 2) {
                                    LogicLayer.LOGIC.adminReject(userList.get(i));
                                }
                            }
                        }
                        else if (action1 == 2) {
                            System.out.println("Enter Changes Rate");
                            long change = scan.nextLong();
                            LogicLayer.LOGIC.changeZCRate(change);
                        }
                        else if(action1 == 3){

                        }
                    } else {
                        System.out.println("Please check your Details");
                    }
                    break;
                }
                case 4: {
                    repeat = false;
                    break;
                }
            }
        }
    }
}
