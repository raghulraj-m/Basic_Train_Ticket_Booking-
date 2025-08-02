# 🚆 Basic Train Ticket Booking System

A simple Java console-based application for managing train ticket bookings, RAC (Reservation Against Cancellation), and waitlists.

---

## 📌 Features

- ✅ Book tickets based on berth preferences (Upper, Middle, Lower)
- ✅ Automatically assigns RAC if no berths are available
- ✅ Automatically assigns Waitlist if RAC is full
- ✅ Ticket cancellation with automatic promotion from RAC or waitlist
- ✅ Real-time availability check for:
  - Berths
  - RAC
  - Waitlist

---

## 🧑‍💻 Classes

### `Booking.java`
Handles all core logic:
- Booking
- RAC management
- Waitlist
- Cancellation
- Status checking

### `Passenger.java`
Represents a passenger with details like:
- Name
- Age
- Berth preference
- Unique passenger ID

---

## 🔄 Booking Flow

1. **User provides name, age, and berth preference (`u`, `m`, `l`, or `n`)**
2. **System assigns a berth if available**
3. **If full, assigns RAC or puts the user on the waitlist**
4. **On cancellation, passengers from RAC and waitlist are promoted automatically**

---

## 📋 How to Run

1. Compile the files:
   ```bash
   javac Booking.java Passenger.java TrainTicketBookingapp.java
