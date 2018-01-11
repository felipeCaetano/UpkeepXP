package upkeepxpteam.atividadediaria.atividadediariaactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import upkeepxpteam.atividadediaria.atividadediariaDAO.AtividadeDiariaDAO;
import upkeepxpteam.atividadediaria.atividadediariabase.AtividadeDiaria;
import upkeepxpteam.upkeepxp.R;

public class AtividadeDiariaActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private AtividadeDiariaDAO atividadeDiariaDAO;
    private List<AtividadeDiaria> atividadeDiariaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_diaria);

        //contexto para o objeto DAO
        final Context context = this.getApplicationContext();
        atividadeDiariaDAO = new AtividadeDiariaDAO(context);

        calendarView = findViewById(R.id.cv_calendar);
        //calendarView.setBackgroundColor(Color.WHITE); //esta no xml
        calendarView. setOnClickListener(new CalendarView.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long calendarDate = calendarView.getDate();
                Date date = new Date(calendarDate);
                Toast.makeText(AtividadeDiariaActivity.this,"Clicado", Toast.LENGTH_SHORT).show();
                Log.v("data escolhida: ", date.toString());

            }
        });
        /*{
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, day);
                Log.v("Calendar: ", ""+Calendar.YEAR+"/"+Calendar.MONTH+"/"+Calendar.DAY_OF_MONTH);
               // atividadeDiariaList = showAtividades(c);
            }*/
        //});
    }

    private List<AtividadeDiaria> showAtividades(Calendar c) {
        return atividadeDiariaDAO.selectActivitiesbyDay(c);
    }
}
