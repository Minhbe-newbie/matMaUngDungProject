package android.web.helper;

public class Utils {
    public static final String EMAIL = "kma.at160541@gmail.com";
    public static final String PASSWORD = "bzabwfubpzzwzabc";
    public static  String convert10toC (Float he10){
        if(he10 >= 8.5f && he10 <= 10f) return "A";
        if(he10 >= 8.0f && he10 < 8.5f) return "B+";
        if(he10 >= 7.0f && he10 < 8.0f) return "B";
        if(he10 >= 6.5f && he10 < 7.0f) return "C+";
        if(he10 >= 5.5f && he10 < 6.5f) return "C";
        if(he10 >= 5f && he10 < 5.5f) return "D+";
        if(he10 >= 4.0f && he10 < 5f) return "D";
        return "F";
    }
    public  static Float convert10to4(Float he10){
        if(he10 >= 8.5f && he10 <= 10f) return 4f;
        if(he10 >= 8.0f && he10 < 8.5f) return 3.5f;
        if(he10 >= 7.0f && he10 < 8.0f) return 3f;
        if(he10 >= 6.5f && he10 < 7.0f) return 2.5f;
        if(he10 >= 5.5f && he10 < 6.5f) return 2f;
        if(he10 >= 5f && he10 < 5.5f) return 1.5f;
        if(he10 >= 4.0f && he10 < 5f) return 1f;
        return 0f;
    }
}
