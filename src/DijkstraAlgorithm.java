
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DijkstraAlgorithm {
	
	private Integer vertices[][];

    public DijkstraAlgorithm (Integer numVertices){
        vertices = new Integer[numVertices][numVertices];
    }

    public void CriarAresta (Integer noOrigem, Integer noDestino, Integer pesoNo){
        if (pesoNo > 0){
            vertices[noOrigem][noDestino] = pesoNo;
            vertices[noDestino][noOrigem] = pesoNo;
        } else {
            throw new InvalidParameterException("O peso entre os nós " + noOrigem + " e " + noDestino + " não pode ser negativo!");
        }
    }

    private Integer getMaisProximo (Integer listaCustos[], Set<Integer> naoVisitados){
        Integer minDistancia = Integer.MAX_VALUE;
        Integer noProximo = 0;
        for (Integer i : naoVisitados) {
            if (listaCustos[i] < minDistancia) {
                minDistancia = listaCustos[i];
                noProximo = i;
            }
        }
        return noProximo;
    }

    private List<Integer> getVizinhos(Integer no) {
        List<Integer> vizinhos = new ArrayList<>();
    
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[no][i] != null && vertices[no][i] > 0) {
                vizinhos.add(i);
            }
        }
        return vizinhos;
    }
    

    private Integer getCusto(Integer noOrigem, Integer noDestino) {
        return vertices[noOrigem][noDestino];
    }

    public List<Integer> menorCaminho (Integer noOrigem, Integer noDestino) {
        Integer custo[] = new Integer[vertices.length];
        Integer auxiliar[] = new Integer[vertices.length];
        Set<Integer> naoVisitados = new HashSet<Integer>();

        custo[noOrigem] = 0;

        for ( int num = 0 ; num < vertices.length ; num++ ) {
            if (num != noOrigem) {
                custo[num] = Integer.MAX_VALUE;
            }
            auxiliar[num] = -1;
            naoVisitados.add(num);
        }

        while (!naoVisitados.isEmpty()) {
            int noMaisProx = getMaisProximo(custo, naoVisitados);
            
            naoVisitados.remove(noMaisProx);
            
            for ( Integer vizinhos : getVizinhos(noMaisProx) ) {
                int custoTotal = custo[noMaisProx] + getCusto(noMaisProx, vizinhos);

                if (custoTotal < custo[vizinhos]) {
                    custo[vizinhos] = custoTotal;
                    auxiliar[vizinhos] = noMaisProx;
                }
            }
            if (noMaisProx == noDestino) {
                return caminhoMaisProx(auxiliar, noMaisProx);
            }
        }
        return new ArrayList<>();
    }

    public List<Integer> caminhoMaisProx (Integer antecessor[], Integer noMaisProx) {
        List<Integer> caminho = new ArrayList<Integer>();
        caminho.add(noMaisProx);

        while (antecessor[noMaisProx] != -1) {
            caminho.add(antecessor[noMaisProx]);
            noMaisProx = antecessor[noMaisProx];
        }

        Collections.reverse(caminho);
        return caminho;
    }
}