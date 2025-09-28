package com.example.catalogodeproductostp3.ui.listar;

import static com.example.catalogodeproductostp3.MainActivity.listaProductos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.catalogodeproductostp3.MainActivity;
import com.example.catalogodeproductostp3.model.Producto;

import java.util.ArrayList;
import java.util.Comparator;

public class ListarViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Producto>> mProductos;


    public LiveData<ArrayList<Producto>> getMProducto() {
        if (mProductos == null) {
           mProductos = new MutableLiveData<>();
        }
        return mProductos;
    }

    public void listarProductos(){
        listaProductos.sort(new Comparator<Producto>() {
            @Override
            public int compare(Producto o1, Producto o2) {
                return o1.getDescripcion().compareTo(o2.getDescripcion());
            }
        });
        mProductos.setValue(listaProductos);
    }
}