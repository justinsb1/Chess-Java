import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String[] week = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        String day = "Wed";
        String newDay = null;
        int j = Arrays.asList(week).indexOf(day);
        for(int i = 0, k = 0; i <= 2; i++, k++) {
            if(k >= week.length-1) {
                k = 0;
            }
            newDay = week[k];
        }

        System.out.println(newDay);
    }
}
