import java.time.LocalTime;
import java.util.ArrayList;

public class MFBook {
    static Student currentUser;
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Appointment> allAppts = new ArrayList<>();
    
    public static boolean checkTimeValidity(int hour1, int minute1, int hour2, int minute2) {
        if (hour1 < 0 || hour2 < 0 || minute1 < 0 || minute2 < 0) {
            System.out.println("less than 0");
            return false;
        } else if (hour1 > hour2) {
            System.out.println("case 2");
            return false;
        } else if (hour1 == hour2 && minute1 > minute2) {
            System.out.println("case 3");
            return false;
        } else if (minute1 > 59 || minute2 > 59) {
            System.out.println("case 4");
            return false;
        } else if (hour1 > 21 || hour2 > 21 ) {
            System.out.println("case 5");
            return false;
        } else if (hour1 < 8 || hour2 < 8) {
            System.out.println("case 6");
            return false;
        } 
        LocalTime now = LocalTime.now();
        int currentHour = now.getHour();
        int currentMinute = now.getMinute();
        if (hour1 < currentHour) {
            System.out.println("Booking start time is in the past!");
            return false;
        }else if (hour1 == currentHour && minute1 < currentMinute) {
            System.out.println("Booking start time is in the past!");
            return false;
        }
        for (Appointment appt : allAppts) {
            int startA = hour1 * 60 + minute1;
            int endA = hour2 * 60 + minute2;

            int startB = appt.hour1 * 60 + appt.minute1;
            int endB = appt.hour2 * 60 + appt.minute2;
            if (startA < endB && startB < endA) {
                return false;
            }
        }
        return true;
    }
    
    public static void addAppt(Appointment appt) {
        allAppts.add(appt);
    }
    
//    public void randomAppts() {
//        int amount = (int) (Math.random() * 10);
//        for (int i = 0; i < amount; i++) {
//            int hour1 = (int) (Math.random() * 13) + 8;
//            int minute1 = (int) (Math.random() * 6) * 10;
//            int hour2 = hour1 + (int) (Math.random() * 2);
//            int minute2 = (int) (Math.random() * 6) * 10;
//            int roomNo = (int) (Math.random() * 14);
//            allAppts.add(new Appointment(roomNo, hour1, minute1, hour2, minute2));
//        }
//    }
    
    public static void main(String args[]) {
        Login login = new Login();
        login.setVisible(true);
    }
}
