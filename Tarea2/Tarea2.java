import java.util.Arrays;

public class Tarea2 {

    private class DirectedIntPair implements Comparable<DirectedIntPair> {

        private int origin;
        private int destination;
        private int capacity;

        public DirectedIntPair(int pOrigin, int pDestination, int pCapacity) {
            origin = pOrigin;
            destination = pDestination;
            capacity = pCapacity;
        }

        public int getOrigin() {
            return origin;
        }

        public int getDestination() {
            return destination;
        }

        public int getCapacity() {
            return capacity;
        }

        @Override
        public int compareTo(DirectedIntPair other) {
            return this.getCapacity() - other.getCapacity();
        }

    }

    public static void main(String[] args) {
        System.out.println("lol");
    }

    /**
     * 
     * @param matrizAdyacencia el grafo dirigido con la superfuente y el
     *                         superdestino
     * @param camino           el camino por el que se envía flujo
     * @return int[][] el grafo residual resultante
     */
    public static int[][] darGrafoResidual(int[][] matrizAdyacencia, Iterable<DirectedIntPair> camino) {
        // copiar el grafo original
        int[][] grafoResidual = Arrays.stream(matrizAdyacencia).map(int[]::clone).toArray(int[][]::new);
        for (DirectedIntPair eje : camino) {
            // restar flujo del eje usado
            grafoResidual[eje.getOrigin()][eje.getDestination()] -= eje.getCapacity();
            // ? crear el eje rojo inverso
            grafoResidual[eje.getDestination()][eje.getOrigin()] += eje.getCapacity();
        }
        return grafoResidual;
    }
}