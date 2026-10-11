public class Appointment {
    Student student;
    int roomNo;
    int hour1;
    int hour2;
    int minute1;
    int minute2;
    //fhkdhfkwe
    
    public Appointment(Student student, int roomNo, int hour1, int minute1, int hour2, int minute2) {
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
