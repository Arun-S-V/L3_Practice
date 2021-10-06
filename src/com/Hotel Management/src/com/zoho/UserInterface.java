package com.zoho;

import java.util.*;

public class UserInterface {
    public static void main(String args[]){
        LogicLayer logic = new LogicLayer();
        Scanner scan = new Scanner(System.in);
        boolean mainRepeat =  true;
        while(mainRepeat) {
            System.out.println("1. Admin Portal");
            System.out.println("2. Book Hotel");
            System.out.println("3. Exit");
            int choice = scan.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Enter Your UserName");
                    String UserName = scan.next();
                    System.out.println("Enter Password");
                    String password = scan.next();
                    if (logic.adminCheck(UserName, password)) {
                        System.out.println("Login Successfully");
                        boolean adminRepeat = true;
                        while(adminRepeat) {
                            System.out.println("1. Add Hotels");
                            System.out.println("2. See User Booking details");
                            System.out.println("3. Back");
                            int adminChoice = scan.nextInt();
                            if(adminChoice == 1){
                                System.out.println("Enter City");
                                String city = scan.next();
                                System.out.println("Enter hotel name");
                                String hotelName = scan.next();
                                System.out.println("Enter hotel rating");
                                int rating = scan.nextInt();
                                System.out.println("Enter available Rooms");
                                int availableRooms = scan.nextInt();
                                System.out.println("Enter Cost per day");
                                double cost = scan.nextDouble();

                                HotelData hotel = new HotelData();
                                hotel.setCity(city);
                                hotel.setRating(rating);
                                hotel.setRoomAvailable(availableRooms);
                                hotel.setCost(cost);
                                hotel.setHotelName(hotelName);

                                String message = logic.addHotel(hotel);
                                System.out.println(message);
                            }
                            else if(adminChoice == 2){
                               HashMap<Integer,UserData> userMap = logic.bookingDetails();
                                for(Map.Entry<Integer,UserData> e : userMap.entrySet()){
                                    System.out.println(e.getKey()+"   "+e.getValue());
                                }
                            }
                            else if(adminChoice == 3){
                                adminRepeat = false;
                            }
                        }
                    } else {
                        System.out.println("Please Enter Correct Information");
                    }
                    break;
                }
                case 2: {
                    boolean userRepeat = true;
                    while(userRepeat) {
                        System.out.println("1. Sort Hotel By Name");
                        System.out.println("2. Sort Hotel By Rating");
                        System.out.println("3. Sort Hotel Available Rooms");
                        System.out.println("4. Search Hotel For a Location");
                        System.out.println("5. Book Hotel");
                        System.out.println("6. Back");
                        int userChoice = scan.nextInt();
                        if(userChoice == 1){
                            List<Map.Entry<String,HotelData>> nameList = logic.sortByName();
                            for(Map.Entry<String,HotelData> e : nameList){
                                System.out.println(e);
                            }
                        }
                        else if(userChoice == 2){
                            List<Map.Entry<String,HotelData>> ratingList = logic.sortByRating();
                            for(Map.Entry<String,HotelData> e : ratingList){
                                System.out.println(e);
                            }
                        }
                        else if(userChoice == 3){
                            List<Map.Entry<String,HotelData>> roomList = logic.sortByAvailableRoom();
                            for(Map.Entry<String,HotelData> e : roomList){
                                System.out.println(e);
                            }
                        }
                        else if(userChoice == 4){
                            System.out.println("Enter Location You Want to Search : ");
                            String city = scan.next();
                            String message = logic.searchByLocation(city);
                            System.out.println("HotelName  AvailableRooms  Location  Rating  RatePerDay");
                            System.out.println(message);
                        }
                        else if(userChoice == 5){
                            System.out.println("Please Check the availability of the hotel and then book");
                            System.out.println("Enter Your name : ");
                            String name = scan.next();
                            System.out.println("Enter Hotel Name : ");
                            String hotelName = scan.next();
                            if(logic.hotelCheck(hotelName)){
                                UserData user = logic.bookHotel(hotelName,name);
                                System.out.println("Hotel Booked  Successfully"+"\n"+"Your UserId is : "+user.getUserId()+"\n"+"Hotel Name : "+user.getHotelName()+"\n"+"Booking Cost : "+user.getCost());

                            }
                            else{
                                System.out.println("You Entered Details is wrong Please check once again....");
                            }
                        }
                        else if(userChoice == 6){
                            userRepeat = false;
                        }
                    }
                    break;
                }
                case 3: {
                    mainRepeat = false;
                    break;
                }
            }
        }
    }
}
