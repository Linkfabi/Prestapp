package cl.farastudio.prestapp.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

import cl.farastudio.prestapp.DTO.Prestamo;
import cl.farastudio.prestapp.R;

public class PrestamoListAdapter extends ArrayAdapter<Prestamo> {

    private List<Prestamo> prestamos;
    private Activity activity;
    private int valor;

    public PrestamoListAdapter(@NonNull Activity context, int resource, @NonNull List<Prestamo> objects, int i) {
        super(context, resource, objects);
        this.prestamos=objects;
        this.activity=context;
        this.valor=i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=this.activity.getLayoutInflater();

        View filaDinero =inflater.inflate(R.layout.prestamo_dinero_list,null,true);
        View filaLibro =inflater.inflate(R.layout.prestamo_libro_list,null,true);
        View filaConsolas =inflater.inflate(R.layout.prestamo_consola_list,null,true);
        View filaNotebook =inflater.inflate(R.layout.prestamo_notebook_list,null,true);
        View filaCelular =inflater.inflate(R.layout.prestamo_celular_list,null,true);
        View filaAEG =inflater.inflate(R.layout.prestamo_arti_e_g_list,null,true);
        View filaAC =inflater.inflate(R.layout.prestamo_articonst_list,null,true);
        View filaOtros =inflater.inflate(R.layout.prestamo_otros_list,null,true);

        Prestamo actual=prestamos.get(position);
        String tipoPrestamo = actual.getTipoPrestamo();
        System.out.println("*********************" + prestamos.get(position) + "********************************");
        System.out.println("*********************" + actual.getTipoPrestamo() + "********************************");

        try {
            switch(tipoPrestamo) {   //Dinero, Libro, Consolas, Notebook, Celular, Articulo Electronico General, Articulos de Construccion, Otros
                case "Dinero":

                    System.out.println("*********************" + "Entro a Dinero" + "********************************");
                    System.out.println("*********************" + prestamos.get(position) + "********************************");

                    TextView txtNombre = filaDinero.findViewById(R.id.txtNombreDineroList);
                    TextView txtMonto = filaDinero.findViewById(R.id.txtMontoDineroList);
                    TextView txtComision = filaDinero.findViewById(R.id.txtComisionDineroList);
                    TextView txtMontoFinal = filaDinero.findViewById(R.id.txtMontoFinalDineroList);
                    TextView txtFechaPres = filaDinero.findViewById(R.id.txtFechaPrestamoDineroList);
                    TextView txtFechaDev = filaDinero.findViewById(R.id.txtFechaEntregaDineroList);
                    TextView txtComentario = filaDinero.findViewById(R.id.txtNotaDineroList);

                    txtNombre.setText(actual.getNombre());
                    txtMonto.setText(String.valueOf(actual.getMonto()));
                    txtComision.setText(String.valueOf(actual.getComision()));
                    txtMontoFinal.setText(String.valueOf(actual.getMontoFinal()));
                    txtFechaPres.setText(actual.getFechaPrestamo());
                    txtFechaDev.setText(actual.getFechaEntrega());
                    txtComentario.setText(actual.getNota());

                    break;
                case "Libro":

                    TextView txtNombreLibro = filaLibro.findViewById(R.id.txtNombreLibroList);
                    TextView txtNombreProdLibro = filaLibro.findViewById(R.id.txtNombreLbLibroList);
                    TextView txtEstadoLibro = filaLibro.findViewById(R.id.txtEstadoLibroList);
                    TextView txtPaginasLibro = filaLibro.findViewById(R.id.txtPaginasLibroList);
                    TextView txtFechaPresLibro = filaLibro.findViewById(R.id.txtFechaPrestamoLibroList);
                    TextView txtFechaDevLibro = filaLibro.findViewById(R.id.txtFechaEntregaLibroList);
                    TextView txtComentarioLibro = filaLibro.findViewById(R.id.txtNotaLibroList);

                    txtNombreLibro.setText(actual.getNombre());
                    txtNombreProdLibro.setText(actual.getNombreProducto());
                    txtEstadoLibro.setText(actual.getEstado());
                    txtPaginasLibro.setText(actual.getPaginas());
                    txtFechaPresLibro.setText(actual.getFechaPrestamo());
                    txtFechaDevLibro.setText(actual.getFechaEntrega());
                    txtComentarioLibro.setText(actual.getNota());

                    break;
                case "Consolas":
                    System.out.println("*********************" + "Entro a consola" + "********************************");

                    TextView txtNombreConsola = filaConsolas.findViewById(R.id.txtNombreConsolaList);
                    TextView txtNombreProducConsola = filaConsolas.findViewById(R.id.txtNombreCsConsolaList);
                    TextView txtAccesorioConsola = filaConsolas.findViewById(R.id.txtAccesoriosConsolaList);
                    TextView txtJuegosConsola = filaConsolas.findViewById(R.id.txtCantJuegosList);
                    TextView txtFechaPresConsola = filaConsolas.findViewById(R.id.txtFechaPrestamoConsolaList);
                    TextView txtFechaDevConsola = filaConsolas.findViewById(R.id.txtFechaEntregaConsolaList);
                    TextView txtComentarioConsola = filaConsolas.findViewById(R.id.txtNotaConsolaList);

                    txtNombreConsola.setText(actual.getNombre());
                    txtNombreProducConsola.setText(actual.getNombreProducto());
                    txtAccesorioConsola.setText(actual.getAccesorios());
                    txtJuegosConsola.setText(String.valueOf(actual.getCantidadJuegos()));
                    txtFechaPresConsola.setText(actual.getFechaPrestamo());
                    txtFechaDevConsola.setText(actual.getFechaEntrega());
                    txtComentarioConsola.setText(actual.getNota());


                    break;
                case "Notebook":

                    TextView txtNombreNotebook = filaNotebook.findViewById(R.id.txtNombreNotebookList);
                    TextView txtMarcaNotebook = filaNotebook.findViewById(R.id.txtMarcaNotebookList);
                    TextView txtModeloNotebook = filaNotebook.findViewById(R.id.txtModeloNotebookList);
                    TextView txtRamNotebook = filaNotebook.findViewById(R.id.txtRamNotebookList);
                    TextView txtHddNotebook = filaNotebook.findViewById(R.id.txtHddNotebookList);
                    TextView txtAccesoriosNotebook = filaNotebook.findViewById(R.id.txtAccesoriosNotebookList);
                    TextView txtFechaPresNotebook = filaNotebook.findViewById(R.id.txtFechaPrestamoNotebookList);
                    TextView txtFechaDevNotebook = filaNotebook.findViewById(R.id.txtFechaEntregaNotebookList);
                    TextView txtComentarioNotebook = filaNotebook.findViewById(R.id.txtNotaNotebookList);

                    txtNombreNotebook.setText(actual.getNombre());
                    txtMarcaNotebook.setText(actual.getMarca());
                    txtModeloNotebook.setText(actual.getModelo());
                    txtRamNotebook.setText(actual.getRam());
                    txtHddNotebook.setText(actual.getHDD());
                    txtAccesoriosNotebook.setText(actual.getAccesorios());
                    txtFechaPresNotebook.setText(actual.getFechaPrestamo());
                    txtFechaDevNotebook.setText(actual.getFechaEntrega());
                    txtComentarioNotebook.setText(actual.getNota());

                    break;
                case "Celular":

                    TextView txtNombreCelular = filaCelular.findViewById(R.id.txtNombreCelularList);
                    TextView txtMarcaCelular = filaCelular.findViewById(R.id.txtMarcaCelularList);
                    TextView txtModeloCelular = filaCelular.findViewById(R.id.txtModeloCelularList);
                    TextView txtEstadoCelular = filaCelular.findViewById(R.id.txtEstadoCelularList);
                    TextView txtAccesoriosCelular = filaCelular.findViewById(R.id.txtAccesoriosCelularList);
                    TextView txtFechaPrestamoCelular = filaCelular.findViewById(R.id.txtFechaPrestamoCelularList);
                    TextView txtFechaEntregaCelular = filaCelular.findViewById(R.id.txtFechaEntregaCelularList);
                    TextView txtComentarioCelular = filaCelular.findViewById(R.id.txtNotaCelularList);

                    txtNombreCelular.setText(actual.getNombre());
                    txtMarcaCelular.setText(actual.getMarca());
                    txtModeloCelular.setText(actual.getModelo());
                    txtEstadoCelular.setText(actual.getEstado());
                    txtAccesoriosCelular.setText(actual.getAccesorios());
                    txtFechaPrestamoCelular.setText(actual.getFechaPrestamo());
                    txtFechaEntregaCelular.setText(actual.getFechaEntrega());
                    txtComentarioCelular.setText(actual.getNota());

                    break;
                case"Articulo Electronico General":

                    TextView txtNombreAEG=filaAEG.findViewById(R.id.txtNombreArtiEGList);
                    TextView txtNombreProAEG=filaAEG.findViewById(R.id.txtNombreProductoArtiEGList);
                    TextView txtMarcaAEG=filaAEG.findViewById(R.id.txtMarcaArtiEGList);
                    TextView txtAccesoriosAEG=filaAEG.findViewById(R.id.txtAccesoriosArtiEGList);
                    TextView txtEstadoAEG=filaAEG.findViewById(R.id.txtEstadoArtiEGList);
                    TextView txtFechaPresAEG=filaAEG.findViewById(R.id.txtFechaPrestamoArtiEGList);
                    TextView txtFechaEntreAEG=filaAEG.findViewById(R.id.txtFechaEntregaArtiEGList);
                    TextView txtFechaComentarioAEG=filaAEG.findViewById(R.id.txtNotaArtiEGList);

                    txtNombreAEG.setText(actual.getNombre());
                    txtNombreProAEG.setText(actual.getNombreProducto());
                    txtMarcaAEG.setText(actual.getMarca());
                    txtAccesoriosAEG.setText(actual.getAccesorios());
                    txtEstadoAEG.setText(actual.getEstado());
                    txtFechaPresAEG.setText(actual.getFechaPrestamo());
                    txtFechaEntreAEG.setText(actual.getFechaEntrega());
                    txtFechaComentarioAEG.setText(actual.getNota());

                    break;
                case"Articulos de Construccion":

                    TextView txtNombreAC=filaAC.findViewById(R.id.txtNombreArtConstList);
                    TextView txtNombreProAC=filaAC.findViewById(R.id.txtNombreProductoArtConstList);
                    TextView txtMarcaAC=filaAC.findViewById(R.id.txtMarcaArtConstList);
                    TextView txtAccesoriosAC=filaAC.findViewById(R.id.txtAccesoriosArtConstList);
                    TextView txtEstadoAC=filaAC.findViewById(R.id.txtEstadoArtConstList);
                    TextView txtFechaPresAC=filaAC.findViewById(R.id.txtFechaPrestamoArtConstList);
                    TextView txtFechaEntreAC=filaAC.findViewById(R.id.txtFechaEntregaArtConstList);
                    TextView txtFechaComentarioAC=filaAC.findViewById(R.id.txtNotaArtConstList);

                    txtNombreAC.setText(actual.getNombre());
                    txtNombreProAC.setText(actual.getNombreProducto());
                    txtMarcaAC.setText(actual.getMarca());
                    txtAccesoriosAC.setText(actual.getAccesorios());
                    txtEstadoAC.setText(actual.getEstado());
                    txtFechaPresAC.setText(actual.getFechaPrestamo());
                    txtFechaEntreAC.setText(actual.getFechaEntrega());
                    txtFechaComentarioAC.setText(actual.getNota());

                    break;
                case"Otros":

                    TextView txtNombreOtros=filaOtros.findViewById(R.id.txtNombreOtrosList);
                    TextView txtNombreProOtros=filaOtros.findViewById(R.id.txtNombreProductoOtrosList);
                    TextView txtMarcaOtros=filaOtros.findViewById(R.id.txtMarcaOtrosList);
                    TextView txtAccesoriosOtros=filaOtros.findViewById(R.id.txtAccesoriosOtrosList);
                    TextView txtEstadoOtros=filaOtros.findViewById(R.id.txtEstadoOtrosList);
                    TextView txtFechaPresOtros=filaOtros.findViewById(R.id.txtFechaPrestamoOtrosList);
                    TextView txtFechaEntreOtros=filaOtros.findViewById(R.id.txtFechaEntregaOtrosList);
                    TextView txtFechaComentarioOtros=filaOtros.findViewById(R.id.txtNotaOtrosList);

                    txtNombreOtros.setText(actual.getNombre());
                    txtNombreProOtros.setText(actual.getNombreProducto());
                    txtMarcaOtros.setText(actual.getMarca());
                    txtAccesoriosOtros.setText(actual.getAccesorios());
                    txtEstadoOtros.setText(actual.getEstado());
                    txtFechaPresOtros.setText(actual.getFechaPrestamo());
                    txtFechaEntreOtros.setText(actual.getFechaEntrega());
                    txtFechaComentarioOtros.setText(actual.getNota());

                    break;
            }

        }catch (Exception e){
            Toast.makeText(activity, "error al cargar lista, vuelva a intentar", Toast.LENGTH_SHORT).show();
        }

        if(valor == 1){
            return filaDinero;
        }else if (valor == 2){
            return filaLibro;
        }else if (valor == 3){
            return filaConsolas;
        }else if (valor == 4){
            return filaNotebook;
        }else if (valor == 5){
            return filaCelular;
        }else if (valor == 6){
            return filaAEG;
        }else if (valor == 7){
            return filaAC;
        }else {
            return filaOtros;
        }
    }
}
