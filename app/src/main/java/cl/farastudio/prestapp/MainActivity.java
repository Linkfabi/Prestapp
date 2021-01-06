package cl.farastudio.prestapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import cl.farastudio.prestapp.DTO.Prestamo;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        texto = findViewById(R.id.textoPrueba);

        spinner = (Spinner) findViewById(R.id.SpPrestamoMain);
        // Cargamos el Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tiposPrestamos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this); // con este comando puedo escuchar cada vez que se selecciona una opcion en el spinner
                                                // se activa el metodo onItemSelected implementado mas abajo

        FloatingActionButton fab = findViewById(R.id.BtnPrestamo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AgregarPrestamo.class);
                startActivity(intent);
            }
        });
    }



    //OnItemSelected me permite realizar una accion depende a la opcion escojida del spinner
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.SpPrestamoMain:
                int seleccionado = spinner.getSelectedItemPosition();
                if(seleccionado == 0){
                    texto.setText("Seleccione una Lista");
                }else if(seleccionado == 1){
                    texto.setText("Lista Dinero No tiene Datos");
                }else if(seleccionado == 2){
                    texto.setText("Lista Libro No tiene Datos");
                }else if(seleccionado == 3){
                    texto.setText("Lista Consolas No tiene Datos");
                }else if(seleccionado == 4){
                    texto.setText("Lista Notebook No tiene Datos");
                }else if(seleccionado == 5){
                    texto.setText("Lista Celular No tiene Datos");
                }else if(seleccionado == 6){
                    texto.setText("Lista Art. Electronico No tiene Datos");
                }else if(seleccionado == 7){
                    texto.setText("Lista Art. Construccion No tiene Datos");
                }else if(seleccionado == 8){
                    texto.setText("Lista Otros No tiene Datos");
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}