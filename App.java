import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  Welcome to SkyHigh Airlines! ✈️  ");
        System.out.println("========================================");

        Plane plane = new Plane();
        Scanner input = new Scanner(System.in);
        plane.loadBookingsFromFile();
        boolean cont = true;

        while (cont) {
            System.out.println("\n+----------------------------+");
            System.out.println("| 1) Buy a plane ticket      |");
            System.out.println("| 2) Show seating area       |");
            System.out.println("| 3) Cancel a booking        |");
            System.out.println("| 4) Show all the bookings   |");
            System.out.println("| 0) Quit                    |");
            System.out.println("+----------------------------+\n");
            System.out.print("Select an option: ");

            int option;
            if (input.hasNextInt()) {
                option = input.nextInt();
                input.nextLine();
            } else {
                System.out.println("Invalid input! Please enter a number.\n");
                input.nextLine(); // Clear invalid input
                continue;
            }

            switch (option) {
                case 0:
                    cont = false;
                    System.out.println("Thank you for using SkyHigh Airlines ✈️ !!");
                    break;
                case 1:
                    plane.bookTicket();
                    break;
                case 2:
                    plane.showSeatingArea();
                    break;
                case 3:
                    plane.cancelBooking();
                    break;
                case 4:
                    plane.showAllBookings();
                    break;
                default:
                    System.out.println("Invalid option! Please Try again.\n");
            }
        }
        input.close();
    }
}