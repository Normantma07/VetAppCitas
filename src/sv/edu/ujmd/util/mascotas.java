/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ujmd.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.conexion;

/**
 *
 * @author N - C - W 
 */
public class mascotas {
     private int idMascotas;
    private String nombre_masc;
    private String especie;
    private String raza;
    private String edad;
    private int idPersona;
    private int idRoles;
    
    private String Insertar = "CALL InsertarMascota(?,?,?,?,?)";
    private String Modificar = "CALL ModificarMascota(?,?,?,?,?)";
    private String Eliminar = "CALL BorrarMascotas(?)";
    private String Seleccion = "select * from mascotas";
    private int Columnas = 0;
    private Connection con;

    /*public mascotas(Connection con) {
        con = conexion.Conectar();
    }*/

    public mascotas() {
        con = conexion.Conectar(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    public int getIdMascotas() {
        return idMascotas;
    }

    public void setIdMascotas(int idMascotas) {
        this.idMascotas = idMascotas;
    }

    public String getNombre_masc() {
        return nombre_masc;
    }

    public void setNombre_masc(String nombre_masc) {
        this.nombre_masc = nombre_masc;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getInsertar() {
        return Insertar;
    }

    public void setInsertar(String Insertar) {
        this.Insertar = Insertar;
    }

    public String getModificar() {
        return Modificar;
    }

    public void setModificar(String Modificar) {
        this.Modificar = Modificar;
    }

    public String getEliminar() {
        return Eliminar;
    }

    public void setEliminar(String Eliminar) {
        this.Eliminar = Eliminar;
    }

    public String getSeleccion() {
        return Seleccion;
    }

    public void setSeleccion(String Seleccion) {
        this.Seleccion = Seleccion;
    }

    public int getColumnas() {
        return Columnas;
    }

    public void setColumnas(int Columnas) {
        this.Columnas = Columnas;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    
    
    

    


    
    //
    
    public DefaultTableModel CargarDatosM() {
        DefaultTableModel modelo = new DefaultTableModel();
        try {
            Connection con;
            con = conexion.Conectar();
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery(getSeleccion());
            ResultSetMetaData md = rs.getMetaData();
            int columnas = md.getColumnCount();
            for (int i = 1; i <= columnas; i++) {
                modelo.addColumn(md.getColumnLabel(i));
            }
            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
                fila = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelo;
    }
    
    public String IngresarDatos(int codigo, String nombre, String apellido, String especie, String edad) {
        String resultado;
        try {
            CallableStatement cmst = con.prepareCall(getInsertar());
            cmst.setInt(1, codigo);
            cmst.setString(2, nombre);
            cmst.setString(3, apellido);
            cmst.setString(4, especie);
            cmst.setString(5, edad);
            
            cmst.execute();
            resultado = " Los datos se ingresaron exitosamente";
            con.close();
        } catch (SQLException sqlex) {
            resultado = " No se realizo la operacion " + sqlex.getMessage();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            resultado = " No se realizo la operacion " + ex.getMessage();
        }
        return resultado;
    }
    
    public String ModificarDatos(int codigo, String nombre, String apellido, String especie, String edad) {
        String resultado;
        try {
            con = conexion.Conectar();
            CallableStatement cmst = con.prepareCall(getModificar());
            cmst.setInt(1, codigo);
            cmst.setString(2, nombre);
            cmst.setString(3, apellido);
            cmst.setString(4, especie);
            cmst.setString(5, edad);
            
            cmst.execute();
            resultado = "Los datos se modificaron correctamente !!!";
            con.close();
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
            resultado = "No se realizo la operacion" + sqlex.getMessage();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            resultado = "No se realizo la operacion" + ex.getMessage();
        }
        return resultado;
    }
    
    
    public String EliminarDatos(int codigo) {
        String resultado;
        try {
            con = conexion.Conectar();
            CallableStatement cmst = con.prepareCall(getEliminar());
            cmst.setInt(1, codigo);
            cmst.execute();
            resultado = "Los datos se eliminaron correctamente";
            con.close();
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
            resultado = "No se realizo la operacion" + sqlex.getMessage();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            resultado = "No se realizo la operacion" + ex.getMessage();
        }
        return resultado;
    }
    
    
    
}


