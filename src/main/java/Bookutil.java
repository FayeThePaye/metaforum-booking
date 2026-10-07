public class Bookutil {
    
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
        return true;
    }
}
