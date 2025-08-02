import java.util.*;
public class TrainTicketBookingapp {
static	Booking bk = new Booking();
static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		while(true) {
			System.out.println("Enter 'book' to Book the ticket!");
			System.out.println("Enter 'book_rac' to Book rac ticket!");
			System.out.println("Enter 'cancel' to cancel the ticket!");
			System.out.println("Enter 'view' to see the available tickets!");
			String choice = sc.nextLine();
			switch(choice) {
			case "book":booking();break;
			case "cancel":cancel();break;
			case "view": view();break;
			case "book_rac":book_rac();break;
			default: System.out.println("invalid input");
			}
		}
	}
		public static void booking() {
			bk.booking();
		}
		public static void cancel() {
			bk.cancel();
		}
		public static void view() {
			bk.available_tickets();
		}
		public static void book_rac() {
			bk.book_rac();
		}
		
	

}
