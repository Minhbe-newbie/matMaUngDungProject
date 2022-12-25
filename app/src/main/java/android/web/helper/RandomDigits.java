package android.web.Login;

import java.util.concurrent.ThreadLocalRandom;

public class RandomDigits {
    public String RandomOTP(){
        int min = 100000;
        int max = 999999;
        int number = ThreadLocalRandom.current().nextInt(min,max+1 );
        return String.valueOf(number);
    }
}
