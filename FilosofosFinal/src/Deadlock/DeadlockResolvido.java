package Deadlock;

public class DeadlockResolvido {

    static final Object LOCK_A = new Object();
    static final Object LOCK_B = new Object();

    public static void iniciar() {

        Thread t1 = new Thread(() -> {
            log("T1 tentando LOCK_A");
            synchronized (LOCK_A) {
                log("T1 adquiriu LOCK_A");
                dormir(100);

                log("T1 tentando LOCK_B");
                synchronized (LOCK_B) {
                    log("T1 adquiriu LOCK_B - concluiu");
                }
            }
        }, "T1");

        Thread t2 = new Thread(() -> {
            // Agora T2 também segue a mesma ordem: A → B
            log("T2 tentando LOCK_A");
            synchronized (LOCK_A) {
                log("T2 adquiriu LOCK_A");
                dormir(100);

                log("T2 tentando LOCK_B");
                synchronized (LOCK_B) {
                    log("T2 adquiriu LOCK_B - concluiu");
                }
            }
        }, "T2");

        t1.start();
        t2.start();
    }

    static void dormir(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    static void log(String msg) {
        System.out.printf("[%s] %s%n", Thread.currentThread().getName(), msg);
    }
}
