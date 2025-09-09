package Controller;
import java.util.Random;

class SomaLinha extends Thread {
    private int[] linha;
    private int indice;

    public SomaLinha(int[] linha, int indice) {
        this.linha = linha;
        this.indice = indice;
    }

    @Override
    public void run() {
        int soma = 0;
        for (int num : linha) {
            soma += num;
        }
        System.out.println("Linha " + indice + " | Soma = " + soma);
    }
}

public class MatrizThreads {
	public static void main(String[] args) {
        int[][] matriz = new int[3][5];
        Random rand = new Random();

        // Preenche a matriz com números aleatórios de 1 a 9
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j] = rand.nextInt(9) + 1;
            }
        }

        // Mostra a matriz gerada
        System.out.println("Matriz gerada:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }

        // Cria 3 threads, cada uma para uma linha
        for (int i = 0; i < 3; i++) {
            SomaLinha t = new SomaLinha(matriz[i], i);
            t.start();
        }
    }
}
