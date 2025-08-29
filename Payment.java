public class Payment {
    private String email;
    private int amount;
    private String name;
    private String seatLabel;
    private String seatClass;

    public Payment(String name, String email, int amount, String seatLabel, String seatClass) {
        this.name = name;
        this.email = email;
        this.amount = amount;
        this.seatLabel = seatLabel;
        this.seatClass = seatClass;
    }

    public void printPayment() {
        System.out.println("\n--- Payment Details ---");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Class: " + seatClass);
        System.out.println("Seat: " + seatLabel);
        System.out.println("Amount: £" + amount);
        System.out.println("-----------------------\n");
    }

    // Getters
    public String getEmail() { return email; }
    public int getAmount() { return amount; }
    public String getName() { return name; }
    public String getSeatLabel() { return seatLabel; }
    public String getSeatClass() { return seatClass; }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}