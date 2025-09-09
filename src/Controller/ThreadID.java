package Controller;

public class ThreadID {
	public static void main(String[] args) {
        // Criando 5 threads
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(() -> {
                // Obtendo o ID da thread
                long id = Thread.currentThread().getId();
                System.out.println("Thread rodando com ID: " + id);
            });

            t.start(); // Inicia a thread
        }
    }
}
