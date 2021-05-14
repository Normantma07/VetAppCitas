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
 * @author Norman
 */
public class Citas {
    
    private int idCitas;
    private String idPersonas;
    private String idMascotas;
    private String hora;
    private String prioridad;
    private int motivo_cita;
    
    
    private String Insertar = "CALL InsertarCita(?,?,?,?,?,?)";
    private String Modificar = "CALL ModificarCita(?,?,?,?,?,?)";
    private String Eliminar = "CALL BorrarCita(?)";
    private String Seleccion = "select * from Citas";
    private int Columnas = 0;
    private Connection con;

    public Citas() {
        con = conexion.Conectar();
    }
    
    
    
    

    public int getIdCitas() {
        return idCitas;
    }

    public void setIdCitas(int idCitas) {
        this.idCitas = idCitas;
    }

    public String getIdPersonas() {
        return idPersonas;
    }

    public void setIdPersonas(String idPersonas) {
        this.idPersonas = idPersonas;
    }

    public String getIdMascotas() {
        return idMascotas;
    }

    public void setIdMascotas(String idMascotas) {
        this.idMascotas = idMascotas;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public int getMotivo_cita() {
        return motivo_cita;
    }

    public void setMotivo_cita(int motivo_cita) {
        this.motivo_cita = motivo_cita;
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
    
    public DefaultTableModel CargarDatosC() {
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
    
    public String IngresarDatos(int codigo, String idPersona, String idMascota, String hora, String prioridad, String motivo) {
        String resultado;
        try {
            CallableStatement cmst = con.prepareCall(getInsertar());
            cmst.setInt(1, codigo);
            cmst.setString(2, idPersona);
            cmst.setString(3, idMascota);
            cmst.setString(4, hora);
            cmst.setString(5, prioridad);
            cmst.setString(6, motivo);
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
    
    public String ModificarDatos(int codigo, String idPersona, String idMascota, String hora, String prioridad, String motivo) {
        String resultado;
        try {
            con = conexion.Conectar();
            CallableStatement cmst = con.prepareCall(getModificar());
            cmst.setInt(1, codigo);
            cmst.setString(2, idPersona);
            cmst.setString(3, idMascota);
            cmst.setString(4, hora);
            cmst.setString(5, prioridad);
            cmst.setString(6, motivo);
            
            cmst.execute();
            resultado = "Los datos se modificaron correctamente";
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
