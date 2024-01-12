package org.nicmaish.besampsico.utils;

public class TextoUtils {
    public static String obtenerUsuarioDesdeCorreo(String correo) {
        final String EXTENSION_CORREO = "@nicmaish.org";
        int index = correo.indexOf(EXTENSION_CORREO);
        return (index != -1) ? correo.substring(0, index) : correo;
    }
}
