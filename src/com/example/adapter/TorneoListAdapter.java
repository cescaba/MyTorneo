package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.*;
import com.example.bean.*;
import com.example.mitorneo1.R;

public class TorneoListAdapter extends ArrayAdapter<Torneo> {
	protected static final String LOG_TAG = TorneoListAdapter.class.getSimpleName();
	
	private List<Torneo> items;
	private int layoutResourceId;
	private Context context;

	public TorneoListAdapter(Context context, int layoutResourceId, List<Torneo> items) {
		super(context, layoutResourceId, items);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.items = items;
		/* int i = 0;
		while(i > items.size()){
			this.items.add(items.get(i));
		}*/
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		TorneoHolder holder = null;

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		holder = new TorneoHolder();
		
		holder.torneo = items.get(position);
		holder.removePaymentButton = (ImageButton)row.findViewById(R.id.torneo_removePay);
		holder.removePaymentButton.setTag(holder.torneo);
		
		holder.goPaymentButton = (ImageButton)row.findViewById(R.id.torneo_goPay);
		holder.goPaymentButton.setTag(holder.torneo);
		
		//System.out.println("holi : "+items.get(position).getId());
		holder.name = (TextView)row.findViewById(R.id.torneo_name);
		//holder.name.setText(holder.torneo.getName());
		
		setNameTextChangeListener(holder);
		//holder.value = (TextView)row.findViewById(R.id.atomPay_value);

		row.setTag(holder);
		
		setupItem(holder);
		return row;
	}

	private void setupItem(TorneoHolder holder) {
		holder.name.setText(holder.torneo.getName());
		
		//holder.value.setText(String.valueOf(holder.atomPayment.getValue()));
	}

	public static class TorneoHolder {
		Torneo torneo;
		TextView name;
		TextView value;
		ImageButton removePaymentButton;
		ImageButton goPaymentButton;
	}
	
	private void setNameTextChangeListener(final TorneoHolder holder) {
		holder.name.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				holder.torneo.setName(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

			@Override
			public void afterTextChanged(Editable s) { }
		});
	}
}
