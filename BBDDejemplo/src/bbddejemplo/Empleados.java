/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbddejemplo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario 1 DAM
 */
public class Empleados {
    private Connection conexion;
    private ArrayList<Departamento>empleados;
    
public Empleados() {

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "ejemplo", "ejemplo");
        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
public int Create(Empleado emp) throws SQLException {
        int filas;
        String sql = "INSERT INTO empleados VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, emp.getEmp_no());
        sentencia.setString(2, emp.getApellido());
        sentencia.setString(3, emp.getOficio());
        sentencia.setInt(4, emp.getDir());
        sentencia.setDate(5, emp.getFecha_alt());
        sentencia.setFloat(6, emp.getSalario());
        sentencia.setFloat(7, emp.getComision());
        sentencia.setInt(8, emp.getDept_n());
        filas = sentencia.executeUpdate();
        return filas;
    }

    public int Update(int emp_no, Empleado emp) throws SQLException {
        int filas;
        String sql = "UPDATE empleados SET apellido=?, oficio=?, dir=?, fecha_alt=?, salario=?, comision=?, dept_no=?, where emp_no";
        PreparedStatement sentencia = conexion.prepareCall(sql);
        sentencia.setString(1,emp.getApellido());
        sentencia.setString(2, emp.getOficio());
        sentencia.setInt(3, emp.getDir());
        sentencia.setDate(5, emp.getFecha_alt());
        sentencia.setFloat(5, emp.getSalario());
        sentencia.setFloat(6, emp.getComision());
        sentencia.setInt(7, emp.getDept_n());
        sentencia.setInt(8,emp_no);
        filas = sentencia.executeUpdate();
        return filas;
    }
    public Empleado Read(int emp_no) throws SQLException {
        ResultSet rs;
        String sql = "SELECT empleados WHERE emp_no = ?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, emp_no);
        sentencia.execute();
        rs = sentencia.getResultSet();
        rs.next();
        Empleado emp = new Empleado (rs.getInt("emp_no"), rs.getString("apellido"), rs.getString("oficio"), rs.getInt("dir"), rs.getDate("fecah_alt"), rs.getFloat("salario"), rs.getFloat("comision"), rs.getInt("dept_n"));
        return emp;
    }

    public int Delete(int emp_no) throws SQLException {
        int filas;
        String sql = "DELETE empleados WHERE emp_no = ?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, emp_no);
        filas = sentencia.executeUpdate();
        return filas;
    }
    public void Close() throws SQLException {
        conexion.close();
    }
    public ArrayList <Empleado> ListarEmpleados() throws SQLException{
        ResultSet rs;
        Empleado emp = new Empleado();
        ArrayList<Empleado> employers = new ArrayList<>();
        String sql = "SELECT * FROM Empleados";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.execute();
        rs = sentencia.getResultSet();
        while (rs.next()) {
            emp = new Empleado();
            emp.setEmp_no(rs.getInt(1));
            emp.setApellido(rs.getString(2));
            emp.setOficio(rs.getString(3));
            emp.setDir(rs.getInt(4));
            emp.setFecha_alt(rs.getDate(5));
            emp.setSalario(rs.getFloat(6));
            emp.setComision(rs.getFloat(7));
            emp.setDept_n(rs.getInt(6));
            employers.add(emp);
        }
        return employers;
    }
}