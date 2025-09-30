package com.example.catalogodeproductostp3.ui.busqueda;

import static android.view.View.INVISIBLE;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catalogodeproductostp3.R;
import com.example.catalogodeproductostp3.databinding.FragmentDetalleDelProductoBinding;
import com.example.catalogodeproductostp3.model.Producto;

public class DetalleDelProductoFragment extends Fragment {

    private FragmentDetalleDelProductoBinding binding;
    private DetalleDelProductoViewModel mViewModel;

    public static DetalleDelProductoFragment newInstance() {
        return new DetalleDelProductoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(DetalleDelProductoViewModel.class);
        //binding = FragmentDetalleDelProductoBinding.inflate(inflater, container, false);
        binding = FragmentDetalleDelProductoBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        mViewModel.getMProducto().observe(getViewLifecycleOwner(), new Observer<Producto>() {
            @Override
            public void onChanged(Producto producto) {
                binding.tvDetalleCodigo.setText(producto.getCodigo());
                binding.tvDetalleDescripcion.setText(producto.getDescripcion());
                binding.tvDetallePrecio.setText(String.valueOf(producto.getPrecio()));
            }
        });
        mViewModel.getMMensaje().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvDetalleMensaje.setText(s);
                binding.tvTituloCodigo.setVisibility(INVISIBLE);
                binding.tvTituloDescripcion.setVisibility(INVISIBLE);
                binding.tvTituloPrecio.setVisibility(INVISIBLE);
            }
        });
        mViewModel.buscarProducto(getArguments());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}