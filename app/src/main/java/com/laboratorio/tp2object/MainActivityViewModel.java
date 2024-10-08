package com.laboratorio.tp2object;

import static com.laboratorio.tp2object.Request.ApiClient.recuperarUsuario;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.laboratorio.tp2object.Model.Usuario;
import com.laboratorio.tp2object.Request.ApiClient;

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<Usuario> mUsuario;
    private MutableLiveData<String> mError;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Usuario> getMUsuario(){
        if(mUsuario==null){
            mUsuario=new MutableLiveData<>();
        }
        return mUsuario;
    }

    public LiveData<String> getMError(){
        if(mError==null){
            mError=new MutableLiveData<>();
        }
        return mError;
    }

    public void login(String mail, String password){
        Usuario usuarioGuardado = recuperarUsuario(getApplication());
        if(usuarioGuardado!=null && usuarioGuardado.getMail().equals(mail) && usuarioGuardado.getPassword().equals(password)){
            mUsuario.postValue(usuarioGuardado);
        }else{
            mError.postValue("Usuario o contrasenia incorrecto.");
        }
    }

//    // Método para iniciar sesión (Login)
//    public void login(String mail, String password) {
//        Usuario usuarioGuardado = recuperarUsuario();
//
//        if (usuarioGuardado != null && usuarioGuardado.getEmail().equals(mail) && usuarioGuardado.getPassword().equals(password)) {
//            mUsuario.postValue(usuarioGuardado);
//        } else {
//            mError.postValue("Usuario o contraseña incorrectos");
//        }
//    }
//
    // Método para guardar el usuario
    public void guardarUsuario(Usuario usuario) {
        guardarUsuario(usuario);
   }
}
