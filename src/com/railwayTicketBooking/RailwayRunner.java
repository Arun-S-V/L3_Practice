package com.railwayTicketBooking;

import java.util.ArrayList;
import java.util.Scanner;

public class RailwayRunner {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        boolean repeat = true;
        while(repeat){
            System.out.println("1. Book");
            System.out.println("2. Cancel");
            System.out.println("3. Print booked tickets");
            System.out.println("4. Print available tickets");
            System.out.println("5. Exit");
            int choice = scan.nextInt();
            switch(choice){
                case 1:{
                    System.out.println("please Enter  how many ticket you want to book");
                    int ticketCount = scan.nextInt();
                    ArrayList<Passenger> psList = new ArrayList<>();
                    int k =1;
                    for(int i = 0;i<ticketCount;i++){
                        System.out.println(k+". Enter your Name : ");
                        String name = scan.next();
                        System.out.println(k+". Enter your Gender : ");
                        String gender = scan.next();
                        System.out.println(k+". Enter your Age : ");
                        int age = scan.nextInt();
                        System.out.println(k+". Enter your Birth Preference : ");
                        String birthPreference = scan.next();

                        Passenger passenger = new Passenger();
                        passenger.setName(name);
                        passenger.setGender(gender);
                        passenger.setAge(age);
                        passenger.setBerthPreference(birthPreference);
                        psList.add(passenger);
                        k++;
                    }
                    String str = LogicLayer.LOGIC.TicketBooking(psList);
                    System.out.println(str);
                    break;
                }
                case 2:{
                    System.out.println("Enter your Ticket Number");
                    long ticketNo = scan.nextLong();
                    LogicLayer.LOGIC.cancelTicket(ticketNo);
                    break;
                }
                case 3:{
                    System.out.println("Enter your Ticket Number : ");
                    long ticketNo = scan.nextLong();
                    if(LogicLayer.LOGIC.ticketCheck(ticketNo)){
                        Passenger passenger = LogicLayer.LOGIC.printTicket(ticketNo);
                        System.out.println("Ticket Number : "+ticketNo);
                        System.out.println("Name : "+passenger.getName());
                        System.out.println("Age : "+passenger.getAge());
                        System.out.println("Gender : "+passenger.getGender());
                        System.out.println("Allocated Berth : "+passenger.getBerthPreference());
                    }
                    else{
                        System.out.println("Please Enter Correct Ticket Number");
                    }
                    break;
                }
                case 4:{
                    String str = LogicLayer.LOGIC.availableTickets();
                    System.out.println(str);
                    break;
                }
                case 5:{
                    repeat = false;
                }
            }
        }
    }
}
