package com.example.hellowordsem9.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hellowordsem9.R;
import com.example.hellowordsem9.models.Movimiento;

import java.util.ArrayList;

public class MovimientoAdapter extends RecyclerView.Adapter<MovimientoAdapter.MovimientoViewHolder> {
    private ArrayList<Movimiento> listaMovimientos;

    public MovimientoAdapter(ArrayList<Movimiento> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
    }

    @NonNull
    @Override
    public MovimientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movimiento, parent, false);
        return new MovimientoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovimientoViewHolder holder, int position) {
        Movimiento movimiento = listaMovimientos.get(position);
        holder.bind(movimiento);
    }

    @Override
    public int getItemCount() {
        return listaMovimientos.size();
    }

    public class MovimientoViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTipoMovimiento;


        public MovimientoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTipoMovimiento = itemView.findViewById(R.id.tvTipoMovimiento);

        }

        public void bind(Movimiento movimiento) {
            tvTipoMovimiento.setText(movimiento.getTipoMovimiento());

        }
    }
}
