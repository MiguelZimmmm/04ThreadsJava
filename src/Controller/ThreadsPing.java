package Controller;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ThreadsPing {

    public static void main(String[] args) {
        Thread uolThread = new Thread(new PingTask("UOL", "www.uol.com.br"));
        Thread terraThread = new Thread(new PingTask("Terra", "www.terra.com.br"));
        Thread googleThread = new Thread(new PingTask("Google", "www.google.com.br"));

        uolThread.start();
        terraThread.start();
        googleThread.start();
    }

    static class PingTask implements Runnable {
        private final String nomeServidor;
        private final String endereco;

        public PingTask(String nomeServidor, String endereco) {
            this.nomeServidor = nomeServidor;
            this.endereco = endereco;
        }

        @Override
        public void run() {
            try {
                ProcessBuilder pb = new ProcessBuilder("ping", "-4", "-c", "10", endereco);
                Process process = pb.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String linha;
                double somaTempos = 0;
                int contagem = 0;

                while ((linha = reader.readLine()) != null) {
                    if (linha.contains("time=")) {
                        // Ex: "64 bytes from ...: icmp_seq=1 ttl=54 time=10.2 ms"
                        int indiceTime = linha.indexOf("time=");
                        int indiceMs = linha.indexOf(" ms");

                        if (indiceTime != -1 && indiceMs != -1) {
                            String tempoStr = linha.substring(indiceTime + 5, indiceMs).trim();
                            double tempo = Double.parseDouble(tempoStr);
                            somaTempos += tempo;
                            contagem++;

                            System.out.printf("[%s] Iteração %d: Tempo = %.2f ms\n", nomeServidor, contagem, tempo);
                        }
                    }
                }

                double media = contagem > 0 ? somaTempos / contagem : 0;
                System.out.printf("✅ [%s] Tempo médio após 10 pings: %.2f ms\n\n", nomeServidor, media);

            } catch (Exception e) {
                System.out.printf("Erro ao pingar o servidor %s (%s): %s\n", nomeServidor, endereco, e.getMessage());
            }
        }
    }
}
