import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Problema5 {

    private class IntTriple implements Comparable<IntTriple> {

        private int first;
        private int second;
        private int cost;

        public IntTriple(int pFirst, int pSecond, int pCost) {
            first = pFirst;
            second = pSecond;
            cost = pCost;
        }

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(IntTriple other) {
            return this.getCost() - other.getCost();
        }

    }

    private class Partition {
        private int[] parents;
        private int[] rankings;

        public Partition(int n) {
            parents = new int[n];
            rankings = new int[n];
            // assume ranking heights as one-indexed
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                rankings[i] = 1;
            }
        }

        public int find(int vertice) {
            int children = vertice;
            int parent = parents[children];
            while (parent != children) {
                children = parent;
                parent = parents[children];
            }
            parents[vertice] = parent;
            return parent;
        }

        public void union(int v1, int v2) {
            int s1 = this.find(v1);
            int s2 = this.find(v2);
            if (rankings[s1] >= rankings[s2]) {
                parents[s2] = s1;
                if (rankings[s1] == rankings[s2]) {
                    rankings[s1]++;
                }
            } else {
                parents[s1] = s2;
            }
        }

        public boolean sameSubset(int v1, int v2) {
            return find(v1) == find(v2);
        }

    }

    private class Kruskal {

        private Problema5 instance;

        public Kruskal(Problema5 pInstance) {
            instance = pInstance;
        }

        /**
         * 
         * calcula el MST para un grafo no dirigido cualquiera
         * 
         * @param n    cantidad de vertices del grafo
         * @param ejes arreglo de ejes del grafo
         * @return arreglo de ejes que componen un MST
         */
        public IntTriple[] kruskalMSTGeneral(int n, IntTriple[] ejes) {

            IntTriple current;
            int v1;
            int v2;
            Partition disyuntos = instance.new Partition(n);
            LinkedList<IntTriple> mst = new LinkedList<IntTriple>(); // Arraylist porque el grafo puede ser desconectado
            Arrays.sort(ejes);
            int i = 0; // contar ejes
            for (; i < ejes.length;) {
                current = ejes[i];
                v1 = current.getFirst();
                v2 = current.getSecond();
                if (!disyuntos.sameSubset(v1, v2)) {
                    disyuntos.union(v1, v2);
                    mst.add(current);
                }
                i++;
            }
            IntTriple[] mstArray = mst.toArray(new IntTriple[0]);
            return mstArray;
        }

        /**
         * 
         * calcula el MST para un grafo no dirigido conectado
         * 
         * @param n    cantidad de vertices del grafo
         * @param ejes arreglo de ejes del grafo
         * @return arreglo de ejes que componen un MST
         */
        public IntTriple[] kruskalMSTConnected(int n, IntTriple[] ejes) {

            IntTriple current;
            int v1;
            int v2;
            Partition disyuntos = instance.new Partition(n);
            IntTriple[] mst = new IntTriple[n - 1];
            Arrays.sort(ejes);
            int i = 0; // contar ejes
            int j = 0; // contar ejes agregados al mst
            for (; j < mst.length;) {
                current = ejes[i];
                v1 = current.getFirst();
                v2 = current.getSecond();
                if (!disyuntos.sameSubset(v1, v2)) {
                    disyuntos.union(v1, v2);
                    mst[j] = current;
                    j++;
                }
                i++;
            }

            return mst;
        }
    }

    public static void main(String[] args) {
        Problema5 instance = new Problema5();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        try {
            // de la forma '\d+ (D|C)' con D = diesconectado y C = conectado
            String[] firstLine = br.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]);
            boolean isConnected = firstLine[1].equals("C");
            ArrayList<IntTriple> ejes = new ArrayList<IntTriple>();
            while (!((line = br.readLine()).equals("0"))) {
                String[] edge = line.split(" ");
                int v1 = Integer.parseInt(edge[0]);
                int v2 = Integer.parseInt(edge[1]);
                int costo = Integer.parseInt(edge[2]);
                IntTriple tripla = instance.new IntTriple(v1, v2, costo);
                ejes.add(tripla);
            }
            Kruskal kruskal = instance.new Kruskal(instance);
            IntTriple[] ejesArray = ejes.toArray(new IntTriple[0]);
            IntTriple[] mst = (isConnected) ? kruskal.kruskalMSTConnected(n, ejesArray)
                    : kruskal.kruskalMSTGeneral(n, ejesArray);
            for (int i = 0; i < mst.length; i++) {
                System.out.println(mst[i].getFirst() + " " + mst[i].getSecond() + " " + mst[i].getCost());
            }

        } catch (IOException e) {
            System.out.println("Error leyendo la entrada");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("error leyendo la línea " + line);
            e.printStackTrace();
        }
    }

}