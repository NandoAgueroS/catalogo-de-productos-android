package com.example.catalogodeproductostp3.ui.busqueda;

import static com.example.catalogodeproductostp3.MainActivity.listaProductos;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.catalogodeproductostp3.model.Producto;

public class DetalleDelProductoViewModel extends AndroidViewModel {

    private MutableLiveData<Producto> mProducto;
    private MutableLiveData<String> mMensaje;

    public DetalleDelProductoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Producto> getMProducto(){
        if (mProducto == null) {
           mProducto = new MutableLiveData<>();
        }
        return mProducto;
    }

    public LiveData<String> getMMensaje(){
        if (mMensaje == null) {
            mMensaje = new MutableLiveData<>();
        }
        return mMensaje;
    }

    public void mostrarResultado(Bundle bundle){
        if (bundle != null && bundle.containsKey("productoEncontrado")){
            Producto producto = bundle.getSerializable("productoEncontrado", Producto.class);
            mProducto.setValue(producto);
        };
    }
    public void buscarProducto(Bundle bundle){

        if (bundle != null
                && bundle.containsKey("codigoABuscar")
                && !bundle.getString("codigoABuscar").isBlank()){

            String codigo = bundle.getString("codigoABuscar");

            Producto productoEncontrado = listaProductos.stream()
                    .filter(p -> p.getCodigo().equals(codigo))
                    .findFirst()
                    .orElse(null);

            if(productoEncontrado != null){
                mProducto.setValue(productoEncontrado);
            }else{
                mMensaje.setValue("No se encontró el producto");
            }
        }else{
            mMensaje.setValue("No se ingresó ningún código");
        }
    }
}