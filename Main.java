import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        DataStore.initializeRooms();

        while (true) {
            System.out.println("\n MEENAKSHI GLOBAL HOTEL SYSTEM");
            System.out.println("1. View Rooms");
            System.out.println("2. Search by Country");
            System.out.println("3. Book Room");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> viewRooms();
                case 2 -> searchRooms();
                case 3 -> bookRoom();
                case 4 -> viewBookings();
                case 5 -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }
        }
    }

    static void viewRooms() {
        for (Room r : DataStore.rooms) {
            System.out.println(r.roomNo + " | " + r.type + " | ₹" + r.price + " | " + (r.available ? "Available" : "Booked"));
        }
    }

    static void searchRooms() {
        System.out.print("Enter country (India/Dubai/Korea): ");
        String country = sc.nextLine();

        for (Room r : DataStore.rooms) {
            if (r.type.toLowerCase().contains(country.toLowerCase())) {
                System.out.println(r.roomNo + " | " + r.type + " | ₹" + r.price);
            }
        }
    }

    static void bookRoom() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter room number: ");
        int roomNo = sc.nextInt();

        for (Room r : DataStore.rooms) {
            if (r.roomNo == roomNo && r.available) {
                Customer c = new Customer(name, phone);
                Booking b = new Booking(c, r, r.type);

                DataStore.bookings.add(b);
                r.available = false;

                System.out.println("Booking Confirmed!");
                System.out.println("Bill: ₹" + r.price);
                return;
            }
        }
        System.out.println("Room not available!");
    }

    static void viewBookings() {
        for (Booking b : DataStore.bookings) {
            System.out.println(b.customer.name + " booked Room " + b.room.roomNo + " (" + b.room.type + ")");
        }
    }
}

class DataStore {
    static List<Room> rooms = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();

    static void initializeRooms() {
        rooms.add(new Room(101, "Single", 2000));
        rooms.add(new Room(102, "Double", 3500));
        rooms.add(new Room(103, "Suite", 7000));

        // Global Rooms
        rooms.add(new Room(201, "Dubai Luxury", 12000));
        rooms.add(new Room(301, "Korea Premium", 9000));
    }
}

class Hotel {
    String name;
    String country;

    Hotel(String name, String country) {
        this.name = name;
        this.country = country;
    }
}

class Room {
    int roomNo;
    String type;
    double price;
    boolean available;

    Room(int roomNo, String type, double price) {
        this.roomNo = roomNo;
        this.type = type;
        this.price = price;
        this.available = true;
    }
}

class Customer {
    String name;
    String phone;

    Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}

class Booking {
    Customer customer;
    Room room;
    String country;

    Booking(Customer customer, Room room, String country) {
        this.customer = customer;
        this.room = room;
        this.country = country;
    }
}

