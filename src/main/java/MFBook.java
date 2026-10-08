import java.util.ArrayList;

public class MFBook {
    ArrayList<Appointment> allAppts = new ArrayList<>();
    
    public void randomAppts() {
        int amount = (int) (Math.random() * 10);
        for (int i = 0; i < amount; i++) {
            int hour1 = (int) (Math.random() * 13) + 8;
            int minute1 = (int) (Math.random() * 6) * 10;
            int hour2 = hour1 + (int) (Math.random() * 2);
            int minute2 = (int) (Math.random() * 6) * 10;
            int roomNo = (int) (Math.random() * 14);
            allAppts.add(new Appointment(roomNo, hour1, minute1, hour2, minute2));
        }
    }
    
    public static void main(String args[]) {
        
    }
}
