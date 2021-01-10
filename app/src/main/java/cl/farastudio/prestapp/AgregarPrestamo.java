package cl.farastudio.prestapp;

import androidx.appcompat.app.AlertDialog;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cl.farastudio.prestapp.DAO.PrestamosDAO;
import cl.farastudio.prestapp.DAO.PrestamosDAODB;
import cl.farastudio.prestapp.DTO.Prestamo;

import static android.view.View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION;
import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class AgregarPrestamo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView calender1;
    TextView calender2;
    String fecha;
    int tipoCalendario=1;
    LinearLayout nombre,nombreProducto,marca,estado,accesorios,cantJuegos,ram,modelo,hdd,monto,comision,montoFinal,paginas,fechaPrestamo,fechaEntrega,comentario;
    EditText TxtNombre,TxtNombreProducto,TxtMarca,TxtEstado,TxtAccesorios,TxtCantidadJuegos,TxtRam,TxtModelo,TxtHDD,TxtMonto,TxtComision,TxtMontoFinal,TxtPaginas,TxtComentarios;
    Spinner spinner;
    Prestamo prestamo = new Prestamo();
    String tipoPrestamo;
    List<String> errores = new ArrayList<>();
    private PrestamosDAO prestamosDAO = new PrestamosDAODB(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        //Cargadomos los Datos con sus respectivas ID
        setContentView(R.layout.activity_agregar_prestamo);
        calender1 = findViewById(R.id.fechaCaptura);
        calender2 = findViewById(R.id.fechaCaptura2);
        cargarLayout();
        cargaTxt();
        spinnerSeleccionNada();
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
                borrarDatosTxt();
                tipoPrestamo = spinner.getSelectedItem().toString();
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

    //carga todos los layout existente en el Activity
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

    //Carga todos los TXT editables para poder modificarlos y recibir los datos
    public void cargaTxt (){
        TxtNombre=findViewById(R.id.txtNombre);
        TxtNombreProducto=findViewById(R.id.txtNombreProducto);
        TxtMarca=findViewById(R.id.txtMarca);
        TxtEstado=findViewById(R.id.txtEstado);
        TxtAccesorios=findViewById(R.id.txtAccesorios);
        TxtCantidadJuegos=findViewById(R.id.txtCantJuegos);
        TxtRam=findViewById(R.id.txtRam);
        TxtModelo=findViewById(R.id.txtModelo);
        TxtHDD=findViewById(R.id.txtHDD);
        TxtMonto=findViewById(R.id.txtMonto);
        TxtComision=findViewById(R.id.txtComision);
        TxtMontoFinal=findViewById(R.id.txtMontoFinal);
        TxtPaginas=findViewById(R.id.txtPaginas);
        TxtComentarios=findViewById(R.id.txtComentarios);
    }

    //Deja en blanco los TXT para evitar que aparescan datos al cambiar de opcion con el spinner
    public void borrarDatosTxt (){
        TxtNombre.setText("");
        TxtNombreProducto.setText("");
        TxtMarca.setText("");
        TxtEstado.setText("");
        TxtAccesorios.setText("");
        TxtCantidadJuegos.setText("");
        TxtRam.setText("");
        TxtModelo.setText("");
        TxtHDD.setText("");
        TxtMonto.setText("");
        TxtComision.setText("");
        TxtMontoFinal.setText("");
        TxtPaginas.setText("");
        TxtComentarios.setText("");
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

    // Metodo del Boton Guardar Prestamo
    public void guardarPrestamo(View view) {
        errores.clear();
        try {
            switch(tipoPrestamo) {   //Dinero, Libro, Consolas, Notebook, Celular, Articulo Electronico General, Articulos de Construccion, Otros
                case "Seleccione una opcion":
                    errores.add("Seleccione un tipo de prestamo");
                    break;
                case "Dinero":
                    comprobarNombre();
                    comprobarMonto();
                    comprobarComision();
                    comprobarFechas();
                    comprobarNota();
                    break;
                case "Libro":
                    comprobarNombre();
                    comprobarNombreProducto();
                    comprobarEstado();
                    comprobarPaginas();
                    comprobarFechas();
                    comprobarNota();
                    break;
                case "Consolas":
                    comprobarNombre();
                    comprobarNombreProducto();
                    comprobarAccesorios();
                    comprobarCantJuegos();
                    comprobarPaginas();
                    comprobarFechas();
                    comprobarNota();
                    break;
                case "Notebook":
                    comprobarNombre();
                    comprobarEstado();
                    comprobarAccesorios();
                    comprobarRam();
                    comprobarModelo();
                    comprobarHdd();
                    comprobarFechas();
                    comprobarNota();
                    break;
                case "Celular":
                    comprobarNombre();
                    comprobarMarca();
                    comprobarEstado();
                    comprobarAccesorios();
                    comprobarModelo();
                    comprobarFechas();
                    comprobarNota();
                    break;
                case"Articulo Electronico General":
                    comprobarNombre();
                    comprobarNombreProducto();
                    comprobarMarca();
                    comprobarEstado();
                    comprobarAccesorios();
                    comprobarFechas();
                    comprobarNota();
                    break;
                case"Articulos de Construccion":
                    comprobarNombre();
                    comprobarMarca();
                    comprobarNombreProducto();
                    comprobarEstado();
                    comprobarAccesorios();
                    comprobarFechas();
                    comprobarNota();
                    break;
                case"Otros":
                    comprobarNombreProducto();
                    comprobarNombre();
                    comprobarMarca();
                    comprobarEstado();
                    comprobarAccesorios();
                    comprobarFechas();
                    comprobarNota();
                    break;
            }

        }catch (Exception e){
            Toast.makeText(AgregarPrestamo.this, "error al guardar el prestamo", Toast.LENGTH_SHORT).show();
        }
        if(errores.isEmpty()){
            Prestamo p = new Prestamo();
            try {
                switch(tipoPrestamo) {   //Dinero, Libro, Consolas, Notebook, Celular, Articulo Electronico General, Articulos de Construccion, Otros
                    case "Dinero":
                        p.setTipoPrestamo(tipoPrestamo);
                        p.setNombre(TxtNombre.getText().toString().trim());
                        p.setNombreProducto(null);
                        p.setMarca(null);
                        p.setEstado(null);
                        p.setAccesorios(null);
                        p.setCantidadJuegos(0);
                        p.setRam(null);
                        p.setModelo(null);
                        p.setHDD(null);
                        p.setMonto(Float.valueOf(TxtMonto.getText().toString().trim()));
                        p.setComision(Float.valueOf(TxtComision.getText().toString().trim()));
                        p.setMontoFinal(Float.valueOf(TxtMontoFinal.getText().toString().trim()));
                        p.setPaginas(0);
                        p.setFechaPrestamo(calender1.getText().toString());
                        p.setFechaEntrega(calender2.getText().toString());
                        p.setNota(TxtComentarios.getText().toString().trim());
                        break;
                    case "Libro":
                        p.setTipoPrestamo(tipoPrestamo);
                        p.setNombre(TxtNombre.getText().toString().trim());
                        p.setNombreProducto(TxtNombreProducto.getText().toString().trim());
                        p.setMarca(null);
                        p.setEstado(TxtEstado.getText().toString().trim());
                        p.setAccesorios(null);
                        p.setCantidadJuegos(0);
                        p.setRam(null);
                        p.setModelo(null);
                        p.setHDD(null);
                        p.setMonto(0);
                        p.setComision(0);
                        p.setMontoFinal(0);
                        p.setPaginas(Integer.valueOf(TxtPaginas.getText().toString().trim()));
                        p.setFechaPrestamo(calender1.getText().toString());
                        p.setFechaEntrega(calender2.getText().toString());
                        p.setNota(TxtComentarios.getText().toString().trim());
                        break;
                    case "Consolas":
                        p.setTipoPrestamo(tipoPrestamo);
                        p.setNombre(TxtNombre.getText().toString().trim());
                        p.setNombreProducto(TxtNombreProducto.getText().toString().trim());
                        p.setMarca(null);
                        p.setEstado(null);
                        p.setAccesorios(TxtAccesorios.getText().toString().trim());
                        p.setCantidadJuegos(Integer.valueOf(TxtCantidadJuegos.getText().toString().trim()));
                        p.setRam(null);
                        p.setModelo(null);
                        p.setHDD(null);
                        p.setMonto(0);
                        p.setComision(0);
                        p.setMontoFinal(0);
                        p.setPaginas(0);
                        p.setFechaPrestamo(calender1.getText().toString());
                        p.setFechaEntrega(calender2.getText().toString());
                        p.setNota(TxtComentarios.getText().toString().trim());
                        break;
                    case "Notebook":
                        p.setTipoPrestamo(tipoPrestamo);
                        p.setNombre(TxtNombre.getText().toString().trim());
                        p.setNombreProducto(null);
                        p.setMarca(null);
                        p.setEstado(TxtEstado.getText().toString().trim());
                        p.setAccesorios(TxtAccesorios.getText().toString().trim());
                        p.setCantidadJuegos(0);
                        p.setRam(TxtRam.getText().toString().trim());
                        p.setModelo(TxtModelo.getText().toString().trim());
                        p.setHDD(TxtHDD.getText().toString().trim());
                        p.setMonto(0);
                        p.setComision(0);
                        p.setMontoFinal(0);
                        p.setPaginas(0);
                        p.setFechaPrestamo(calender1.getText().toString());
                        p.setFechaEntrega(calender2.getText().toString());
                        p.setNota(TxtComentarios.getText().toString().trim());
                        break;
                    case "Celular":
                        p.setTipoPrestamo(tipoPrestamo);
                        p.setNombre(TxtNombre.getText().toString().trim());
                        p.setNombreProducto(null);
                        p.setMarca(TxtMarca.getText().toString().trim());
                        p.setEstado(TxtEstado.getText().toString().trim());
                        p.setAccesorios(TxtAccesorios.getText().toString().trim());
                        p.setCantidadJuegos(0);
                        p.setRam(null);
                        p.setModelo(TxtModelo.getText().toString().trim());
                        p.setHDD(null);
                        p.setMonto(0);
                        p.setComision(0);
                        p.setMontoFinal(0);
                        p.setPaginas(0);
                        p.setFechaPrestamo(calender1.getText().toString());
                        p.setFechaEntrega(calender2.getText().toString());
                        p.setNota(TxtComentarios.getText().toString().trim());
                        break;
                    case"Articulo Electronico General":
                        p.setTipoPrestamo(tipoPrestamo);
                        p.setNombre(TxtNombre.getText().toString().trim());
                        p.setNombreProducto(TxtNombreProducto.getText().toString().trim());
                        p.setMarca(TxtMarca.getText().toString().trim());
                        p.setEstado(TxtEstado.getText().toString().trim());
                        p.setAccesorios(TxtAccesorios.getText().toString().trim());
                        p.setCantidadJuegos(0);
                        p.setRam(null);
                        p.setModelo(null);
                        p.setHDD(null);
                        p.setMonto(0);
                        p.setComision(0);
                        p.setMontoFinal(0);
                        p.setPaginas(0);
                        p.setFechaPrestamo(calender1.getText().toString());
                        p.setFechaEntrega(calender2.getText().toString());
                        p.setNota(TxtComentarios.getText().toString().trim());
                        break;
                    case"Articulos de Construccion":
                        p.setTipoPrestamo(tipoPrestamo);
                        p.setNombre(TxtNombre.getText().toString().trim());
                        p.setNombreProducto(TxtNombreProducto.getText().toString().trim());
                        p.setMarca(TxtMarca.getText().toString().trim());
                        p.setEstado(TxtEstado.getText().toString().trim());
                        p.setAccesorios(TxtAccesorios.getText().toString().trim());
                        p.setCantidadJuegos(0);
                        p.setRam(null);
                        p.setModelo(null);
                        p.setHDD(null);
                        p.setMonto(0);
                        p.setComision(0);
                        p.setMontoFinal(0);
                        p.setPaginas(1);
                        p.setFechaPrestamo(calender1.getText().toString());
                        p.setFechaEntrega(calender2.getText().toString());
                        p.setNota(TxtComentarios.getText().toString().trim());
                        break;
                    case"Otros":
                        p.setTipoPrestamo(tipoPrestamo);
                        p.setNombre(TxtNombre.getText().toString().trim());
                        p.setNombreProducto(TxtNombreProducto.getText().toString().trim());
                        p.setMarca(TxtMarca.getText().toString().trim());
                        p.setEstado(TxtEstado.getText().toString().trim());
                        p.setAccesorios(TxtAccesorios.getText().toString().trim());
                        p.setCantidadJuegos(0);
                        p.setRam(null);
                        p.setModelo(null);
                        p.setHDD(null);
                        p.setMonto(0);
                        p.setComision(0);
                        p.setMontoFinal(0);
                        p.setPaginas(2);
                        p.setFechaPrestamo(calender1.getText().toString());
                        p.setFechaEntrega(calender2.getText().toString());
                        p.setNota(TxtComentarios.getText().toString().trim());
                        break;
                }

            }catch (Exception e){
                Toast.makeText(AgregarPrestamo.this, "error al guardar Base de datos del prestamo", Toast.LENGTH_SHORT).show();
            }

            prestamosDAO.add(p);

            Toast.makeText(AgregarPrestamo.this, "Se ha creado correctamente", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AgregarPrestamo.this, MainActivity.class));
        }else {
            mostrarErrores(errores);
        }
        Toast.makeText(AgregarPrestamo.this, tipoPrestamo, Toast.LENGTH_SHORT).show();
    }

    private void mostrarErrores(List<String> errores) {
        String mensaje = "";
        for (String e : errores) {
            mensaje += "-" + e + "\n";
        }
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(AgregarPrestamo.this);
        alertBuilder.setTitle("Error de validación")
                .setMessage(mensaje)
                .setPositiveButton("Aceptar", null)
                .create()
                .show();
    }

    //TODO Todos las comprobaciones de errores estan aqui
    private void comprobarNombre(){
        if(TxtNombre.getText().toString().trim().isEmpty()) {
            errores.add("Nombre no debe estar vacio");
        }
    }
    private void comprobarNombreProducto(){
        if(TxtNombreProducto.getText().toString().trim().isEmpty()) {
            errores.add("NombreProducto no debe estar vacio");
        }
    }
    private void comprobarMarca(){
        if(TxtMarca.getText().toString().trim().isEmpty()) {
            errores.add("Marca no debe estar vacio");
        }
    }
    private void comprobarEstado(){
        if(TxtEstado.getText().toString().trim().isEmpty()) {
            errores.add("Estado Producto no debe estar vacio");
        }
    }
    private void comprobarAccesorios(){
        if(TxtAccesorios.getText().toString().trim().isEmpty()) {
            errores.add("Accesorios no debe estar vacio");
        }
    }
    private void comprobarCantJuegos(){
        int valor;
        if(TxtCantidadJuegos.getText().toString().trim().isEmpty()){
            valor=-1;
            if(valor <= 0){
                errores.add("Cantidad de juegos debe ser igual o mayor a 0");
            }
        }
    }
    private void comprobarRam(){
        if(TxtRam.getText().toString().trim().isEmpty()) {
            errores.add("Ram no debe estar vacio");
        }
    }
    private void comprobarModelo(){
        if(TxtModelo.getText().toString().trim().isEmpty()) {
            errores.add("Modelo Producto no debe estar vacio");
        }
    }
    private void comprobarHdd(){
        if(TxtHDD.getText().toString().trim().isEmpty()) {
            errores.add("Disco duro no debe estar vacio");
        }
    }
    private void comprobarMonto(){
        int valor;
        if(TxtMonto.getText().toString().trim().isEmpty()){
            valor=-1;
            if(valor <= 0){
                errores.add("Monto debe ser igual o mayor a 0");
            }
        }
    }
    private void comprobarComision(){
        int valor;
        if(TxtComision.getText().toString().trim().isEmpty()){
            valor=-1;
            if(valor <= 0){
                errores.add("Comision debe ser igual o mayor a 0");
            }
        }
    }
    private void comprobarPaginas(){
        int valor;
        if(TxtPaginas.getText().toString().trim().isEmpty()){
            valor=0;
            if(valor < 0){
                errores.add("Cantidad Paginas debe ser mayor a 0");
            }
        }
    }
    private void comprobarFechas(){
        /* Date fechaCompara = StringAfecha(fecha.getText().toString());
        if(fechaCompara.before(fechaActual) && !fechaCompara.equals(fechaActual) ){
            errores.add("Fecha debe ser igual o mayor a Hoy");
        } */
    }
    private void comprobarNota(){
        if(TxtComentarios.getText().toString().trim().isEmpty()) {
            errores.add("Comentario no debe estar vacio");
        }
    }

}