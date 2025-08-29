# ✈️ Plane-Seat-Booking-System

A simple **Java console application** that simulates an **plane seat booking system**.  
This project demonstrates **file handling, input validation, OOP concepts, and user interaction** in Java.  

## 🚀 Features

- ✅ **View seating map** with labeled seats (A1, B2, C3 …)  
- ✅ **Book seats** with passenger name & email  
- ✅ **Cancel bookings** with confirmation messages  
- ✅ **Seat availability** per row and overall statistics  
- ✅ **Payment system** (amount shown with passenger email)  
- ✅ **File handling**: Bookings are saved to `bookings.txt`  
- ✅ **Input validation** for rows, seat numbers, and email format  


## 🏗️ Project Structure

├── Main.java # Application entry point

├── Plane.java # Handles seating, booking, canceling, file handling

├── Payment.java # Manages payment details

├── bookings.txt # Stores all booked and cancelled tickets

└── README.md # Project documentation


## 💻 Technologies Used

- **Java** (JDK 8)
- **OOP principles** (classes, objects, methods)
- **File Handling** (read/write to text file)
- **Input Validation**

## 📂 File Handling

- All bookings are stored in `bookings.txt` in a **human-readable format**.  

- Example content:

  Passenger: Sumaiya Shahudheen
  Email: sumaiyashahu56@gmail.com
  Seat: B2 (Business Class Seat 02)
  Amount: £200


# ▶️How to Run the Project

1. Clone the repository and navigate into the project folder:

  ```bash
  git clone https://github.com/yourusername/plane-seat-booking-system.git
  cd plane-seat-booking-system
  ```

2. Compile the Java files:

  ```bash
  javac Main.java Plane.java Payment.java
  ```

3. Run the program:

  ```bash
  java Main
  ```


📜 License

This project is licensed under the MIT License.


👩‍💻 Author

Sumaiya Shahudheen
