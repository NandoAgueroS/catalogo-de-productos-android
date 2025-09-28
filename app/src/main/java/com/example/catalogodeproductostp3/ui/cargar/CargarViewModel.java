package com.example.catalogodeproductostp3.ui.cargar;

import android.app.Application;
import android.widget.Toast;

import static com.example.catalogodeproductostp3.MainActivity.*;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.catalogodeproductostp3.model.Producto;


public class CargarViewModel extends AndroidViewModel {

    private MutableLiveData<String> mError;
    private MutableLiveData<String> mMensaje;

    public CargarViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMError(){
        if (mError== null) {
           mError= new MutableLiveData<>();
        }
        return mError;
    }

    public LiveData<String> getMMensaje(){
        if (mMensaje== null) {
            mMensaje= new MutableLiveData<>();
        }
        return mMensaje;
    }

    public void cargarProducto(String codigo, String descripcion, String precio){
        boolean valido = validar(codigo, descripcion, precio);

        if (valido){
            listaProductos.add(new Producto(codigo, descripcion, Double.parseDouble(precio)));
            mMensaje.setValue("Producto " + descripcion + " guardado correctamente");
        }
    }

    private boolean validar(String codigo, String descripcion, String precio){
        boolean duplicado = false;
        boolean valido= true;
        StringBuilder mensaje = new StringBuilder();

        if (descripcion.isBlank()){
            mensaje.append("El campo descripción no puede estar vacío \n");
            valido = false;
        }
        if (codigo.isBlank()){
            mensaje.append("El campo código no puede estar vacío \n");
            valido = false;
        }
        if (precio.isBlank()){
            mensaje.append("El campo precio no puede estar vacío \n");
            valido = false;
        }else {
            try{
                double pre = Double.parseDouble(precio);
                duplicado = listaProductos.contains(new Producto(codigo, descripcion, pre));
            } catch (NumberFormatException e) {
                mensaje.append("El campo precio debe ser un número");
                valido = false;
            }
        }
        if(duplicado){
            mensaje.append("Ya existe un producto con ese código \n");
            valido = false;
        }
        mError.setValue(mensaje.toString());
        return valido;

    }



}