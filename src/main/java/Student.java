
import java.util.ArrayList;

public class Student {

    int stuID;
    String pswd;
    ArrayList<Appointment> appointments = new ArrayList<>();
    int count = 0;

    public Student(int stuID, String pswd) {
        this.stuID = stuID;
        this.pswd = pswd;
    }

    public void addAppt(Appointment newAppt) {
        if (count < 3) {
            this.appointments.set(count, newAppt);
            count++;
        }
    }

    //TODO: 
}
