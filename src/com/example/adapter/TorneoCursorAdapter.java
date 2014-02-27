package com.example.adapter;

import com.example.dao.TorneoDAO;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class TorneoCursorAdapter  extends CursorAdapter{
 
	   private TorneoDAO dbAdapter = null ;
	 
	   @SuppressWarnings("deprecation")
	public TorneoCursorAdapter(Context context, Cursor c)
	   {
	      super(context, c);
	      dbAdapter = new TorneoDAO(context);
	      dbAdapter.abrir();
	   }
	 
	   @Override
	   public void bindView(View view, Context context, Cursor cursor)
	   {
	      TextView tv = (TextView) view ;
	 
	     // tv.setText(cursor.getString(cursor.getColumnIndex(TorneoDAO.C_COLUMNA_NOMBRE)));
	   }
	 
	   @Override
	   public View newView(Context context, Cursor cursor, ViewGroup parent)
	   {
	      final LayoutInflater inflater = LayoutInflater.from(context);
	      final View view = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
	 
	      return view;
	   }
	
}
