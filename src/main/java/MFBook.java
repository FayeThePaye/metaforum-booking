
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MFBook {

    static Student currentUser;
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Appointment> allAppts = new ArrayList<>();

    public static boolean checkTimeValidity(int roomNo, Date date, 
            int hour1, int minute1, int hour2, int minute2) {
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        date = cal1.getTime();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date today = cal.getTime();
        if (date.before(today)) {
            return false;
        } else if (date.equals(today)) {
            LocalTime now = LocalTime.now();
            int currentHour = now.getHour();
            int currentMinute = now.getMinute();
            if (hour1 < currentHour) {
                System.out.println("Booking start time is in the past!");
                return false;
            } else if (hour1 == currentHour && minute1 < currentMinute) {
                System.out.println("Booking start time is in the past!");
                return false;
            }
        }

        if (hour1 < 0 || hour2 < 0 || minute1 < 0 || minute2 < 0) {
            System.out.println("less than 0");
            return false;
        } else if (hour1 > hour2) {
            System.out.println("case 2");
            return false;
        } else if (hour1 == hour2 && minute1 >= minute2) {
            System.out.println("case 3");
            return false;
        } else if (minute1 > 59 || minute2 > 59) {
            System.out.println("case 4");
            return false;
        } else if (hour1 > 21 || hour2 > 21) {
            System.out.println("case 5");
            return false;
        } else if (hour1 < 8 || hour2 < 8) {
            System.out.println("case 6");
            return false;
        }
        int start = hour1 * 60 + minute1;
        int end = hour2 * 60 + minute2;
        if (end - start > 120) {
            return false;
        }
        for (Appointment appt : allAppts) {
            int startA = hour1 * 60 + minute1;
            int endA = hour2 * 60 + minute2;

            int startB = appt.hour1 * 60 + appt.minute1;
            int endB = appt.hour2 * 60 + appt.minute2;
            if (appt.roomNo == roomNo && appt.dateStr.equals(
                    new SimpleDateFormat("dd/MM/yyyy").format(date)) 
                    && startA < endB && startB < endA) {
                System.out.println("overlap");
                return false;
            }
        }

        return true;
    }

    public static void addAppt(Appointment appt) {
        allAppts.add(appt);
    }

    //₊˚ ✧ ━━━━⊱⋆⊰━━━━ ✧ ₊˚
    //CSV file management (unclean code)
    //saveappt happens when: 1. new appt created 2. appt cancelled
    public static void saveApptToCSV() {
        String filePath = "appts.csv";

        try (java.io.PrintWriter pw
                = new java.io.PrintWriter(
                        new java.io.FileWriter(filePath, false))) {

            for (Appointment appt : allAppts) {
                pw.println(
                        appt.roomNo + ","
                        + appt.student.stuID + ","
                        + appt.dateStr + ","
                        + appt.hour1 + ","
                        + appt.minute1 + ","
                        + appt.hour2 + ","
                        + appt.minute2
                );
            }

        } catch (java.io.IOException e) {
            System.err.println(
                    "Error saving appointments: " + e.getMessage()
            );
        }
    }

    //loadappt happens when MFBook (main) is ran
    public static void loadApptFromCSV() {
        String filePath = "appts.csv";
        java.io.File file = new java.io.File(filePath);

        if (!file.exists()) {
            return;
        }
        //clear everythinghh
        MFBook.allAppts.clear();
        for (Student student : MFBook.students) {
            student.appointments.clear();
        }
        try (java.io.BufferedReader br
                = new java.io.BufferedReader(new java.io.FileReader(file))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 7) {
                    int roomNo = Integer.parseInt(data[0].trim());
                    int studentID = Integer.parseInt(data[1].trim());
                    String dateStr = data[2].trim();
                    int hour1 = Integer.parseInt(data[3].trim());
                    int minute1 = Integer.parseInt(data[4].trim());
                    int hour2 = Integer.parseInt(data[5].trim());
                    int minute2 = Integer.parseInt(data[6].trim());

                    //link id to student object
                    for (Student student : MFBook.students) {
                        if (student.stuID == studentID) {
                            Appointment appt = new Appointment(
                                    student, roomNo, dateStr,
                                    hour1, minute1, hour2, minute2
                            );
                            MFBook.allAppts.add(appt);
                            student.appointments.add(appt);
                            break;
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.err.println(
                    "Error loading appointments: " + e.getMessage()
            );
        }
    }

    //used when new account created (todo: cant make duplicate accounts)
    public static void saveAccountToCSV(int id, String password) {
        String filePath = "accounts.csv";
        try (java.io.FileWriter fw = new java.io.FileWriter(filePath, true); 
                java.io.BufferedWriter bw = new java.io.BufferedWriter(fw); 
                        java.io.PrintWriter pw = new java.io.PrintWriter(bw)) {
            pw.println(id + "," + password);
            System.out.println("account saved to ");
        } catch (java.io.IOException e) {
            System.err.println("Error saving account to CSV: " + e.getMessage());
        }
    }

    //used when MFBOOk is ran main
    private static void loadAccountsFromCSV() {
        String filePath = "accounts.csv";
        java.io.File file = new java.io.File(filePath);

        if (!file.exists()) {
            return; 
        }

        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    int id = Integer.parseInt(data[0].trim());
                    String password = data[1].trim();
                    MFBook.students.add(new Student(id, password));
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading accounts from CSV: " + e.getMessage());
        }
    }

    //used alongside load account from CSV because to restore Appointments 
    //arraylist after initialization (since not in csv file)
    public static void restoreAppts2Stu() {

        for (Student student : MFBook.students) {
            student.appointments.clear();
        }

        for (Appointment appt : MFBook.allAppts) {
            for (Student student : MFBook.students) {
                if (student.stuID == appt.student.stuID) {
                    student.appointments.add(appt);
                    break;
                }
            }
        }
    }
    //₊˚ ✧ ━━━━⊱⋆⊰━━━━ ✧ ₊˚

    public static void main(String args[]) {
        loadAccountsFromCSV();
        loadApptFromCSV();
        restoreAppts2Stu();
        try {
            javax.swing.UIManager.setLookAndFeel(
                    new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        Login login = new Login();
        login.setVisible(true);
    }
}
