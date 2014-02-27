package com.example.adapter;
import java.util.List;

import com.example.mitorneo1.R;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.bean.*;

public class JugadorListAdapter extends ArrayAdapter<Jugador> {
	
	private List<Jugador> items;
	private int layoutResourceId;
	private Context context;

	public JugadorListAdapter(Context context, int layoutResourceId, List<Jugador> items) {
		super(context, layoutResourceId, items);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		JugadorHolder holder = null;

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		holder = new JugadorHolder();
		
		holder.jugador = items.get(position);
		holder.removeJugadorButton = (ImageButton)row.findViewById(R.id.jugador_removePay);
		holder.removeJugadorButton.setTag(holder.jugador);

		holder.name = (TextView)row.findViewById(R.id.jugador_name);
		setNameTextChangeListener(holder);
		//holder.value = (TextView)row.findViewById(R.id.atomPay_value);

		row.setTag(holder);
		
		
		
		setupItem(holder);
		return row;
	}

	private void setupItem(JugadorHolder holder) {
		holder.name.setText(holder.jugador.getName());
		
		//holder.value.setText(String.valueOf(holder.atomPayment.getValue()));
	}

	public static class JugadorHolder {
		Jugador jugador;
		TextView name;
		TextView value;
		ImageButton removeJugadorButton;
	}
	
	private void setNameTextChangeListener(final JugadorHolder holder) {
		holder.name.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				holder.jugador.setName(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

			@Override
			public void afterTextChanged(Editable s) { }
		});
	}

}
