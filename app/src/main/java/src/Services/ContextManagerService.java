package src.Services;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

/**
 * Para que este servicio funcione correctamente, se debe establecer el Context en la Main Activity
 * De esa forma, se evita hacer Prop Drilling del contexto en cada metodo de la app
 */
public abstract class ContextManagerService {
    @SuppressLint("StaticFieldLeak")
    private static Context context = null;

    public static void setContext(@NonNull Context context) {
        if (ContextManagerService.context == null) {
            ContextManagerService.context = context;
        }
    }

    public static Context getContext() {
        return ContextManagerService.context;
    }
}