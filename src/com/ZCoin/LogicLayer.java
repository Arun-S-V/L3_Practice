package com.ZCoin;

import java.util.ArrayList;
import java.util.HashMap;

public enum LogicLayer {
    LOGIC;
    ArrayList<Employee> employeeList =  new ArrayList<>();
    ArrayList<User> userTempList = new ArrayList<>();
    HashMap<String,User> userMap = new HashMap();
    HashMap<Long,ArrayList<String>> TransactionMap = new HashMap<>();
    HashMap<Long,ArrayList<String>> TransactionZCRC = new HashMap<>();
    String employeeEmail = "zoho@gmail.com";
    String employeePassword = "zoho123";
    long zId = 1000;
    long changeRate = 2;
    public void changeZCRate(long change){
        changeRate = change;
    }

    public void checkListFill(User user){
        userTempList.add(user);
    }
//    public void adminRegister(Employee emp){
//        employeeList.add(emp);
//    }
    public boolean adminPortal(Employee emp){
        String email = emp.getEmail();
        String password = emp.getPassword();
        if(employeeEmail.equals(email) && employeePassword.equals(password)){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean userLogin(User emp){
        String email = emp.getEmailId();
        String password = emp.getPassword();
        if(userMap.containsKey(email)){
            if(userMap.get(email).getPassword().equals(password)){
                return true;
            }
        }
        else{
            return false;
        }
        return false;
    }
    public ArrayList<User> userApprove(){
        if(!userTempList.isEmpty()) {
            return userTempList;
        }
        return userTempList;
    }
    public long adminApprove(User user){
        userTempList.remove(0);
        user.setzId(zId);
        String email = user.getEmailId();
        userMap.put(email,user);
        zId = zId + 2;
        return zId-2;
    }
    public void adminReject(User user){
        employeeList.remove(0);
    }
    public User showAccountDetails(String email){
        User user = userMap.get(email);
        return user;
    }

    public String depositAmount(double deposit,long ZId,String email){
        User user = userMap.get(email);
        double RC = user.getAmount();
        double amount = RC+deposit;
        user.setAmount(amount);
        userMap.put(email,user);
        String str = "Amount Deposited : "+deposit+"\n"+"Balance :"+amount;
        ArrayList<String> tempList = TransactionMap.getOrDefault(zId,new ArrayList<String>());
        tempList.add(str);
        TransactionMap.put(ZId,tempList);
        return str;
    }
    public String withdrawAmount(double withdraw,long ZId,String email){
        User user = userMap.get(email);
        double RC = user.getAmount();
        double amount = RC-withdraw;
        user.setAmount(amount);
        userMap.put(email,user);
        String str = "Amount Withdrawal : "+withdraw+"\n"+"Balance : "+amount;
        ArrayList<String> tempList = TransactionMap.getOrDefault(zId,new ArrayList<String>());
        tempList.add(str);
        TransactionMap.put(ZId,tempList);
        return str;
    }
    public ArrayList<String> transactionHistory(long zId){
        ArrayList<String> transList = TransactionMap.get(zId);
        return transList;
    }
    public ArrayList<String> transactionHistory2(long zId){
        ArrayList<String> transList = TransactionZCRC.get(zId);
        return transList;
    }
    public String exchangeRCtoZC(String email,long zId,double exeRC){
        User user = userMap.get(email);
        double RC = user.getAmount();

        if(exeRC <= RC){
            double ZC = exeRC/changeRate;
            double amount = RC - exeRC;
            user.setAmount(amount);
            user.setZC(ZC);
            userMap.put(email,user);
            String str = exeRC+"RC to "+ZC+" ZC";
            ArrayList<String> tempList = TransactionMap.getOrDefault(zId,new ArrayList<String>());
            tempList.add(str);
            TransactionZCRC.put(zId,tempList);
            return str;
        }
        else{
            return "You have not enough Rc";
        }
    }
    public String exchangeZCtoRC(String email,long zId,double exeZC){
        User user = userMap.get(email);
        double ZC = user.getZC();

        if(exeZC <= ZC){
            double RC = exeZC*changeRate;
            double commision = RC*0.0015;
            RC = RC - commision;
            double amount = ZC - exeZC;
            user.setAmount(RC);
            user.setZC(amount);
            userMap.put(email,user);
            String str = exeZC+"ZC to "+RC+" RC";
            ArrayList<String> tempList = TransactionMap.getOrDefault(zId,new ArrayList<String>());
            tempList.add(str);
            TransactionZCRC.put(zId,tempList);
            return str;
        }
        else{
            return "You have not enough ZC";
        }
    }
    public String aToaTransaction(long myZID,long othersZID,double exeZC,String email,String otherEmail){
        if(userMap.containsKey(otherEmail)){
            User user1 = userMap.get(email);
            double myZC = user1.getZC();
            if(exeZC<=myZC){
                User user2 = userMap.get(otherEmail);
                double otherZC =  user2.getZC();
                double sum1ZC = exeZC + otherZC;
                user2.setZC(sum1ZC);
                userMap.put(otherEmail,user2);
                double sum2ZC = myZC-exeZC;
                user1.setZC(sum2ZC);
                userMap.put(email,user1);
                String str = "From"+zId+"ZC of "+ exeZC+" transfer to"+othersZID;
                ArrayList<String> list = TransactionMap.getOrDefault(zId,new ArrayList<String>());
                list.add(str);
                TransactionZCRC.put(zId,list);
                return str;
            }
            else{
                return "You have not enough ZC";
            }
        }
        else{
            return "Please Enter correct email Id";
        }
    }
}
