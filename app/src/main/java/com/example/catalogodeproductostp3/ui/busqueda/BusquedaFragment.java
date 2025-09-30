package com.example.catalogodeproductostp3.ui.busqueda;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catalogodeproductostp3.R;
import com.example.catalogodeproductostp3.databinding.FragmentBusquedaBinding;
import com.example.catalogodeproductostp3.databinding.FragmentCargarBinding;
import com.example.catalogodeproductostp3.databinding.FragmentDetalleDelProductoBinding;
import com.example.catalogodeproductostp3.model.Producto;

public class BusquedaFragment extends Fragment {

    private BusquedaViewModel mViewModel;

    private FragmentBusquedaBinding binding;
    public static BusquedaFragment newInstance() {
        return new BusquedaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(BusquedaViewModel.class);

        binding = FragmentBusquedaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("codigoABuscar", binding.etCodigoBusqueda.getText().toString());
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main).navigate(R.id.nav_detalle, bundle);
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