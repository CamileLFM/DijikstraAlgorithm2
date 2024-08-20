import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        System.out.println("Teste do Algorítimo de Dijkstra:");

        DijkstraAlgorithm grafo = new DijkstraAlgorithm(6);

        grafo.CriarAresta(0, 1, 1);
        grafo.CriarAresta(0, 2, 4);
        grafo.CriarAresta(1, 2, 2);
        grafo.CriarAresta(1, 3, 5);
        grafo.CriarAresta(2, 3, 1);

        List<Integer> caminho = grafo.menorCaminho(0, 3);

        System.out.println("Menor caminho do nó 0 ao nó 4: " + caminho);
    }
}
