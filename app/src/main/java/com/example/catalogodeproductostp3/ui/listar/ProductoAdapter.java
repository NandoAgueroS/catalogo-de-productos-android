package com.example.catalogodeproductostp3.ui.listar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catalogodeproductostp3.R;
import com.example.catalogodeproductostp3.model.Producto;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolderProducto> {

    private List<Producto> productos;
    private Context context;
    private LayoutInflater inflater;

    public ProductoAdapter(List<Producto> productos, Context context, LayoutInflater inflater) {
        this.productos = productos;
        this.context = context;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolderProducto(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto holder, int position) {
        Producto producto = productos.get(position);
        holder.tvPrecio.setText(String.valueOf(producto.getPrecio()));
        holder.tvDescripcion.setText(producto.getDescripcion());
        holder.tvCodigo.setText(producto.getCodigo());
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ViewHolderProducto extends RecyclerView.ViewHolder{
        TextView tvDescripcion, tvCodigo, tvPrecio;

        public ViewHolderProducto(@NonNull View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
        }
    }
}

