/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ujmd.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import vista.Login;
/**
 *
 * @author Cesia Coto
 */
public class Bitacora {
    private Calendar calendario;
    private Calendar c;
    private String dia, mes, anio, archivo, contenido, directorio, fecha;
    private int hora = 0, minutos = 0, segundos = 0;
    private Log lo;

    public void crearBitacora(Log l,String Usuario) {

        //se toman los datos del calendario y horario para meterlos en variables
        this.calendario = Calendar.getInstance();
        this.c = new GregorianCalendar();
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH) + 1);
        anio = Integer.toString(c.get(Calendar.YEAR));

        //System.out.println(hora + ":" + minutos + ":" + segundos);
        //System.out.println(dia + "_" + mes +  "_" + anio); 
        archivo = "bita_" + dia + "-" + mes + "-" + anio + ".txt";
        contenido = "entra " + Usuario + " " + hora + ":" + minutos + ":" + segundos + " " //metodo getUsuario
                + dia + "-" + mes + "-" + anio;
        directorio = "bitacora" + dia + "-" + mes + "-" + anio;
        fecha = dia + "-" + mes + "-" + anio;
        try {
            //se crea el directorio y se verifica si existe o no
            File dir = new File(directorio);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            l.setContenido(e.getMessage());
            l.escribirLog(); // getUsuario 

        }

        //se crea el archivo txt dentro del directorio y se valida si existe
        try {
            File file = new File(directorio + "/" + archivo);
            if (!file.exists()) {
                file.createNewFile();
            }
            //se creal el archivo y se escribe dentro de el lo que contiene 
            //variable contenido
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            l.setContenido(e.getMessage());
            l.escribirLog();
        }
    }

    public void cerrarSesion(Log l,String Usuario) {
        this.calendario = Calendar.getInstance();
        this.c = new GregorianCalendar();
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH) + 1);
        anio = Integer.toString(c.get(Calendar.YEAR));

        //System.out.println(hora + ":" + minutos + ":" + segundos);
        //System.out.println(dia + "_" + mes +  "_" + anio); 
        archivo = "bita_" + dia + "-" + mes + "-" + anio + ".txt";
        contenido = "sale " + Usuario + " " + hora + ":" + minutos + ":" + segundos + " "
                + dia + "-" + mes + "-" + anio;
        directorio = "bitacora" + dia + "-" + mes + "-" + anio;

        try {
            File file = new File(directorio + "/" + archivo);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            l.setContenido(e.getMessage());
            l.escribirLog();
        }
    }
}