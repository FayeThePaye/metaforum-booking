
import java.util.Date;

public class Appointment {

    String dateStr;
    Student student;
    String roomNo;
    int hour1;
    int hour2;
    int minute1;
    int minute2;
    //fhkdhfkwe

    public Appointment(Student student, String roomNo, String dateStr, int hour1, 
                int minute1, int hour2, int minute2) {
        this.dateStr = dateStr;
        this.roomNo = roomNo;
        this.hour1 = hour1;
        this.hour2 = hour2;
        this.minute1 = minute1;
        this.minute2 = minute2;
        this.student = student;
    }

    public void setID(int stuID) {
        this.student.stuID = stuID;

    }

    //TODO: 
}
