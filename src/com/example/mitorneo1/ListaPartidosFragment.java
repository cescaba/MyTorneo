package com.example.mitorneo1;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bean.Partido;
import com.example.dao.JugadorDAO;
import com.example.dao.PartidoDAO;
import com.example.util.Ipsum;
public class ListaPartidosFragment extends ListFragment {
	final static String TORNEO = "torneo";
	long idtorneo;
	PartidoDAO helperpartido;
	JugadorDAO helperjugador;
	List<Partido> partidos;
	List<String> nombrepartidos;
	OnHeadlineSelectedListener mCallback;

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnHeadlineSelectedListener {
        /** Called by HeadlinesFragment when a list item is selected */
        public void onPartidoSelected(int position,long  torneo);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        idtorneo = args.getLong(TORNEO);
        
        helperpartido = new PartidoDAO(getActivity().getApplicationContext());
        helperjugador = new JugadorDAO(getActivity().getApplicationContext());
        helperpartido.abrir();
        helperjugador.abrir();
  
		partidos = helperpartido.obtenerlistadepartidos(idtorneo);
		nombrepartidos = new ArrayList<String>();
		
		for(int i=0;i<partidos.size();i++){
			nombrepartidos.add(""+helperjugador.obtenerxid(partidos.get(i).getId_juga1(),idtorneo)
			+" "+partidos.get(i).getGol_juga1() + " - "+partidos.get(i).getGol_juga2()
			+" "+helperjugador.obtenerxid(partidos.get(i).getId_juga2(),idtorneo));
		}
		helperpartido.cerrar();
		helperjugador.cerrar();
     
        // Create an array adapter for the list view, using the Ipsum headlines array
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, nombrepartidos));
    }

    @Override
    public void onStart() {
        super.onStart();

        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        /* EN TABLETS if (getFragmentManager().findFragmentById(R.id.partido) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }*/
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Notify the parent activity of selected item
        mCallback.onPartidoSelected(position,idtorneo);
        
        // Set the item as checked to be highlighted when in two-pane layout
        getListView().setItemChecked(position, true);
    }
}
