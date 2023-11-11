/******************************************************************************
Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.List;
import java.util.ArrayList;

class Empleados {
    private int idEmpleado;
    private String nombreEmpleado;
    
    public Empleados(int idEmpleado, String nombreEmpleado){
        this.idEmpleado= idEmpleado;
        this.nombreEmpleado=nombreEmpleado;
    }
    public String getNombreEmpleado(){
        return nombreEmpleado;
    }
}

class Departamento{
    private int idDepartamento;
    private String nombreDepartamento;
    private List<Empleados> empleados; // Agregación
    
    public Departamento(int idDepartamento, String nombreDepartamento){
        this.idDepartamento=idDepartamento;
        this.nombreDepartamento=nombreDepartamento;
        this.empleados=new ArrayList<>();
    }
    public String getNombreDepartamento(){
        return nombreDepartamento;
    }
    
    public void agregarEmpleado(Empleados empleado){
        empleados.add(empleado);
    }
    public List<Empleados> getEmpleados(){
        return empleados;
    }
}

public class Main
{
    private static List<Departamento> obtenerDatosDesdeFuente() {        
        List<Departamento> departamentos = new ArrayList<>();

        Departamento departamentoVentas = new Departamento(1, "Ventas");
        departamentoVentas.agregarEmpleado(new Empleados(1, "Juan"));
        departamentoVentas.agregarEmpleado(new Empleados(2, "María"));

        Departamento departamentoTI = new Departamento(2, "Tecnología de la Información");
        departamentoTI.agregarEmpleado(new Empleados(3, "Carlos"));
        departamentoTI.agregarEmpleado(new Empleados(4, "Ana"));

        departamentos.add(departamentoVentas);
        departamentos.add(departamentoTI);

        return departamentos;
       }
       
    private static List<String> transformarDatos(List<Departamento> departamentos) {
        // Simulamos la transformación de datos según nuestras necesidades
        // En este ejemplo, simplemente convertimos los datos a cadenas de texto para la carga
        List<String> datosTransformados = new ArrayList<>();

        for (Departamento departamento : departamentos) {
            String infoDepartamento = "Departamento: " + departamento.getNombreDepartamento();
            datosTransformados.add(infoDepartamento);

            for (Empleados empleado : departamento.getEmpleados()) {
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
    
    
    
	public static void main(String[] args) {
        // E de Extract: Simulamos la extracción de datos desde alguna fuente (puede ser una base de datos, archivo, etc.)
        List<Departamento> departamentos = obtenerDatosDesdeFuente();

        // T de Transform: Simulamos la transformación de datos según nuestras necesidades
        List<String> datosTransformados = transformarDatos(departamentos);

        // L de Load: Simulamos la carga de datos en un almacén de datos (puede ser una base de datos, archivo, etc.)
        cargarDatosEnAlmacen(datosTransformados);
	}
}
