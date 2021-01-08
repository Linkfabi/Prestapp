package cl.farastudio.prestapp.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cl.farastudio.prestapp.DTO.Prestamo;
import cl.farastudio.prestapp.R;

public class PrestamoListAdapter extends ArrayAdapter<Prestamo> {

    private List<Prestamo> prestamos;
    private Activity activity;

    public PrestamoListAdapter(@NonNull Activity context, int resource, @NonNull List<Prestamo> objects) {
        super(context, resource, objects);
        this.prestamos=objects;
        this.activity=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=this.activity.getLayoutInflater();

        View filaDinero =inflater.inflate(R.layout.prestamo_dinero_list,null,true);

        Prestamo actual=prestamos.get(position);
        String tipoPrestamo = actual.getTipoPrestamo();

        try {
            switch(tipoPrestamo){   //Dinero, Libro, Consolas, Notebook, Celular, Articulo Electronico General, Articulos de Construccion, Otros
                case"Dinero":

                    TextView txtNombre=filaDinero.findViewById(R.id.txtNombreDineroList);
                    TextView txtMonto=filaDinero.findViewById(R.id.txtMontoDineroList);
                    TextView txtComision=filaDinero.findViewById(R.id.txtComisionDineroList);
                    TextView txtMontoFinal=filaDinero.findViewById(R.id.txtMontoFinalDineroList);
                    TextView txtFechaPres=filaDinero.findViewById(R.id.txtFechaPrestamoDineroList);
                    TextView txtFechaDev=filaDinero.findViewById(R.id.txtFechaEntregaDineroList);
                    TextView txtComentario=filaDinero.findViewById(R.id.txtNotaDineroList);

                    txtNombre.setText(actual.getNombre());
                    txtMonto.setText(String.valueOf(actual.getMonto()));
                    txtComision.setText(String.valueOf(actual.getComision()));
                    txtMontoFinal.setText(String.valueOf(actual.getMontoFinal()));
                    txtFechaPres.setText(actual.getFechaPrestamo());
                    txtFechaDev.setText(actual.getFechaEntrega());
                    txtComentario.setText(actual.getNota());


                    break;
                case"Libro":
                    View filaLibro =inflater.inflate(R.layout.prestamo_libro_list,null,true);

                    break;
                case"Consolas":
                    View filaConsolas =inflater.inflate(R.layout.prestamo_consola_list,null,true);

                    break;
                case "Notebook":
                    View filaNotebook =inflater.inflate(R.layout.prestamo_notebook_list,null,true);

                    break;
                case "Celular":
                    View filaCelular =inflater.inflate(R.layout.prestamo_celular_list,null,true);

                    break;
                case"Articulo Electronico General":
                    View filaAEG =inflater.inflate(R.layout.prestamo_otros_list,null,true);

                    break;
                case"Articulos de Construccion":
                    View filaAC =inflater.inflate(R.layout.prestamo_otros_list,null,true);

                    break;
                case"Otros":
                    View filaOtros =inflater.inflate(R.layout.prestamo_otros_list,null,true);

                    break;
                default:
                    break;
            }

        }catch (Exception e){
            System.out.println("*********************" + tipoPrestamo + "********************************");
        }

        return filaDinero;

    }
}
