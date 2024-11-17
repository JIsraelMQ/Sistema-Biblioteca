public class PseudoAleatorio {
    private long seed;

    // Constructor para inicializar la semilla
    public PseudoAleatorio(long seed) {
        this.seed = seed;
    }

    // Método para generar un número pseudoaleatorio entre 0 y 100
    public int next() {
        // Constantes para el Generador Congruencial Lineal
        long a = 1664525;      // Multiplicador
        long c = 1013904223;   // Incremento
        long m = (long) Math.pow(2, 32); // Módulo

        // Actualizar la semilla
        seed = (a * seed + c) % m;

        // Escalar el resultado al rango 0-100
        return (int) (seed % 101);
    }

    public static void main(String[] args) {
        // Inicializar el generador con una semilla
        PseudoAleatorio generator = new PseudoAleatorio(System.currentTimeMillis());

        // Generar y mostrar 10 números pseudoaleatorios
        for (int i = 0; i < 10; i++) {
            System.out.println(generator.next());
        }
    }
}
