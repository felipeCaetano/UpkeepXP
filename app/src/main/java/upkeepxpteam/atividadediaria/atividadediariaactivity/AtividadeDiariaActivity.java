package upkeepxpteam.atividadediaria.atividadediariaactivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import upkeepxpteam.upkeepxp.R;

public class AtividadeDiariaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    CompactCalendarView compactCalendarView;
    private ListView eventos;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    //para usar quando retirar o ano do formato (para ano atual)
    //private SimpleDateFormat dateFormatForCurMonth = new SimpleDateFormat("MMMM", Locale.getDefault());

    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());

    List<Event> events = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_diaria);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        eventos = findViewById(R.id.lista_eventos);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher_foreground);
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Setting default toolbar title to empty
        actionBar.setTitle(null);

        compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.shouldDrawIndicatorsBelowSelectedDays(true);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

        //set initial title
        actionBar.setTitle(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

        //set title on calendar scroll
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                //colocar aqui o metodo para exibir a listview das atividades
                criaListaEventos(dateClicked);
                final List<Event> events = compactCalendarView.getEvents(dateClicked);
                final ArrayAdapter<Event> adaptadorEvents = new ArrayAdapter<>(AtividadeDiariaActivity.this,android.R.layout.simple_list_item_1,events);
                eventos.setAdapter(adaptadorEvents);
                Toast.makeText(AtividadeDiariaActivity.this, "Date : " + dateClicked.toString(), Toast.LENGTH_SHORT).show();
                currentCalender.setTime(dateClicked);
                addEvents(compactCalendarView, currentCalender.get(Calendar.MONTH));
                Log.d("Eventos:", ""+ events);

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                // Changes toolbar title on monthChange
                actionBar.setTitle(dateFormatForMonth.format(firstDayOfNewMonth));

            }

        });


        addDummyEvents();

        //  gotoToday();


    }

    private void criaListaEventos(Date dateClicked) {
        events = compactCalendarView.getEvents(dateClicked);
    }

    // Adding dummy events in calendar view for April, may, june 2016
    private void addDummyEvents() {

        addEvents(compactCalendarView, Calendar.APRIL);
        addEvents(compactCalendarView, Calendar.MAY);
        addEvents(compactCalendarView, Calendar.JUNE);

        // Refresh calendar to update events
        compactCalendarView.invalidate();
    }


    // Adding events from 1 to 6 days

    private void addEvents(CompactCalendarView compactCalendarView, int month) {
        currentCalender.setTime(new Date());
        currentCalender.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = currentCalender.getTime();
        for (int i = 0; i < 6; i++) {
            currentCalender.setTime(firstDayOfMonth);
            if (month > -1) {
                currentCalender.set(Calendar.MONTH, month);
            }
            currentCalender.add(Calendar.DATE, i);
            setToMidnight(currentCalender);
            compactCalendarView.addEvent(new Event(Color.CYAN,currentCalender.getTimeInMillis(),"Evento"+i), true);
        }
    }


    private void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }


    public void gotoToday() {

        // Set any date to navigate to particular date
        compactCalendarView.setCurrentDate(Calendar.getInstance(Locale.getDefault()).getTime());


    }
}
