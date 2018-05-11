/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbddejemplo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario 1 DAM
 */
public class Departamentos {

    private Connection conexion;
    private ArrayList<Departamento>departamentos;

    public Departamentos() {

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "ejemplo", "ejemplo");
        } catch (SQLException ex) {
            Logger.getLogger(Departamentos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    
        public int Create(Departamento dep) throws SQLException {

        int filas = 0;
        String sql = "INSERT INTO departamentos VALUES" + " ( ?, ?, ? )";
        PreparedStatement sentencia;

        sentencia = conexion.prepareStatement(sql);

        sentencia.setInt(1, dep.getDept_no());
        sentencia.setString(2, dep.getDnombre());
        sentencia.setString(3, dep.getLoc());

        filas = sentencia.executeUpdate();
        return filas;

    }


    public int voidUpdate(int dept_no, Departamento dept)throws SQLException {
        int filas;
        String sql = "UPDATE departamentos SET dnombre=?, loc=?, where dept_no";
        PreparedStatement sentencia = conexion.prepareCall(sql);
        sentencia.setString(1,dept.getDnombre());
        sentencia.setString(2, dept.getLoc());
        sentencia.setInt(3,dept_no);
        filas = sentencia.executeUpdate();
        return filas;
        
    }
    public Departamento Read(int dept_no) throws SQLException{
        ResultSet rs;
        String sql = "SELECT * FROM departamentos WHERE dept_no = " + dept_no;
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.execute();
        rs = sentencia.getResultSet();
        rs.next();
        Departamento dept = new Departamento (rs.getInt("dept_no"), rs.getString("dnombre"), rs.getString("loc"));
        return dept;
    }
    public Departamento ReadNombre(String dnombre) throws SQLException{
        ResultSet rs;
        String sql = "SELECT * FROM departamentos WHERE dnombre = '" + dnombre + "'";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.execute();
        rs = sentencia.getResultSet();
        rs.next();
        Departamento dept = new Departamento (rs.getInt("dept_no"), rs.getString("dnombre"), rs.getString("loc"));
        return dept;
    }

    public int Delete(int dept_no) throws SQLException{
        int filas;
        String sql = "DELETE from departamentos WHERE dept_no = ?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, dept_no);
        filas = sentencia.executeUpdate();
        return filas;
    }
    public void Close() throws SQLException {
        conexion.close();
    }
    public ArrayList <Departamento> ListarDepartamentos() throws SQLException{
        ResultSet rs;
        Departamento dept = new Departamento();
        ArrayList<Departamento> departs = new ArrayList<>();
        String sql = "SELECT * FROM Departamentos";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.execute();
        rs = sentencia.getResultSet();
        while (rs.next()) {
            dept = new Departamento();
            dept.setDept_no(rs.getInt(1));
            dept.setDnombre(rs.getString(2));
            dept.setLoc(rs.getString(3));
            departs.add(dept);
        }
        return departs;
    }
}