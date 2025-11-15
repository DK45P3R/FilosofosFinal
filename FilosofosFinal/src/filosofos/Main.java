package filosofos;
public class Main {

    public static void main(String[] args) {

        final int NUM_FILOSOFOS = 5;

        // array de garfos
        Garfo[] garfos = new Garfo[NUM_FILOSOFOS];
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            garfos[i] = new Garfo(i);
        }

        // cria e inicia os filósofos
        Filosofo[] filosofos = new Filosofo[NUM_FILOSOFOS];
        for (int i = 0; i < NUM_FILOSOFOS; i++) {

            Garfo garfoEsq = garfos[i];
            Garfo garfoDir = garfos[(i + 1) % NUM_FILOSOFOS];

            // garante ordem global pegando sempre o garfo menor primeiro.
            if (garfoEsq.getId() < garfoDir.getId()) {
                filosofos[i] = new Filosofo(i, garfoEsq, garfoDir);
            } else {
                filosofos[i] = new Filosofo(i, garfoDir, garfoEsq);
            }

            Thread t = new Thread(filosofos[i]);
            t.start();
        }
    }
}
