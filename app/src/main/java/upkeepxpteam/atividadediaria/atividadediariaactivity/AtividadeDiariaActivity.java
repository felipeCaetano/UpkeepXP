package upkeepxpteam.atividadediaria.atividadediariaactivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import upkeepxpteam.atividadediaria.atividadediariaDAO.AtividadeDiariaDAO;
import upkeepxpteam.atividadediaria.atividadediariabase.AtividadeDiaria;
import upkeepxpteam.upkeepxp.R;

public class AtividadeDiariaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CompactCalendarView compactCalendarView;
    private ListView eventos;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private AtividadeDiariaDAO atividadeDiariaDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_diaria);

        //Inicializa instancia que pega atividades da memória
        Context context = this.getApplicationContext();
        atividadeDiariaDAO = new AtividadeDiariaDAO(context);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        compactCalendarView = findViewById(R.id.compactcalendar_view);
        compactCalendarView.shouldDrawIndicatorsBelowSelectedDays(true);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

        Date today = new Date();
        compactCalendarView.setCurrentDate(today);

        String data = dateFormat.format(today);
        Log.d("Data de Hoje", data);
        final List<Event> events = compactCalendarView.getEvents(today);
        final List<String> dadosAtividade = new ArrayList<>();
        Log.d("Hoje tem: ", ""+events);

        List<AtividadeDiaria> atividadeDiarias = atividadeDiariaDAO.selecionaAtividaPorDia(data);
        Log.d("Atividades tem: ", ""+atividadeDiarias);
        for(AtividadeDiaria atividade: atividadeDiarias) {
            Event ev = new Event(Color.CYAN,today.getTime(),atividade);
            events.add(ev);
        }
        for (Event ev: events) {
            AtividadeDiaria atividadeDiaria = (AtividadeDiaria) ev.getData();
            /*Esse trecho add uma lista inteira num array list:
            String[] dados = {atividadeDiaria.getEquipeNome(), atividadeDiaria.getDescricao()};
            dadosAtividade.addAll(Arrays.asList(dados));
             */
            dadosAtividade.add(""+atividadeDiaria.getEquipeNome()+"\n"+atividadeDiaria.getDescricao()); //Gambis detected: fazer um adapter customizado
        }
        Log.d("Hoje tem: ", ""+events);

        eventos = findViewById(R.id.lista_eventos);
        //final ArrayAdapter<Event> adaptadorEvents = new ArrayAdapter<>(AtividadeDiariaActivity.this,android.R.layout.simple_list_item_1,events);
        final ArrayAdapter<String> adaptadorEvents = new ArrayAdapter<>(AtividadeDiariaActivity.this,android.R.layout.simple_list_item_1,dadosAtividade);


        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_date_range);
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Setting default toolbar title to empty
        actionBar.setTitle("Atividades Diárias");


        eventos.setAdapter(adaptadorEvents);

        //set initial title
        actionBar.setTitle(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

        //set title on calendar scroll
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                //colocar aqui o metodo para exibir a listview das atividades
                criaListaEventos(dateClicked);
                final List<Event> events = compactCalendarView.getEvents(dateClicked);

                eventos.setAdapter(adaptadorEvents);

                Log.d("Data Clicada: ", dateClicked.toString());
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

        FloatingActionButton fab = findViewById(R.id.fab_atividade);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(AtividadeDiariaActivity.this, CadastraAtividadeActivity.class);
                startActivity(it);
            }
        });

        //addDummyEvents();

        //  gotoToday();

    }

    private void criaListaEventos(Date dateClicked) {
        //events = compactCalendarView.getEvents(dateClicked);
    }

    // Adding dummy events in calendar view for April, may, june 2016
    //este método é de testes
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

    //trocar para setHorario??
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
