package com.example.hellowordsem9.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hellowordsem9.DetalleCuentaActivity;
import com.example.hellowordsem9.ListaCuentasActivity;
import com.example.hellowordsem9.R;
import com.example.hellowordsem9.models.Cuentas;

import java.util.ArrayList;

public class CuentaAdapter extends RecyclerView.Adapter<CuentaAdapter.CuentaViewHolder> {

    ArrayList<Cuentas> listaCuentas;
    public CuentaAdapter(ArrayList<Cuentas> listaCuentas){
        this.listaCuentas = listaCuentas;
    }
    @NonNull
    @Override
    public CuentaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuenta,null,false);
        return new CuentaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CuentaViewHolder holder, int position) {
    holder.viewNombre.setText(listaCuentas.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
      return listaCuentas.size();
    }

    public class CuentaViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre;
        public CuentaViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.name_cuenta);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, DetalleCuentaActivity.class);
                    intent.putExtra("ID",listaCuentas.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
