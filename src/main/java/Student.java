public class Student {
    int stuID;
    String pswd;
    Appointment[] appointments = new Appointment[3];
    int count = 0;
    public Student(int stuID, String pswd) {
        this.stuID = stuID;
        this.pswd = pswd;
    }
    
    public void addAppt (Appointment newAppt) {
        if (count < 3) {
            this.appointments[count] = newAppt;
            count++;
        }
    }
    
    //TODO: 
}
