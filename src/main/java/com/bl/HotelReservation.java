package com.bl;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class HotelReservation{

    static Scanner scanner = new Scanner(System.in);
    ArrayList<Hotel> hotelList = new ArrayList<>();
    ArrayList<Hotel> hotelListinRange = new ArrayList<>();
    ArrayList<Hotel> lowCostHotelList = new ArrayList<>();

    DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    int minPrice = 0;
    int bestRated = 0;
    static String startDate = null;
    static String endDate = null;

    public static void main(String args[]) {
        int X= 1;
        HotelReservation main = new HotelReservation();
        while (X != 0) {
            System.out.println("Welcome to Hotel Reservation System");
            System.out.println("-------------------------------");
            System.out.println("1. Create hotel Data");
            System.out.println("2. Show Hotels");
            System.out.println("3. Find Low Cost Hotel");
            System.out.println("4. Find Low cost Top Rated Hotel");
            System.out.println("5. Find Top Rated Hotel");
            System.out.println("0. Exit");
            System.out.println("-------------------------------");
            System.out.println("Choose your option:");
            X = scanner.nextInt();
            switch (X) {
                // Case statements
                case 1: // UC 1 // UC5
                    System.out.println("Enter the number of records you want to create...");
                    main.createHotelData(scanner.nextInt());
                    break;
                case 2:
                    System.out.println("Hotel Records are .....");
                    main.showHotels();
                    break;
                case 3: // UC 2
                    main.getDateRange();
                    main.findLowCostAnyHotel(startDate, endDate);
                    break;
                case 4: // UC 6
                    main.getDateRange();
                    main.findLowCostBestHotel(startDate, endDate);
                    break;
                case 5: // UC 7
                    main.getDateRange();
                    main.findBestRatedHotel(startDate, endDate);
                    break;

                case 0:
                    break;
                // Default case statement
                default:
                    System.out.println("Wrong option");
            }
        }

    }

    private void getDateRange() {
        //date range UC
        System.out.println("Enter the range for date(format should be 01/12/1999) .....");
        System.out.println("Start date: ");
        startDate = scanner.next();
        System.out.println("End date: ");
        endDate = scanner.next();
    }

    private void findLowCostAnyHotel(String startDate, String endDate) {

        LocalDate date1 = LocalDate.parse(startDate, dateformatter);
        LocalDate date2 = LocalDate.parse(endDate, dateformatter);
        ArrayList<Hotel> hotelListinRange = new ArrayList<>();
        for (Hotel hotelObject : hotelList) {
            if (hotelObject.getHotelDate().isAfter(date1) && hotelObject.getHotelDate().isBefore(date2))
            {
//to get date range before and after...
                hotelListinRange.add(hotelObject);
            }
        }
        if (!hotelListinRange.isEmpty()) {
            minPrice = hotelListinRange.get(0).getHotelPrice();
            Hotel hotel = hotelListinRange.get(0);
            for (int i = 1; i < hotelListinRange.size(); i++)
            {
                if (hotelListinRange.get(i).getHotelPrice() < minPrice) {
                    minPrice = hotelListinRange.get(i).getHotelPrice();
                    hotel = hotelListinRange.get(i);
                }
            }

            System.out.println("Hotel with cheapRate from " + date1 + " to " + date2);
            System.out.println("HotelName = " + hotel.getHotelName() + " Rate = " + hotel.getHotelPrice());
        }
    }

    private void findLowCostBestHotel(String startDate, String endDate) {

        LocalDate date1 = LocalDate.parse(startDate, dateformatter);
        LocalDate date2 = LocalDate.parse(endDate, dateformatter);

        for (Hotel hotelObject : hotelList) {
            if (hotelObject.getHotelDate().isAfter(date1) && hotelObject.getHotelDate().isBefore(date2)) {

                hotelListinRange.add(hotelObject); // list of hotels in date
                // range
            }
        }

        if (!hotelListinRange.isEmpty()) {

            minPrice = hotelListinRange.get(0).getHotelPrice();
            Hotel hotel = hotelListinRange.get(0);

            for (int i = 1; i < hotelListinRange.size(); i++) {
                if (hotelListinRange.get(i).getHotelPrice() <= minPrice) {
                    minPrice = hotelListinRange.get(i).getHotelPrice();
                    hotel = hotelListinRange.get(i);
                    lowCostHotelList.add(hotel); // list of hotels having min cost

                }
            }

            if (!lowCostHotelList.isEmpty()) {
                bestRated = lowCostHotelList.get(0).getHotelRate();
                Hotel hotel2 = lowCostHotelList.get(0);
                for (int i = 1; i < lowCostHotelList.size(); i++) {

                    if (lowCostHotelList.get(i).getHotelRate() > bestRated) {
                        bestRated = lowCostHotelList.get(i).getHotelRate();
                        hotel2 = lowCostHotelList.get(i);
                    }
                }

                System.out.println("Hotel with lowCost and BestRated from " + date1 + " to " + date2);
                System.out.println("HotelName = " + hotel2.getHotelName() + " Rate = " + hotel2.getHotelPrice());
            }
            else
            {
                System.out.println("Hotel with lowCost and BestRated from " + date1 + " to " + date2);
                System.out.println("HotelName = " + hotel.getHotelName() + " Rate = " + hotel.getHotelPrice());

            }
        }
    }

    private void findBestRatedHotel(String startDate, String endDate) {

        LocalDate date1 = LocalDate.parse(startDate, dateformatter);
        LocalDate date2 = LocalDate.parse(endDate, dateformatter);
        ArrayList<Hotel> hotelListinRange = new ArrayList<>();
        for (Hotel hotelObject : hotelList) {
            if (hotelObject.getHotelDate().isAfter(date1) && hotelObject.getHotelDate().isBefore(date2)) {

                hotelListinRange.add(hotelObject);
            }
        }
        if (!hotelListinRange.isEmpty()) {
            bestRated = hotelListinRange.get(0).getHotelRate();
            Hotel hotel = hotelListinRange.get(0);
            for (int i = 1; i < hotelListinRange.size(); i++) {
                if (hotelListinRange.get(i).getHotelPrice() > bestRated) {
                    bestRated = hotelListinRange.get(i).getHotelPrice();
                    hotel = hotelListinRange.get(i);
                }
            }

            System.out.println("Best Hotel rated from " + date1 + " to " + date2);
            System.out.println("HotelName = " + hotel.getHotelName() + " Rate = " + hotel.getHotelPrice());
        }
    }

    private void showHotels() {
        int i = 0;

        for (Hotel hotelObject : hotelList) {
            //enhanced for loop ,no need to define so
            i++;
            System.out.println(
                    i + " : " + "HotelName = " + hotelObject.getHotelName() + " Rank = " + hotelObject.getHotelRate()
                            + " Rate = " + hotelObject.getHotelPrice() + " Date = " + hotelObject.getHotelDate());
        }
    }

    private void createHotelData(int num) {

        for (int i = 0; i < num; i++) {

            System.out.println("Enter the name of hotel...");
            String hotelName = scanner.next();

            System.out.println("Enter the Price of hotel...");
            int hotelPrice = scanner.nextInt();

            System.out.println("Enter the date of rate (format should be 01/12/1999)...");
            String date = scanner.next();

            System.out.println("Give the Rating for this hotel out of 5...");
            int hotelRate = scanner.nextInt();

            LocalDate hotelDate = LocalDate.parse(date, dateformatter);

            hotelList.add(new Hotel(hotelName, hotelPrice, hotelRate, hotelDate));
        }
    }
}

class Hotel {
    String hotelName;
    int hotelPrice;
    int hotelRate;
    LocalDate hotelDate;

    public Hotel(String hotelName, int hotelPrice, int hotelRate, LocalDate hotelDate) {
        super();
        this.hotelName = hotelName;
        this.hotelPrice = hotelPrice;
        this.hotelRate = hotelRate;
        this.hotelDate = hotelDate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public int getHotelPrice() {
        return hotelPrice;
    }

    public int getHotelRate() {
        return hotelRate;
    }

    public LocalDate getHotelDate() {
        return hotelDate;
    }

}
