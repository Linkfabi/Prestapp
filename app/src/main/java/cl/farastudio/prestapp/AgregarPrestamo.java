package cl.farastudio.prestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class AgregarPrestamo extends AppCompatActivity {

    TextView calender1;
    TextView calender2;
    String fecha;
    int tipoCalendario=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_prestamo);
        calender1 = findViewById(R.id.fechaCaptura);
        calender2 = findViewById(R.id.fechaCaptura2);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        FloatingActionButton fab = findViewById(R.id.BtnCasa);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarPrestamo.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void abrirCalendario() {
        Calendar calen = Calendar.getInstance();
        int anio = calen.get(Calendar.YEAR);
        int mes = calen.get(Calendar.MONTH);
        int dia = calen.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpg = new DatePickerDialog(AgregarPrestamo.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month +1;
                fecha = dayOfMonth +"/"+ month + "/"+ year;
                if (tipoCalendario == 1){
                    calender1.setText(fecha);
                }else{
                    calender2.setText(fecha);
                }
            }
        }, anio, mes, dia);
        dpg.show();
    }

    public void calendarioIngreso(View view) {
        tipoCalendario = 1;
        abrirCalendario();
    }

    public void calendarioEgreso(View view) {
        tipoCalendario = 2;
        abrirCalendario();
    }
}