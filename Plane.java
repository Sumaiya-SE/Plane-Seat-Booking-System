import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

public class Plane {

    private int[][] planeSeats; // 0 = available, 1 = booked
    private String[][] passengerNames;
    private String[][] passengerEmails;
    private int[] pricePerRow;
    private String[] seatClasses;
    private Scanner input = new Scanner(System.in);
    private final String FILE_NAME = "bookings.txt";

    public Plane() {
        initialisePlane();
    }

    private void initialisePlane() {
        planeSeats = new int[4][];
        passengerNames = new String[4][];
        passengerEmails = new String[4][];
        pricePerRow = new int[4];
        seatClasses = new String[4];

        // Assign seats per row
        planeSeats[0] = new int[4];  // First class
        planeSeats[1] = new int[6];  // Business
        planeSeats[2] = new int[8];  // Economy
        planeSeats[3] = new int[8];  // Economy

        passengerNames[0] = new String[4];
        passengerNames[1] = new String[6];
        passengerNames[2] = new String[8];
        passengerNames[3] = new String[8];

        passengerEmails[0] = new String[4];
        passengerEmails[1] = new String[6];
        passengerEmails[2] = new String[8];
        passengerEmails[3] = new String[8];

        // Prices
        pricePerRow[0] = 150; // First
        pricePerRow[1] = 120; // Business
        pricePerRow[2] = 70;  // Economy
        pricePerRow[3] = 70;  // Economy

        // Classes
        seatClasses[0] = "First";
        seatClasses[1] = "Business";
        seatClasses[2] = "Economy";
        seatClasses[3] = "Economy";
    }

    public void showSeatingArea() {
        System.out.println("\n========== PLANE SEATING MAP ==========");
        for (int row = 0; row < planeSeats.length; row++) {
            System.out.print(seatClasses[row] + " Row " + (row + 1) + " (£" + pricePerRow[row] + "): ");
            for (int seat = 0; seat < planeSeats[row].length; seat++) {
                String label = (char) ('A' + seat) + "" + (row + 1);
                if (planeSeats[row][seat] == 1) {
                    System.out.print("[Booked] "); // booked
                } else {
                    System.out.print("[" + label + "] "); // available
                }
            }
            System.out.println();
        }
        System.out.println("\nLegend: [Label] = Available, [Booked] = Booked\n");
    }

    public void showAllBookings() {
        System.out.println("\n========== ALL BOOKINGS ==========\n");
        boolean hasBookings = false;

        for (int row = 0; row < planeSeats.length; row++) {
            for (int seat = 0; seat < planeSeats[row].length; seat++) {
                if (planeSeats[row][seat] == 1) {
                    hasBookings = true;
                    String seatLabel = (char) ('A' + seat) + "" + (row + 1);
                    System.out.println("•• " + passengerNames[row][seat] + " (" + passengerEmails[row][seat] +
                            ") - " + seatClasses[row] + " Class, Seat- " + seatLabel +
                            " - £" + pricePerRow[row]);
                }
            }
        }

        if (!hasBookings) {
            System.out.println("No bookings found.");
        }
        System.out.println("\n==================================\n");
    }

    public void bookTicket() {
        String name = getValidName();
        String email = getValidEmail();

        int row = getRowNumber();
        int seat = getSeatNumber(row);

        if (planeSeats[row][seat] == 0) {
            planeSeats[row][seat] = 1;
            passengerNames[row][seat] = name;
            passengerEmails[row][seat] = email;

            int price = pricePerRow[row];
            String seatLabel = (char) ('A' + seat) + "" + (row + 1);
            Payment pay = new Payment(name, email, price, seatLabel, seatClasses[row]);
            pay.printPayment();

            System.out.println("\n✅ Booking successful for " + name +
                    " (" + email + ") in " + seatClasses[row] +
                    " Class, Seat " + seatLabel + ".");
            saveAllBookingsToFile();
        } else {
            System.out.println("❌ This seat is already booked. Try another one.");
        }
    }

    public void cancelBooking() {
        int row = getRowNumber();
        int seat = getSeatNumber(row);

        if (planeSeats[row][seat] == 1) {
            System.out.println("\n❌ Booking for " + passengerNames[row][seat] +
                    " (" + passengerEmails[row][seat] + ") is cancelled now.");
            planeSeats[row][seat] = 0;
            passengerNames[row][seat] = null;
            passengerEmails[row][seat] = null;
            saveAllBookingsToFile();
        } else {
            System.out.println("⚠️This seat is not booked yet.");
        }
    }

