package Deadlock;

public class DeadlockComLogs {

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
            log("T2 tentando LOCK_B");
            synchronized (LOCK_B) {
                log("T2 adquiriu LOCK_B");
                dormir(100);
                log("T2 tentando LOCK_A");
                synchronized (LOCK_A) {
                    log("T2 adquiriu LOCK_A - concluiu");
                }
            }
        }, "T2");

        t1.start();
        t2.start();

        // Observador opcional
        new Thread(() -> {
            dormir(500);
            if (t1.isAlive() && t2.isAlive()) {
                log("Observador: possível deadlock detectado — ambas as threads ainda vivas após 500ms");
                log("Estado esperado: T1 segurando LOCK_A e esperando LOCK_B;");
                log("                T2 segurando LOCK_B e esperando LOCK_A (espera circular).");
            }
        }, "Observador").start();
    }

    static void dormir(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    static void log(String msg) {
        System.out.printf("[%s] %s%n", Thread.currentThread().getName(), msg);
    }
}
