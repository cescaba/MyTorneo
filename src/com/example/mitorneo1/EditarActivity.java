package com.example.mitorneo1;


import java.util.List;

import com.example.bean.Jugador;
import com.example.bean.Partido;
import com.example.bean.Torneo;
import com.example.dao.PartidoDAO;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class EditarActivity extends FragmentActivity 
	implements ListaPartidosFragment.OnHeadlineSelectedListener{
	long id_torneo = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.partidos_jugados);
		// Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.fragment_container) != null) {
        	Bundle bundle = getIntent().getExtras();
    		Torneo torneo = (Torneo)bundle.get("torneo");
    		
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ExampleFragment
            ListaPartidosFragment firstFragment = new ListaPartidosFragment();
            Bundle args = new Bundle();
            args.putLong(ListaPartidosFragment.TORNEO, torneo.getId());
            System.out.println("EditarActivity idTorneo: "+torneo.getId());
            firstFragment.setArguments(args);
            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            //firstFragment.setArguments(getIntent().getExtras());
            System.out.println("Entro acaa");
            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.editar, menu);
		return true;
	}
	 public void onPartidoSelected(int position, long idtorneo) {
	        // The user selected the headline of an article from the HeadlinesFragment

	        //Esto pasa solo con tablets
		 	// Capture the article fragment from the activity layout
	       	/*PartidoFragment articleFrag = (PartidoFragment)
	                getSupportFragmentManager().findFragmentById(R.id.article_fragment);

	        if (articleFrag != null) {
	            // If article frag is available, we're in two-pane layout...

	            // Call a method in the ArticleFragment to update its content
	            articleFrag.updateArticleView(position);

	        } else {*/
	        
	            // If the frag is not available, we're in the one-pane layout and must swap frags...

	            // Create fragment and give it an argument for the selected article
		 		PartidoFragment newFragment = new PartidoFragment();
	            Bundle args = new Bundle();
	            args.putInt(PartidoFragment.ARG_POSITION, position);
	            args.putLong(PartidoFragment.TORNEO, idtorneo);
	            System.out.println("entro aca Editar Activty");
	            newFragment.setArguments(args);
	            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

	            // Replace whatever is in the fragment_container view with this fragment,
	            // and add the transaction to the back stack so the user can navigate back
	            transaction.replace(R.id.fragment_container, newFragment);
	            transaction.addToBackStack(null);

	            // Commit the transaction
	            transaction.commit();
	        //}
	    }
}
