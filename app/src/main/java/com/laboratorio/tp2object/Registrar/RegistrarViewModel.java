package com.laboratorio.tp2object.Registrar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.laboratorio.tp2object.Model.Usuario;
import com.laboratorio.tp2object.Request.ApiClient;

public class RegistrarViewModel extends AndroidViewModel {
    private MutableLiveData<String> mMsj;
    public RegistrarViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<String> getMMsj(){
        if(mMsj==null){
            mMsj=new MutableLiveData<>();
        }
        return mMsj;
    }

    public void registrar(Usuario usuario){
        ApiClient.guardarUsuario(getApplication(), usuario);
        mMsj.postValue("Registro exitoso!!");
    }
}
