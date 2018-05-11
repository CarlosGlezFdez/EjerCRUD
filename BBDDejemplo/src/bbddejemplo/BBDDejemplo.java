/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbddejemplo;

import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario 1 DAM
 */
public class BBDDejemplo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        Departamento dept = null;
        Departamentos bddept = new Departamentos();
        Empleado emp = null;
        Empleados bdemp = new Empleados();
        int opcion;
        //Empleados
        int emp_no;
        String apellido;
        String oficio;
        int dir;
        Date fecha_alt;
        float salario;
        float comision;
        int dept_n;
        //Departamentos
        int dept_no;
        String dnombre;
        String loc;

        System.out.println("---------------OPCIONES----------------");
        System.out.println("1. <--Listar los departamentos--------> \n"
                + "2. <--Listar los empleados------------> \n"
                + "3. <--Buscar empleados por su nombre--> \n"
                + "4. <--Buscar departamento por nombre--> \n"
                + "5. <--Contratar empleado--------------> \n"
                + "6. <--Crear departamento--------------> \n"
                + "7. <--Despedir empleado---------------> \n"
                + "8. <--Borrar departamento-------------> \n"
                + "\n"
                + "0. Salir \n");
        // lees numneros
        Scanner sc = new Scanner(System.in);
        opcion = sc.nextInt();
        // En cada caso hacer cosas
        switch (opcion) {
            case 1: // llamar a listar departamentos
                int opciones;
                opciones = sc.nextInt();
                if (opciones == 1) {
                    ArrayList<Departamento> deps = new ArrayList<>();

                    deps = bddept.ListarDepartamentos();
                    for (int i = 0; i < deps.size(); i++) {
                        System.out.println(deps.get(i).getDept_no() + " | " + deps.get(i).getDnombre() + " | " + deps.get(i).getLoc());
                    }
                } else if (opciones == 2) {
                    dept_no = sc.nextInt();
                    dept = bddept.Read(dept_no);
                    System.out.printf("+----------------------------------------------------+ %n");
                    System.out.printf("|%-5s |%-10s |%-15s |%n", "Apellido", "Departamento", "Localidad");
                    System.out.printf("+----------------------------------------------------+ %n");

                    System.out.printf("|%05d |%-10s |%-15s |%n", dept.getDept_no(), dept.getDnombre(), dept.getLoc());

                } else {
                    System.out.println("Error bro");
                }

                break;
            case 2:
                int opcions;
                opcions = sc.nextInt();
                if (opcions == 1) {
                    ArrayList<Empleado> emps = new ArrayList<>();

                    emps = bdemp.ListarEmpleados();
                    for (int i = 0; i < emps.size(); i++) {
                        System.out.println(emps.get(i).getEmp_no() + " | " + emps.get(i).getApellido() + " | " + 
                                emps.get(i).getOficio()+ " | " + emps.get(i).getDir()+ " | " + emps.get(i).getFecha_alt()
                                + " | " + emps.get(i).getSalario()+ " | " + emps.get(i).getComision()+ " | " +
                                emps.get(i).getDept_n()+ " | " );
                    }
                } else if (opcions == 2) {
                    emp_no = sc.nextInt();
                    emp = bdemp.Read(emp_no);
                    System.out.printf("+----------------------------------------------------+ %n");
                    System.out.printf("|%-5s |%-10s |%-15s |%n", "Apellido", "Departamento", "Localidad");
                    System.out.printf("+----------------------------------------------------+ %n");

                    System.out.printf("|%05d |%-10s |%-15s |%n", dept.getDept_no(), dept.getDnombre(), dept.getLoc());

                } else {
                    System.out.println("Error bro");
                }
                break;
            case 3:
                break;
            case 4:
                dnombre = sc.next();
                dept = bddept.ReadNombre(dnombre);
                System.out.printf("+----------------------------------------------------+ %n");
                System.out.printf("|%-5s |%-10s |%-15s |%n", "Apellido", "Departamento", "Localidad");
                System.out.printf("+----------------------------------------------------+ %n");

                System.out.printf("|%05d |%-10s |%-15s |%n", dept.getDept_no(), dept.getDnombre(), dept.getLoc());
                break;
            case 5:
                break;
            case 6:
                System.out.println("Vale has elegido la opcion de crear un nuevo departamento");
                System.out.println("Lo primero, dime el número que quieres que tenga el nuevo departamento");
                dept_no = sc.nextInt();
                System.out.println("Ahora dime el nombre del departamento");
                dnombre = sc.next();
                System.out.println("Y dime la localización, la calle, en la que se encuentra el departamento");
                loc = sc.next();
                dept = new Departamento(dept_no, dnombre, loc);
                bddept.Create(dept);
                System.out.println("Vale, pues ya esta creado el nuevo departamento");

                break;
            case 7:
                System.out.println("Dime el número el ID del empleado que quieres despedir");
                emp_no = sc.nextInt();
                bdemp.Delete(emp_no);
                System.out.println("Ya hemos borrado el departamento" + emp_no);
                break;
            case 8:
                System.out.println("Dime el número del departamneto que quieres borrar");
                dept_no = sc.nextInt();
                bddept.Delete(dept_no);
                System.out.println("Ya hemos borrado el departamento" + dept_no);
                break;
        }
    }
}
