package filosofos;
public class Filosofo implements Runnable {

	//sempre pega o garfo menor primeiro
    private final int id;
    private final Garfo primeiro;
    private final Garfo segundo;

    public Filosofo(int id, Garfo garfo1, Garfo garfo2) {
        this.id = id;
        this.primeiro = garfo1;
        this.segundo = garfo2;
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                comer();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    //Pensar simula o estado ocioso do filosofo, pegando um numero aleatorio
    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando...");
        Thread.sleep((long) (Math.random() * 2000));
    }
    //comer simula o objeto utilizando o recurso(garfo)
    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " está com fome.");

        synchronized (primeiro) {
            System.out.println("Filósofo " + id + " pegou o garfo " + primeiro.getId());

            synchronized (segundo) {
                System.out.println("Filósofo " + id + " pegou o garfo " + segundo.getId());
                System.out.println("Filósofo " + id + " está comendo...");
                Thread.sleep((long) (Math.random() * 2000));
                System.out.println("Filósofo " + id + " colocou os garfos.");
            }
        }
    }
}
