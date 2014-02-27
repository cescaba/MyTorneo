package com.example.mitorneo1;


import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final ImageButton btnnuevo = (ImageButton)findViewById(R.id.btnnueva);
		final ImageButton btncarga = (ImageButton)findViewById(R.id.btncargar);
		
		btnnuevo.setOnClickListener(AbrirActivy(this, NuevoActivity.class));
		btncarga.setOnClickListener(AbrirActivy(this,LoadActivity.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public View.OnClickListener AbrirActivy(Context ctx,Class<?> cls){
		final Intent i = new Intent(ctx, cls );
		View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startActivity(i);
		}
	};
		return listener;
	}

}
