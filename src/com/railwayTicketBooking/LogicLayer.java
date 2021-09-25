package com.railwayTicketBooking;

import java.util.ArrayList;
import java.util.HashMap;

public enum LogicLayer {
    LOGIC;
    HashMap<Long, Passenger> conformedTicket = new HashMap<>();
    ArrayList<Passenger> RACTicket = new ArrayList<>();
    ArrayList<Passenger> waitingTicket = new ArrayList<>();
    int LBCount = 1;
    int UBCount = 1;
    int MBCount = 1;
    int SUBCount = 1;
    int SLRACCount = 1;
    long ticketNumber = 1000;

    public String TicketBooking(ArrayList<Passenger> psList) {
        String str = "";
        if (conformedTicket.size() <= 1) {
            for (int i = 0; i < psList.size(); i++) {
                Passenger passenger = psList.get(i);
                int age = passenger.getAge();
                if (age > 5) {
                    String birthAllowance = BirthAllowance(passenger);
                    passenger.setBerthPreference(birthAllowance);
                    conformedTicket.put(ticketNumber, passenger);
                    str = str + passenger.getName() +"\n"+ "Your Ticket No is :" + ticketNumber+"\n"+"Seat Number is: "+birthAllowance+"\n";
                    ticketNumber++;
                }
            }
            //System.out.println(conformedTicket);
            return str;
        }
        else if(RACTicket.size() <= 1){
            for (int i = 0; i < psList.size(); i++) {
                Passenger passenger = psList.get(i);
                int age = passenger.getAge();
                if (age > 5) {
                    String str1 = "SLRAC"+SLRACCount;
                    passenger.setBerthPreference(str1);
                    passenger.setTicketNo(ticketNumber);
                    RACTicket.add(passenger);
                    str = str + passenger.getName() +"\n"+ "Your Ticket in RAC : Ticket No :" + ticketNumber+"\n"+"Seat Number is :"+str1;
                    ticketNumber++;
                    SLRACCount++;
                }
            }
            return str;
        }
        else if(waitingTicket.size() <= 1){
            for (int i = 0; i < psList.size(); i++) {
                Passenger passenger = psList.get(i);
                int age = passenger.getAge();
                if (age > 5) {
                    passenger.setTicketNo(ticketNumber);
                    waitingTicket.add(passenger);
                    str = passenger.getName() +"\n"+ "Your Ticket in Waiting List : Ticket No is " + ticketNumber;
                    ticketNumber++;
                }
            }
            return str;
        }
        else{
            return "Ticket Not available";
        }
    }

    public String BirthAllowance(Passenger passenger) {
        String berthPreference = passenger.getBerthPreference();
        int age = passenger.getAge();
        String str = "";
        if (age >= 60) {
            if (LBCount <= 18){
                str = "LB"+LBCount;
                LBCount++;
            }
            else if(MBCount <= 18){
                str = "MB"+MBCount;
                MBCount++;
            }
            else if(UBCount <= 18){
                str = "UB"+UBCount;
                UBCount++;
            }
            else if(SUBCount <= 9){
                str = "SUB"+SUBCount;
                SUBCount++;
            }
        }
        else {
            switch(berthPreference){
                case "LB":{
                    if(LBCount <= 18){
                        str = "LB"+LBCount;
                        LBCount++;
                        break;
                    }
                    else {
                        if(MBCount <18){
                            str = "MB"+MBCount;
                            MBCount++;
                            break;
                        }
                        else if(UBCount < 18){
                            str = "UB"+UBCount;
                            UBCount++;
                            break;
                        }
                        else if(SUBCount < 9){
                            str = "SUB"+SUBCount;
                            SUBCount++;
                            break;
                        }
                    }
                }
                case "MB" :{
                    if(MBCount <= 18){
                        str = "MB"+MBCount;
                        MBCount++;
                        break;
                    }
                    else {
                        if(LBCount <=18){
                            str = "LB"+LBCount;
                            LBCount++;
                            break;
                        }
                        else if(UBCount <= 18){
                            str = "UB"+UBCount;
                            UBCount++;
                            break;
                        }
                        else if(SUBCount <= 9){
                            str = "SUB"+SUBCount;
                            SUBCount++;
                            break;
                        }
                    }
                }
                case "UB" :{
                    if(UBCount <= 18){
                        str = "UB"+UBCount;
                        UBCount++;
                        break;
                    }
                    else{
                        if(LBCount <= 18){
                            str = "LB"+LBCount;
                            LBCount++;
                            break;
                        }
                        else if(MBCount <= 18){
                            str = "MB"+MBCount;
                            MBCount++;
                            break;
                        }
                        else if(SUBCount <= 9){
                            str = "SUB"+SUBCount;
                            SUBCount++;
                            break;
                        }
                    }
                }
                case "SUB":{
                    if(SUBCount <= 0){
                        str = "SUB"+SUBCount;
                        SUBCount++;
                        break;
                    }
                    else{
                        if(LBCount <= 18){
                            str = "LB"+LBCount;
                            LBCount++;
                            break;
                        }
                        else if(MBCount <= 18){
                            str = "MB"+MBCount;
                            MBCount++;
                            break;
                        }
                        else if(UBCount <= 9){
                            str = "UB"+UBCount;
                            UBCount++;
                            break;
                        }
                    }
                }
            }
        }
        return str;
    }
    public String cancelTicket(long ticketNo){
        String str = "";
        if(conformedTicket.containsKey(ticketNo)){
            Passenger passenger = conformedTicket.remove(ticketNo);
            String berthAllocated = passenger.getBerthPreference();
            str = "Canceled Successfully";
            if(!RACTicket.isEmpty()) {
                ticketRACCancel(berthAllocated);
            }
        }
        else{
            str = "Your ticket Number is not Exits";
        }
        return str;
    }
    public void ticketRACCancel(String berthAllocated){
        Passenger passenger = RACTicket.remove(0);
        long ticketNo = passenger.getTicketNo();
        passenger.setBerthPreference(berthAllocated);
        conformedTicket.put(ticketNo,passenger);
        if(!waitingTicket.isEmpty()) {
            waitingAllocate();
        }
    }
    public void waitingAllocate(){
        Passenger passenger = waitingTicket.remove(0);
        long ticketNo = passenger.getTicketNo();
        passenger.setBerthPreference("SLRAC"+SLRACCount);
        RACTicket.add(passenger);
    }
    public boolean ticketCheck(long ticketNo){
        if(conformedTicket.containsKey(ticketNo)){
            return true;
        }
        else if(!conformedTicket.containsKey(ticketNo)){
            for (Passenger passenger : RACTicket){
                if (passenger.getTicketNo() == ticketNo){
                    return true;
                }
            }
        }
        return false;
    }
    public Passenger printTicket(long ticketNo){
        Passenger passenger = new Passenger();
        if(conformedTicket.containsKey(ticketNo)){
            passenger = conformedTicket.get(ticketNo);
            return passenger;
        }
        else{
            for (Passenger passenger1 : RACTicket){
                if (passenger.getTicketNo() == ticketNo){
                    return passenger1;
                }
            }
        }
        return passenger;
    }
    public String availableTickets(){
        int berthAvailable = 63 - conformedTicket.size();
        int RACAvailable = 18 - RACTicket.size();
        int waiting = waitingTicket.size();
        String str = "Available Berths = "+berthAvailable+"\n"+"Available RAC's = "+RACAvailable+"\n"+"Waiting List = "+waiting;
        return str;
    }
}
