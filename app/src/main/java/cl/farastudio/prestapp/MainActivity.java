package cl.farastudio.prestapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import cl.farastudio.prestapp.DAO.PrestamosDAO;
import cl.farastudio.prestapp.DAO.PrestamosDAODB;
import cl.farastudio.prestapp.DTO.Prestamo;
import cl.farastudio.prestapp.adapters.PrestamoListAdapter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private List<Prestamo> prestamo;
    private PrestamosDAO prestamosDAO = new PrestamosDAODB(this);
    private ListView prestamoLV;
    private PrestamoListAdapter adapter;
    private Spinner spinner;
    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        texto = findViewById(R.id.textoPrueba);

        this.prestamo =this.prestamosDAO.getAll();
        this.prestamoLV =findViewById(R.id.lista_prestamo);



        /*this.prestamoLV.setOnItemClickListener((adapterView, view, i, l) -> {
            Paciente paciente = pacientes.get(i);
            Intent intent = new Intent(ListarPacientesActivity.this,VistaPacienteActivity.class);
            intent.putExtra("paciente",paciente);
            startActivity(intent);
        });*/



        // Cargamos el Spinner
        spinner = (Spinner) findViewById(R.id.SpPrestamoMain);
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
                    this.adapter =new PrestamoListAdapter(this,R.layout.prestamo_dinero_list,this.prestamo, 1);
                    this.prestamoLV.setAdapter(this.adapter);
                }else if(seleccionado == 2){
                    texto.setText("Lista Libro No tiene Datos");
                    this.adapter =new PrestamoListAdapter(this,R.layout.prestamo_libro_list,this.prestamo, 2);
                    this.prestamoLV.setAdapter(this.adapter);
                }else if(seleccionado == 3){
                    texto.setText("Lista Consolas No tiene Datos");
                    this.adapter =new PrestamoListAdapter(this,R.layout.prestamo_consola_list,this.prestamo, 3);
                    this.prestamoLV.setAdapter(this.adapter);
                }else if(seleccionado == 4){
                    texto.setText("Lista Notebook No tiene Datos");
                    this.adapter =new PrestamoListAdapter(this,R.layout.prestamo_notebook_list,this.prestamo, 4);
                    this.prestamoLV.setAdapter(this.adapter);
                }else if(seleccionado == 5){
                    texto.setText("Lista Celular No tiene Datos");
                    this.adapter =new PrestamoListAdapter(this,R.layout.prestamo_celular_list,this.prestamo, 5);
                    this.prestamoLV.setAdapter(this.adapter);
                }else if(seleccionado == 6){
                    texto.setText("Lista Art. Electronico No tiene Datos");
                    this.adapter =new PrestamoListAdapter(this,R.layout.prestamo_arti_e_g_list,this.prestamo, 6);
                    this.prestamoLV.setAdapter(this.adapter);
                }else if(seleccionado == 7){
                    texto.setText("Lista Art. Construccion No tiene Datos");
                    this.adapter =new PrestamoListAdapter(this,R.layout.prestamo_articonst_list,this.prestamo, 7);
                    this.prestamoLV.setAdapter(this.adapter);
                }else if(seleccionado == 8){
                    texto.setText("Lista Otros No tiene Datos");
                    this.adapter =new PrestamoListAdapter(this,R.layout.prestamo_otros_list,this.prestamo, 8);
                    this.prestamoLV.setAdapter(this.adapter);
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