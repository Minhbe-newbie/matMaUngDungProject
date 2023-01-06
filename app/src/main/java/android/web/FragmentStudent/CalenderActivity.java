package android.web.FragmentStudent;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.web.R;
import android.web.database.DatabaseClass;
import android.web.helper.JSONServices;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class CalenderActivity extends AppCompatActivity {

    ImageView backRed;
    private TextView text_info,tvMonth;
    private CalendarView calendarView;
    String note;
    String MaMon, Ma_Lop, Ten_Lop, PhongHoc, TG2;
    Date ThoiGianHoc;

    public Event AddEvent(String data, long date){
        Event ev1 = new Event(Color.RED, date, data);
        return ev1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        initViews();
        addEvent();
        final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        // Set first day of week to Monday, defaults to Monday so calling setFirstDayOfWeek is not necessary
        // Use constants provided by Java Calendar class
        compactCalendarView.setFirstDayOfWeek(Calendar.SUNDAY);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
//        compactCalendarView.setDayColumnNames("T2","T3","T4","T5","T6","T7","CN");

        // Tạo 1 sự kiện, Và vcl thời gian nó đòi kiểu theo dạng này?
        // https://www.epochconverter.com/ link lấy thời gian
        String data = "Lịch học hôm nay là:";
        String data2 = "Rất tiếc, mọi deadline dồn vào một ngày";

        ResultSet rs;
        rs = (ResultSet) DatabaseClass.getCalender();
        List<JSONObject> resObj = JSONServices.getFormattedResult(rs);
        if(!resObj.isEmpty()) {
            System.out.println(resObj);
            for (int i = 0; i < resObj.size(); i++) {
                try {
                    note = (String) resObj.get(i).get("GhiChu");
                    MaMon = (String) resObj.get(i).get("MaMon");
                    Ma_Lop = (String) resObj.get(i).get("Ma_Lop");
                    Ten_Lop = (String) resObj.get(i).get("Ten_Lop");
                    PhongHoc = (String) resObj.get(i).get("PhongHoc");
                    TG2 = (String) resObj.get(i).get("ThoiGianHoc");


                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    ThoiGianHoc = sdf.parse((String) resObj.get(i).get("ThoiGianHoc"));
                    System.out.println(ThoiGianHoc);

                    String Subject = Ma_Lop + " " + Ten_Lop + " " +PhongHoc + " " + TG2.substring(11,16) + " -> "+ TG2.substring(11,16);

                    String Sub2 = Ten_Lop+"(AT"+Ma_Lop.substring(Ma_Lop.length()-3,Ma_Lop.length())+") \n" +
                            PhongHoc + " " + TG2.substring(11,16) + "\n" +
                            note;


                    long millis = ThoiGianHoc.getTime();
                    System.out.println(millis);

                    compactCalendarView.addEvent(AddEvent(Sub2,millis));

                } catch (JSONException | ParseException e) {
                    e.printStackTrace();
                }
            }
        }

//        compactCalendarView.addEvent(AddEvent("Test",1673082000000L));
//
//        Event ev1 = new Event(Color.RED, 1673082000000L, data);
//        compactCalendarView.addEvent(ev1);
//
//        // Added event 2 GMT: Sun, 07 Jun 2015 19:10:51 GMT
//        Event ev2 = new Event(Color.RED, 1673168400000L,data2);
//        compactCalendarView.addEvent(ev2);
//
//        ev2 = new Event(Color.RED, 1673269200000L,data2);
//        compactCalendarView.addEvent(ev2);
//
//        ev2 = new Event(Color.RED, 1673276400000L,data2);
//        compactCalendarView.addEvent(ev2);
//        // Query for events on Sun, 07 Jun 2015 GMT.
//        // Time is not relevant when querying for events, since events are returned by day.
//        // So you can pass in any arbitary DateTime and you will receive all events for that day.
//        List<Event> events = compactCalendarView.getEvents(1673276400000L); // can also take a Date object
//
//        // events has size 2 with the 2 events inserted previously
//        Log.d(TAG, "Events: " + events);

        // Lấy thông tin của từng ngày một khi bấm vào
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                Log.d(TAG, "Day was clicked: " + dateClicked + " with events " + events);
//                text_info.setText("Day was clicked: " + dateClicked + " \n with events " + events);

                try {
//                    text_info.setText( String.valueOf(events.get(0).getTimeInMillis()));
                    String info = "";

                    if(!events.isEmpty()) {
                        System.out.println(events);
                        for (int i = 0; i < events.size(); i++) {
                                info += (String) events.get(i).getData()+"\n\n";
                            }
                        }

                text_info.setText(info);
                }
                catch (Exception e){
                    e.printStackTrace();
                }



            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
                text_info.setText("Month was scrolled to: " + firstDayOfNewMonth);
                System.out.println(firstDayOfNewMonth.getMonth());
                tvMonth.setText("Tháng "+(firstDayOfNewMonth.getMonth()+1) + " năm " + (firstDayOfNewMonth.getYear()-100+2000));
            }
        });
    }

    private void addEvent() {
        backRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

    }




    private void initViews() {
        backRed = findViewById(R.id.backRed);
        text_info = findViewById(R.id.text_info);
        tvMonth = findViewById(R.id.tvMonth);
    }
}