import java.util.*;

public class Booking {
    static Scanner sc = new Scanner(System.in);
    static Passenger[] available_berth = new Passenger[12];
    static Passenger[] wait_list = new Passenger[5];
    static Passenger[] rac = new Passenger[5];

    public void booking() {
        boolean booked = false;
        System.out.println("enter passenger details like 'name' 'age' preference like 'u','m','l','n'");
        String name = sc.nextLine();
        int age = sc.nextInt();
        sc.nextLine();
        String preference = sc.nextLine();
        Passenger new_passenger = new Passenger(name, age, preference);

        if (preference.equals("u")) {
            for (int i = 0; i < available_berth.length; i += 3) {
                if (available_berth[i] == null) {
                    available_berth[i] = new_passenger;
                    booked = true;
                    Passenger.id++;
                    break;
                }
            }
        } else if (preference.equals("m")) {
            for (int i = 1; i < available_berth.length; i += 3) {
                if (available_berth[i] == null) {
                    available_berth[i] = new_passenger;
                    booked = true;
                    Passenger.id++;
                    break;
                }
            }
        } else if (preference.equals("l")) {
            for (int i = 2; i < available_berth.length; i += 3) {
                if (available_berth[i] == null) {
                    available_berth[i] = new_passenger;
                    booked = true;
                    Passenger.id++;
                    break;
                }
            }
        } else if (preference.equals("n")) {
            for (int i = 0; i < available_berth.length; i++) {
                if (available_berth[i] == null) {
                    available_berth[i] = new_passenger;
                    booked = true;
                    Passenger.id++;
                    break;
                }
            }
        }

        if (booked) {
            System.out.println("ticket successfully booked!");
            System.out.println("your ticket id is :" + new_passenger.passenger_id);
            System.out.println("your berth is :" + preference);
            System.out.println("your name is :" + new_passenger.name);
            System.out.println("your age is :" + new_passenger.age);
        } else {
            System.out.println("No berth available.");
        }
    }

    public void book_rac() {
        boolean booked = false, waiting = true;
        System.out.println("enter passenger details like 'name' 'age' preference like 'u','m','l','n'");
        String name = sc.nextLine();
        int age = sc.nextInt();
        sc.nextLine();
        String preference = sc.nextLine();
        Passenger new_passenger = new Passenger(name, age, preference);

        for (int i = 0; i < rac.length; i++) {
            if (rac[i] == null) {
                rac[i] = new_passenger;
                booked = true;
                Passenger.id++;
                break;
            }
        }

        if (booked) {
            System.out.println("RAC ticket successfully booked!");
            System.out.println("your ticket id is :" + new_passenger.passenger_id);
            System.out.println("your berth is RAC");
            System.out.println("your name is :" + new_passenger.name);
            System.out.println("your age is :" + new_passenger.age);
        } else {
            for (int i = 0; i < wait_list.length; i++) {
                if (wait_list[i] == null) {
                    wait_list[i] = new_passenger;
                    waiting = false;
                    Passenger.id++;
                    System.out.println("your ticket is in waitlist number " + i);
                    break;
                }
            }
            if (waiting) System.out.println("unfortunately your ticket is not booked!");
        }
    }

    public void available_tickets() {
        int avl = 0, rac_count = 0, wait_count = 0;
        for (int i = 0; i < available_berth.length; i++) if (available_berth[i] == null) avl++;
        for (int i = 0; i < rac.length; i++) if (rac[i] == null) rac_count++;
        for (int i = 0; i < wait_list.length; i++) if (wait_list[i] == null) wait_count++;
        System.out.println("number of available berth: " + avl);
        System.out.println("number of RAC available: " + rac_count);
        System.out.println("number of waitlist available: " + wait_count);
    }

    public void cancel() {
        System.out.println("enter the passenger_id to cancel your ticket");
        int passenger_id = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < available_berth.length; i++) {
            if (available_berth[i] != null && available_berth[i].passenger_id == passenger_id) {
                available_berth[i] = null;
                System.out.println("Your ticket is canceled successfully!");
                return;
            }
        }
        for (int i = 0; i < rac.length; i++) {
            if (rac[i] != null && rac[i].passenger_id == passenger_id) {
                rac[i] = null;
                System.out.println("Your RAC ticket is canceled successfully!");
                swift(i, rac.length, rac, passenger_id, 1);
                add_priority_waiting(wait_list[0], rac);
                return;
            }
        }
    }

    public static void swift(int start, int end, Passenger[] queue, int passenger_id, int key) {
        if (queue[end - 1] != null && queue[end - 1].passenger_id == passenger_id) {
            queue[end - 1] = null;
            if (key == 1) System.out.println("Your ticket is canceled successfully!");
        } else {
            for (int i = start; i < end - 1; i++) {
                queue[i] = queue[i + 1];
            }
            queue[end - 1] = null;
        }
    }

    public static void add_priority_waiting(Passenger wait, Passenger[] queue) {
        for (int i = 0; i < rac.length; i++) {
            if (rac[i] == null) {
                rac[i] = wait;
                break;
            }
        }
        swift(0, wait_list.length, wait_list, wait.passenger_id, 0);
    }
}
