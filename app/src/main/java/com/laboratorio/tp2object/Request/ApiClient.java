package com.laboratorio.tp2object.Request;

import android.content.Context;
import android.widget.Toast;

import com.laboratorio.tp2object.Model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {
    private static final String FILENAME = "usuario.obj";

    // Guardar Usuario utilizando ObjectOutputStream
    public static void guardarUsuario(Context context, Usuario usuario) {
        try {
            // Archivo donde se guardará el objeto
            File archivo = new File(context.getFilesDir(), FILENAME);

            // Canal de salida de datos
            FileOutputStream fos = new FileOutputStream(archivo);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            // Escribe el objeto en el archivo
            oos.writeObject(usuario);

            // Limpia y cierra el flujo de salida
            bos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al guardar el usuario", Toast.LENGTH_SHORT).show();
        }
    }

    // Recuperar Usuario utilizando ObjectInputStream
    public static Usuario recuperarUsuario(Context context) {
        Usuario usuario = null;
        try {
            // Archivo donde se guarda el objeto
            File archivo = new File(context.getFilesDir(), FILENAME);

            if (archivo.exists() && archivo.length() > 0) {
                // Canal de entrada de datos
                FileInputStream fis = new FileInputStream(archivo);
                BufferedInputStream bis = new BufferedInputStream(fis);
                ObjectInputStream ois = new ObjectInputStream(bis);

                // Lee el objeto del archivo
                usuario = (Usuario) ois.readObject();

                // Cierra el flujo de entrada
                ois.close();
            } else {
                Toast.makeText(context, "No hay usuarios guardados", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al recuperar el usuario", Toast.LENGTH_SHORT).show();
        }
        return usuario;
    }

    // Método de Login para verificar el mail y password contra los datos guardados
    public static Usuario login(Context context, String mail, String password) {
        Usuario usuario = recuperarUsuario(context);

        if (usuario != null && usuario.getMail().equals(mail) && usuario.getPassword().equals(password)) {
            return usuario;  // El usuario coincide
        } else {
            return null;  // No se encontró o los datos no coinciden
        }
    }
}