    // ---------------- Input Validation ----------------
    private String getValidName() {
        String name;
        while (true) {
            System.out.print("Enter your name: ");
            name = input.nextLine().trim();
            if (name.matches("[a-zA-Z\\s]+") && name.length() >= 2) break;
            System.out.println("❌ Invalid name. Use only letters and spaces (min 2 characters).");
        }
        return name;
    }
    private String getValidEmail() {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        while (true) {
            System.out.print("Enter your email: ");
            String email = input.nextLine().trim();
            // Compile regex into a Pattern
            Pattern pattern = Pattern.compile(regex);
            // Match the input email against the pattern
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                return email;
            }
            System.out.println("❌ Invalid email format. Please try again.");
        }
    }

    private int getRowNumber() {
        // Show class information before asking for row number
        System.out.println("\n--- Available Classes ---");
        System.out.println("Row 1: First Class (£150) - 4 seats (A-D)");
        System.out.println("Row 2: Business Class (£100) - 6 seats (A-F)");
        System.out.println("Rows 3-4: Economy Class (£50) - 8 seats each (A-H)");
        System.out.println("--------------------------");

        int row;
        while (true) {
            System.out.print("Enter row number (1-" + planeSeats.length + "): ");
            if (input.hasNextInt()) {
                row = input.nextInt() - 1;
                input.nextLine();
                if (row >= 0 && row < planeSeats.length)
                    break;
            }
            else {
                input.nextLine(); // Clear invalid input
            }
            System.out.println("❌ Invalid row! Try again.");
        }
        return row;
    }

    private int getSeatNumber(int row) {
        int seat;
        while (true) {
            System.out.print("Enter seat letter (A-" + (char)('A' + planeSeats[row].length - 1) + "): ");
            String seatInput = input.nextLine().toUpperCase();

            if (seatInput.length() == 1) {
                char seatChar = seatInput.charAt(0);
                seat = seatChar - 'A';
                if (seat >= 0 && seat < planeSeats[row].length) break;
            }
            System.out.println("❌ Invalid seat! Try again.");
        }
        return seat;
    }

    // ---------------- File Handling ----------------
    private void saveAllBookingsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < planeSeats.length; i++) {
                for (int j = 0; j < planeSeats[i].length; j++) {
                    if (planeSeats[i][j] == 1) {
                        String label = (char) ('A' + j) + "" + (i + 1);
                        writer.println("Row: " + (i + 1)
                                + " | Seat: " + label
                                + " | Class: " + seatClasses[i]
                                + " | Passenger: " + passengerNames[i][j]
                                + " | Email: " + passengerEmails[i][j]
                                + " | Price: £" + pricePerRow[i] + "\n");
                    }
                }
            }
            System.out.println("✅ Booking information saved to file successfully.");
        } catch (IOException e) {
            System.out.println("⚠️ Error saving bookings: " + e.getMessage());
        }
    }

    public void loadBookingsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Booking with an empty plane.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int loadedCount = 0;
            while ((line = reader.readLine()) != null) {
                // Parse the new format: Row: X | Seat: X | Class: X | Passenger: X | Email: X | Price: £X
                String[] parts = line.split("\\|");
                if (parts.length >= 6) {
                    try {
                        // Extract row number
                        String rowStr = parts[0].split(":")[1].trim();
                        int row = Integer.parseInt(rowStr) - 1;

                        // Extract seat letter
                        String seatStr = parts[1].split(":")[1].trim();
                        char seatChar = seatStr.charAt(0);
                        int seat = seatChar - 'A';

                        // Extract passenger name
                        String name = parts[3].split(":")[1].trim();
                        // Extract email
                        String email = parts[4].split(":")[1].trim();

                        // Validate row and seat indices
                        if (row >= 0 && row < planeSeats.length &&
                                seat >= 0 && seat < planeSeats[row].length) {
                            planeSeats[row][seat] = 1;
                            passengerNames[row][seat] = name;
                            passengerEmails[row][seat] = email;
                            loadedCount++;
                        }
                    }
                    catch (Exception e) {
                        System.out.println("⚠️ Error parsing line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error loading bookings: " + e.getMessage());
        }
    }
}