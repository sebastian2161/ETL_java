/******************************************************************************
Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

class Empleado {
    private int idEmpleado;
    private String nombreEmpleado;

    public Empleado(int idEmpleado, String nombreEmpleado) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
    }

    // Métodos getter y setter para acceder a los atributos
}

class Departamento {
    private int idDepartamento;
    private String nombreDepartamento;
    private List<Empleado> empleados; // Agregación

    public Departamento(int idDepartamento, String nombreDepartamento) {
        this.idDepartamento = idDepartamento;
        this.nombreDepartamento = nombreDepartamento;
        this.empleados = new ArrayList<>();
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }
}


public class ProcesoETLJDBC {
    public static void main(String[] args) {
        // E de Extract: Extraer datos de la base de datos
        List<Departamento> departamentos = extraerDatosDesdeBD();

        // T de Transform: Transformar los datos según nuestras necesidades
        List<String> datosTransformados = transformarDatos(departamentos);

        // L de Load: Cargar los datos transformados en el almacén (puede ser una base de datos, archivo, etc.)
        cargarDatosEnAlmacen(datosTransformados);
    }

    private static List<Departamento> extraerDatosDesdeBD() {
        List<Departamento> departamentos = new ArrayList<>();

        String jdbcURL = "jdbc:mysql://localhost:3306/tu_base_de_datos";
        String usuario = "tu_usuario";
        String contraseña = "tu_contraseña";

        try (Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña)) {
            String consulta = "SELECT d.idDepartamento, d.nombreDepartamento, e.idEmpleado, e.nombreEmpleado " +
                              "FROM Departamentos d " +
                              "LEFT JOIN Empleados e ON d.idDepartamento = e.idDepartamento";
            try (PreparedStatement declaracion = conexion.prepareStatement(consulta);
                 ResultSet resultado = declaracion.executeQuery()) {

                while (resultado.next()) {
                    int idDepartamento = resultado.getInt("idDepartamento");
                    String nombreDepartamento = resultado.getString("nombreDepartamento");
                    int idEmpleado = resultado.getInt("idEmpleado");
                    String nombreEmpleado = resultado.getString("nombreEmpleado");

                    Departamento departamentoExistente = null;

                    for (Departamento departamento : departamentos) {
                        if (departamento.getIdDepartamento() == idDepartamento) {
                            departamentoExistente = departamento;
                            break;
                        }
                    }

                    if (departamentoExistente == null) {
                        departamentoExistente = new Departamento(idDepartamento, nombreDepartamento);
                        departamentos.add(departamentoExistente);
                    }

                    if (idEmpleado > 0) {
                        Empleado empleado = new Empleado(idEmpleado, nombreEmpleado);
                        departamentoExistente.agregarEmpleado(empleado);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departamentos;
    }

    private static List<String> transformarDatos(List<Departamento> departamentos) {
        List<String> datosTransformados = new ArrayList<>();

        for (Departamento departamento : departamentos) {
            String infoDepartamento = "Departamento: " + departamento.getNombreDepartamento();
            datosTransformados.add(infoDepartamento);

            for (Empleado empleado : departamento.getEmpleados()) {
                String infoEmpleado = "  Empleado: " + empleado.getNombreEmpleado();
                datosTransformados.add(infoEmpleado);
            }
        }

        return datosTransformados;
    }

    private static void cargarDatosEnAlmacen(List<String> datosTransformados) {
        // Simulamos la carga de datos en un almacén de datos (puede ser una base de datos, archivo, etc.)
        // En este ejemplo, simplemente imprimimos los datos transformados
        System.out.println("Datos transformados cargados en el almacén:");
        for (String dato : datosTransformados) {
            System.out.println(dato);
        }
    }
}
