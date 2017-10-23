/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class HR {

    public int insertarRegion(Region region) {

        return 1;
    }

    public int borrarRegion(int regionId) {

        return 1;
    }

    public int modificarRegion(int regionId, Region region) {

        return 1;
    }

    public Region leerRegion(int regionId) {

        return null;
    }

    public ArrayList<Region> leerRegions() {

        return null;
    }

    public int insertarCountry(Country country) {

        return 1;
    }

    public int borrarCountry(String countryId) {
        int registrosAfectados = 0;
        try {
            System.out.println("----- Acciones Previas");
            System.out.println("Carga del driver de Oracle en memoria");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Creación de una conexión a una BD Oracle llamada HR");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "kk");
            System.out.println("Creación de un objeto sentencia llamable asociado a dicha conexión");
            String llamada = "call borra_country(?)";
            CallableStatement sentenciaLlamable = conexion.prepareCall(llamada);

            System.out.println("----- Lanzamiento de la sentencia llamable");
            sentenciaLlamable.setString(1, countryId);
            System.out.println("Llamada final: " + sentenciaLlamable.toString());
            sentenciaLlamable.execute();
            System.out.println("Procedimiento Almacenado realizado");

            System.out.println("----- Cerrar la Conexión a la BD");
            sentenciaLlamable.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no Encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getErrorCode() + " - " + ex.getMessage());
        }

        return registrosAfectados;
    }

    public int modificarCountry(int countryId, Country country) {

        return 1;
    }

    public Country leerCountry(int countryId) {

        return null;
    }

    public ArrayList<Country> leerCountries() {

        return null;
    }

    public int insertarLocation(Location location) {

        return 1;
    }

    public int borrarLocation(int locationId) {

        return 1;
    }

    public int modificarLocation(int locationId, Location location) {

        return 1;
    }

    public Country leerLocation(int locationId) {

        return null;
    }

    public ArrayList<Location> leerLocations() {

        return null;
    }

    public int insertarDepartment(Department department) {
        return 1;
    }

    public int borrarDepartment(int departmentId) {

        return 1;
    }

    public int modificarDepartment(int departmentId, Department department) {
        int registrosAfectados = 0;
        String dml = "";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "kk");
            java.sql.Statement sentencia = conexion.createStatement();

            dml = "update DEPARTMENTS"
                    + "DEPARTMENT_ID = " + department.getDepartmentId() + ","
                    + "DEPARTMENT_NAME = " + department.getDepartmentName() + ","
                    + "MANAGER_ID = " + department.getManager() + ","
                    + "LOCATION_ID = " + department.getLocation() + ","
                    + "where DEPARTMENT_ID = " + department.getDepartmentId();
            registrosAfectados = sentencia.executeUpdate(dml);

            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dml);
            switch (ex.getErrorCode()) {
                case 2291:
                    excepcionHR.setMensajeErrorUsuario("La localidad elegida no existe o el jefe de departamnento no es un empleado");
                    break;
                case 1407:
                    excepcionHR.setMensajeErrorUsuario("El identificador y el nombre");
                    break;
                case 2292:
                    excepcionHR.setMensajeErrorUsuario("No se puede modificar el identificador del departamento");
                    break;
                case 1:
                    excepcionHR.setMensajeErrorUsuario("El identificador de departamento");
                    break;
                default:
                    excepcionHR.setMensajeErrorUsuario("Error en el sistema, consulte con el administrador.");
                    break;
            }
        }

        return registrosAfectados;

    }

    public Department leerDepartment(int departmentId) {

        return null;
    }

    public ArrayList<Department> leerDepartments() {

        return null;
    }

    public int insertarEmployee(Employee employee) {

        return 1;
    }

    public int borrarEmployee(int employeeId) {

        return 1;
    }

    public int modificarEmployee(int employeeId, Employee employee) {

        return 1;
    }

    public Employee leerEmployee(int employeeId) {

        return null;
    }

    public ArrayList<Employee> leerEmployees() {

        return null;
    }

    public int insertarJob(Job job) {

        return 1;
    }

    public int borrarJob(String jobId) {

        return 1;
    }

    public int modificarJob(String jobId, Job job) {

        return 1;
    }

    public Job leerJob(String jobId) {

        return null;
    }

    public ArrayList<Job> leerJobs() {

        return null;
    }

    public int insertarJobHistory(JobHistory jobHistory) {

        return 1;
    }

    public int borrarJobHistory(int employeeId, Date startDate) {

        return 1;
    }

    public int modificarJobHistory(int employeeId, Date startDate, JobHistory jobHistory) {

        return 1;
    }

    public JobHistory leerJobHistory(int employeeId, Date startDate) {

        return null;
    }

    public ArrayList<JobHistory> leerJobHistories() {

        return null;
    }
}
