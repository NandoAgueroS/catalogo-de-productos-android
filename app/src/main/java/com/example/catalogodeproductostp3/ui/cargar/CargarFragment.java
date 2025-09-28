package com.example.catalogodeproductostp3.ui.cargar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.catalogodeproductostp3.databinding.FragmentCargarBinding;
import com.example.catalogodeproductostp3.model.Producto;

public class CargarFragment extends Fragment {

    private FragmentCargarBinding binding;
    private CargarViewModel cargarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cargarViewModel = new ViewModelProvider(this).get(CargarViewModel.class);

        binding = FragmentCargarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        cargarViewModel.getMMensaje().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMensaje.setText(s);
                binding.etPrecio.setText("");
                binding.etDescripcion.setText("");
                binding.etCodigo.setText("");
                binding.tvError.setText("");
            }
        });
        cargarViewModel.getMError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMensaje.setText("");
                binding.tvError.setText(s);
            }
        });

        binding.btCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = binding.etCodigo.getText().toString();
                String descripcion = binding.etDescripcion.getText().toString();
                String precio = binding.etPrecio.getText().toString();
                cargarViewModel.cargarProducto(codigo, descripcion, precio);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}