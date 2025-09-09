package Controller;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSapo {

    // Distância total da corrida (em metros)
    private static final int DISTANCIA_TOTAL = 100;

    // Máximo de metros por salto
    private static final int MAX_SALTO = 10;

    // Controla a colocação (compartilhado entre threads)
    private static AtomicInteger colocacao = new AtomicInteger(1);

    public static void main(String[] args) {
        System.out.println("-- Corrida de Sapos Iniciada! -- ");

        for (int i = 1; i <= 5; i++) {
            Sapo sapo = new Sapo("Sapo " + i);
            Thread thread = new Thread(sapo);
            thread.start();
        }
    }

    static class Sapo implements Runnable {
        private String nome;
        private int distanciaPercorrida = 0;
        private Random random = new Random();

        public Sapo(String nome) {
            this.nome = nome;
        }

        @Override
        public void run() {
            while (distanciaPercorrida < DISTANCIA_TOTAL) {
                int salto = random.nextInt(MAX_SALTO + 1); // de 0 até MAX_SALTO
                distanciaPercorrida += salto;

                if (distanciaPercorrida > DISTANCIA_TOTAL) {
                    distanciaPercorrida = DISTANCIA_TOTAL;
                }

                System.out.printf("%s saltou %d metros. Distância total: %d metros.\n",
                        nome, salto, distanciaPercorrida);

                try {
                    Thread.sleep(200); // pausa para simular tempo entre saltos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int posicao = colocacao.getAndIncrement();

            System.out.printf("-- 🏁 %s chegou em %dº lugar! --\n", nome, posicao);
        }
    }
}
