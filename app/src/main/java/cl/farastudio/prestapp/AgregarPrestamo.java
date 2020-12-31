package cl.farastudio.prestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class AgregarPrestamo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView calender1;
    TextView calender2;
    String fecha;
    int tipoCalendario=1;
    LinearLayout nombre,nombreProducto,marca,estado,accesorios,cantJuegos,ram,modelo,hdd,monto,comision,montoFinal,paginas,fechaPrestamo,fechaEntrega,comentario;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        //Cargadomos los Datos con sus respectivas ID
        setContentView(R.layout.activity_agregar_prestamo);
        calender1 = findViewById(R.id.fechaCaptura);
        calender2 = findViewById(R.id.fechaCaptura2);
        cargarLayout();
        spinner = (Spinner) findViewById(R.id.SpPrestamo);
        // Cargamos el Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tiposPrestamos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this); // con este comando puedo escuchar cada vez que se selecciona una opcion en el spinner
                                                // se activa el metodo onItemSelected implementado mas abajo

        // Metodo para escuchar el click del Boton Regresar
        FloatingActionButton fab = findViewById(R.id.BtnCasa);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarPrestamo.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    // Metodo que abre el calendario y carga las fechas actuales del sistema
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

                //este SWITCH es para saber de donde fue convocado y asi cargar el dato en el TXT correspondiente
                switch (tipoCalendario){
                    case 1:
                        calender1.setText(fecha);
                        break;
                    case 2:
                        calender2.setText(fecha);
                        break;
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


    //OnItemSelected me permite realizar una accion depende a la opcion escojida del spinner
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.SpPrestamo:
                int seleccionado = spinner.getSelectedItemPosition();
                if(seleccionado == 0){
                    spinnerSeleccionNada();
                }else if(seleccionado == 1){
                    spinnerSeleccionDinero();
                }else if(seleccionado == 2){
                    spinnerSeleccionLibro();
                }else if(seleccionado == 3){
                    spinnerSeleccionConsola();
                }else if(seleccionado == 4){
                    spinnerSeleccionNotebook();
                }else if(seleccionado == 5){
                    spinnerSeleccionCelular();
                }else if(seleccionado == 6){
                    spinnerSeleccionGeneral();
                }else if(seleccionado == 7){
                    spinnerSeleccionGeneral();
                }else if(seleccionado == 8){
                    spinnerSeleccionGeneral();
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void cargarLayout (){
        nombre=findViewById(R.id.LLnombre);
        nombreProducto=findViewById(R.id.LLnombreProducto);
        marca=findViewById(R.id.LLmarca);
        estado=findViewById(R.id.LLestado);
        accesorios=findViewById(R.id.LLaccesorios);
        cantJuegos=findViewById(R.id.LLcantidadJuegos);
        ram=findViewById(R.id.LLram);
        modelo=findViewById(R.id.LLmodelo);
        hdd=findViewById(R.id.LLhdd);
        monto=findViewById(R.id.LLmonto);
        comision=findViewById(R.id.LLcomision);
        montoFinal=findViewById(R.id.LLmontoFinal);
        paginas=findViewById(R.id.LLpaginas);
        fechaPrestamo=findViewById(R.id.LLfechaPrestamo);
        fechaEntrega=findViewById(R.id.LLfechaEntrega);
        comentario=findViewById(R.id.LLcomentario);
    }

    //Visible: muestra el layout, INVISIBLE: lo oculta pero deja el espacio, GONE: lo oculta y elimina el espacio
    public void spinnerSeleccionNada(){
        nombre.setVisibility(GONE);
        nombreProducto.setVisibility(GONE);
        marca.setVisibility(GONE);
        estado.setVisibility(GONE);
        accesorios.setVisibility(GONE);
        cantJuegos.setVisibility(GONE);
        ram.setVisibility(GONE);
        modelo.setVisibility(GONE);
        hdd.setVisibility(GONE);
        monto.setVisibility(GONE);
        comision.setVisibility(GONE);
        montoFinal.setVisibility(GONE);
        paginas.setVisibility(GONE);
        fechaPrestamo.setVisibility(GONE);
        fechaEntrega.setVisibility(GONE);
        comentario.setVisibility(GONE);
    }
    public void spinnerSeleccionDinero(){
        nombre.setVisibility(VISIBLE);
        nombreProducto.setVisibility(GONE);
        marca.setVisibility(GONE);
        estado.setVisibility(GONE);
        accesorios.setVisibility(GONE);
        cantJuegos.setVisibility(GONE);
        ram.setVisibility(GONE);
        modelo.setVisibility(GONE);
        hdd.setVisibility(GONE);
        monto.setVisibility(VISIBLE);
        comision.setVisibility(VISIBLE);
        montoFinal.setVisibility(VISIBLE);
        paginas.setVisibility(GONE);
        fechaPrestamo.setVisibility(VISIBLE);
        fechaEntrega.setVisibility(VISIBLE);
        comentario.setVisibility(VISIBLE);
    }
    public void spinnerSeleccionLibro(){
        nombre.setVisibility(VISIBLE);
        nombreProducto.setVisibility(VISIBLE);
        marca.setVisibility(GONE);
        estado.setVisibility(VISIBLE);
        accesorios.setVisibility(GONE);
        cantJuegos.setVisibility(GONE);
        ram.setVisibility(GONE);
        modelo.setVisibility(GONE);
        hdd.setVisibility(GONE);
        monto.setVisibility(GONE);
        comision.setVisibility(GONE);
        montoFinal.setVisibility(GONE);
        paginas.setVisibility(VISIBLE);
        fechaPrestamo.setVisibility(VISIBLE);
        fechaEntrega.setVisibility(VISIBLE);
        comentario.setVisibility(VISIBLE);
    }
    public void spinnerSeleccionConsola(){
        nombre.setVisibility(VISIBLE);
        nombreProducto.setVisibility(VISIBLE);
        marca.setVisibility(GONE);
        estado.setVisibility(GONE);
        accesorios.setVisibility(VISIBLE);
        cantJuegos.setVisibility(VISIBLE);
        ram.setVisibility(GONE);
        modelo.setVisibility(GONE);
        hdd.setVisibility(GONE);
        monto.setVisibility(GONE);
        comision.setVisibility(GONE);
        montoFinal.setVisibility(GONE);
        paginas.setVisibility(GONE);
        fechaPrestamo.setVisibility(VISIBLE);
        fechaEntrega.setVisibility(VISIBLE);
        comentario.setVisibility(VISIBLE);
    }
    public void spinnerSeleccionNotebook(){
        nombre.setVisibility(VISIBLE);
        nombreProducto.setVisibility(GONE);
        marca.setVisibility(GONE);
        estado.setVisibility(VISIBLE);
        accesorios.setVisibility(VISIBLE);
        cantJuegos.setVisibility(GONE);
        ram.setVisibility(VISIBLE);
        modelo.setVisibility(VISIBLE);
        hdd.setVisibility(VISIBLE);
        monto.setVisibility(GONE);
        comision.setVisibility(GONE);
        montoFinal.setVisibility(GONE);
        paginas.setVisibility(GONE);
        fechaPrestamo.setVisibility(VISIBLE);
        fechaEntrega.setVisibility(VISIBLE);
        comentario.setVisibility(VISIBLE);
    }
    public void spinnerSeleccionCelular(){
        nombre.setVisibility(VISIBLE);
        nombreProducto.setVisibility(GONE);
        marca.setVisibility(VISIBLE);
        estado.setVisibility(VISIBLE);
        accesorios.setVisibility(VISIBLE);
        cantJuegos.setVisibility(GONE);
        ram.setVisibility(GONE);
        modelo.setVisibility(VISIBLE);
        hdd.setVisibility(GONE);
        monto.setVisibility(GONE);
        comision.setVisibility(GONE);
        montoFinal.setVisibility(GONE);
        paginas.setVisibility(GONE);
        fechaPrestamo.setVisibility(VISIBLE);
        fechaEntrega.setVisibility(VISIBLE);
        comentario.setVisibility(VISIBLE);
    }
    public void spinnerSeleccionGeneral(){
        nombre.setVisibility(VISIBLE);
        nombreProducto.setVisibility(VISIBLE);
        marca.setVisibility(VISIBLE);
        estado.setVisibility(VISIBLE);
        accesorios.setVisibility(VISIBLE);
        cantJuegos.setVisibility(GONE);
        ram.setVisibility(GONE);
        modelo.setVisibility(GONE);
        hdd.setVisibility(GONE);
        monto.setVisibility(GONE);
        comision.setVisibility(GONE);
        montoFinal.setVisibility(GONE);
        paginas.setVisibility(GONE);
        fechaPrestamo.setVisibility(VISIBLE);
        fechaEntrega.setVisibility(VISIBLE);
        comentario.setVisibility(VISIBLE);
    }

}