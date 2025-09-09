package Controller;
import java.util.Random;

class ThreadVetor extends Thread {
    private int numero;
    private int[] vetor;

    public ThreadVetor(int numero, int[] vetor) {
        this.numero = numero;
        this.vetor = vetor;
    }

    @Override
    public void run() {
        long inicio = System.nanoTime(); // mais preciso que currentTimeMillis()

        if (numero % 2 == 0) {
            // Se número for par → usa for tradicional
            for (int i = 0; i < vetor.length; i++) {
                int valor = vetor[i];
            }
        } else {
            // Se número for ímpar → usa foreach
            for (int valor : vetor) {
                int temp = valor;
            }
        }

        long fim = System.nanoTime();
        double tempoSegundos = (fim - inicio) / 1_000_000_000.0;

        // Formata para mostrar várias casas decimais
        System.out.printf("Thread com número %d percorreu o vetor em %.9f segundos.%n", 
                          numero, tempoSegundos);
    }
}

public class ThreadsTempo {
    public static void main(String[] args) {
        int[] vetor = new int[1000];
        Random rand = new Random();

        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = rand.nextInt(100) + 1;
        }

        ThreadVetor t1 = new ThreadVetor(1, vetor);
        ThreadVetor t2 = new ThreadVetor(2, vetor);

        t1.start();
        t2.start();
    }
}
