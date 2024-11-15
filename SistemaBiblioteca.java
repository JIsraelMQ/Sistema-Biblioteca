import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Libro {
    private String titulo;
    private boolean prestado;

    public Libro(String titulo) {
        this.titulo = titulo;
        this.prestado = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void prestar() {
        prestado = true;
    }

    public void devolver() {
        prestado = false;
    }
}

class Biblioteca {
    private LinkedList<Libro> libros;
    private Queue<String> colaSolicitudes;

    public Biblioteca() {
        libros = new LinkedList<>();
        colaSolicitudes = new LinkedList<>();
    }

    public void agregarLibro(String titulo) {
        libros.add(new Libro(titulo));
        System.out.println("Libro '" + titulo + "' agregado a la biblioteca.");
    }

    public void solicitarLibro(String titulo, String usuario) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equals(titulo) && !libro.isPrestado()) {
                libro.prestar();
                System.out.println("Libro '" + titulo + "' prestado a " + usuario);
                return;
            }
        }
        colaSolicitudes.add(usuario + " solicita el libro '" + titulo + "'");
        System.out.println("Libro no disponible. Usuario " + usuario + " añadido a la cola de solicitudes.");
    }

    public void devolverLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equals(titulo) && libro.isPrestado()) {
                libro.devolver();
                System.out.println("Libro '" + titulo + "' devuelto a la biblioteca.");
                atenderSiguienteSolicitud(titulo);
                return;
            }
        }
        System.out.println("Libro no encontrado o no está prestado.");
    }

    private void atenderSiguienteSolicitud(String titulo) {
        if (!colaSolicitudes.isEmpty()) {
            String solicitud = colaSolicitudes.poll();
            System.out.println("Atendiendo solicitud: " + solicitud);
        } else {
            System.out.println("No hay solicitudes pendientes para el libro '" + titulo + "'");
        }
    }

    public void mostrarLibros() {
        System.out.println("Libros en la biblioteca:");
        for (Libro libro : libros) {
            System.out.println("- " + libro.getTitulo() + " (Prestado: " + libro.isPrestado() + ")");
        }
    }
}

public class SistemaBiblioteca {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        // Agregar algunos libros a la biblioteca
        biblioteca.agregarLibro("El Quijote");
        biblioteca.agregarLibro("Cien Años de Soledad");
        biblioteca.agregarLibro("La Odisea");

        int opcion;
        do {
            System.out.println("\n--- Sistema de Biblioteca ---");
            System.out.println("1. Solicitar libro");
            System.out.println("2. Devolver libro");
            System.out.println("3. Mostrar libros");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título del libro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingrese el nombre del usuario: ");
                    String usuario = scanner.nextLine();
                    biblioteca.solicitarLibro(titulo, usuario);
                    break;
                case 2:
                    System.out.print("Ingrese el título del libro a devolver: ");
                    titulo = scanner.nextLine();
                    biblioteca.devolverLibro(titulo);
                    break;
                case 3:
                    biblioteca.mostrarLibros();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);

        scanner.close();
    }
}
