package upkeepxpteam.atividadediaria.atividadediariaactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

import java.util.Calendar;
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
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

            }
        });
    }

    private List<AtividadeDiaria> showAtividades(Calendar c) {
        return atividadeDiariaDAO.selectActivitiesbyDay(c);
    }
}
