package com.HotelManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogicLayer {
    String adminName = "admin";
    String passCode = "12345";
    HashMap<String, HotelData> hotelDetails = new HashMap<>();
    HashMap<Integer,UserData> userDetails = new HashMap<>();
    HotelSorting sorting = new HotelSorting();
    int userId = 100;
    public boolean adminCheck(String userName, String password) {
        if(userName.equals(adminName) && password.equals(passCode)){
            return true;
        }
        return false;
    }

    public String addHotel(HotelData hotel) {
        String hotelName = hotel.getHotelName();
        hotelDetails.put(hotelName,hotel);
//         HashMap<String,HotelData> temp = hotelDetails.getOrDefault(city,new HashMa);
//         temp.put(hotelName,hotel);
//         hotelDetails.put(city,temp);
//        System.out.println(hotelDetails);
         return "Hotel Added Successfully";
    }
    public List<Map.Entry<String,HotelData>> sortByName(){
        List<Map.Entry<String,HotelData>> nameList= sorting.sortByName(hotelDetails);
        return nameList;
    }
    public List<Map.Entry<String,HotelData>> sortByRating(){
        List<Map.Entry<String,HotelData>> ratingList= sorting.sortByRating(hotelDetails);
        return ratingList;
    }
    public List<Map.Entry<String,HotelData>> sortByAvailableRoom(){
        List<Map.Entry<String,HotelData>> roomList= sorting.sortByAvailableRoom(hotelDetails);
        return roomList;
    }
    public String searchByLocation(String city){
        String str = "";
        for(Map.Entry<String,HotelData> e : hotelDetails.entrySet()){
            if(e.getValue().getCity().equals(city)){
                str = str + e.getValue().getHotelName()+"            "+e.getValue().getRoomAvailable()+"             "+e.getValue().getCity()+"      "+e.getValue().getRating()+"      "+e.getValue().getCost()+"\n";
            }
        }
        return str;
    }

    public boolean hotelCheck(String hotelName) {
        if(hotelDetails.containsKey(hotelName)){
            return true;
        }
        return false;
    }

    public UserData bookHotel(String hotelName, String name) {
        HotelData hotel = hotelDetails.get(hotelName);
        double cost = hotel.getCost();
        int room = hotel.getRoomAvailable();
        room = room - 1;
        hotel.setRoomAvailable(room);
        hotelDetails.put(hotelName,hotel);

        UserData user = new UserData();
        user.setName(name);
        user.setUserId(userId);
        user.setHotelName(hotelName);
        user.setCost(cost);
        userDetails.put(userId,user);
        userId++;
        return user;
    }

    public HashMap<Integer, UserData> bookingDetails() {
        return userDetails;
    }
}
