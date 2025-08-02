
import java.util.*;

public class TaxiBookingSystem {

    static ArrayList<Taxi> taxi = new ArrayList<>();
    static ArrayList<Booking> booking = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("enter the number to declare the number of taxi: ");
        int num_taxi = sc.nextInt();

        for (int i = 1; i <= num_taxi; i++) {
            taxi.add(new Taxi(i));
        }

        sc.nextLine(); // consume leftover newline

        while (true) {
            System.out.println("enter 'book' to book taxi:");
            String choice = sc.nextLine();
            switch (choice) {
                case "book":
                    book();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("invalid choice");
            }
        }
    }

    public static void book() {
        Scanner sc = new Scanner(System.in);

        System.out.print("enter pickup point (A-F): ");
        String pickup = sc.nextLine().trim();

        System.out.print("enter the drop point: ");
        String drop = sc.nextLine().trim();

        System.out.print("enter the pick up time: ");
        int pickup_time = sc.nextInt();

        int mini = 6;
        Taxi temp = null;
        int flag = 0;

        for (int i = 0; i < taxi.size(); i++) {
            if (taxi.get(i).current_time <= pickup_time
                    && mini > (taxi.get(i).current - 'A')) {
                mini = taxi.get(i).current - 'A';
                temp = taxi.get(i);
                flag = 1;
            }
        }

        if (temp != null) {
            for (int i = 0; i < taxi.size(); i++) {
                if (taxi.get(i).total < temp.total) {
                    temp = taxi.get(i);
                }
            }
        }

        if (flag == 0 || temp == null) {
            System.out.println("booking cancelled, no taxi allocated!");
        } else {
            Booking new_booking = new Booking(
                    temp.taxi_id,
                    pickup.charAt(0),
                    drop.charAt(0),
                    pickup_time
            );

            booking.add(new_booking);
            System.out.println("Taxi " + temp.taxi_id + " booked from "
                    + pickup + " to " + drop + " at " + pickup_time);

            // Update taxi state (if desired)
            temp.current = drop.charAt(0);
            temp.current_time = pickup_time + Math.abs(drop.charAt(0) - pickup.charAt(0));
            temp.total += Math.abs(drop.charAt(0) - pickup.charAt(0)) * 100;
        }
    }

}
