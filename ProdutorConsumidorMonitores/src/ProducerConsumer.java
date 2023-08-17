import java.util.Random;

public class ProducerConsumer {

    static final int N = 5;
    static Producer p = new Producer();
    static Consumer c = new Consumer();
    static Monitor m = new Monitor();
    public static void main(String[] args) throws Exception {
       Thread produtor = new Thread(p,"Thread produtor");
       Thread consumidor = new Thread(c,"Thread consumidor");
       produtor.start();
       consumidor.start();
    }

    static class Producer implements Runnable {
        public void run() {
            int item;
            while (true) {
                item = produceItem();
                m.insertItem(item);
                System.out.println("Inserindo item produtor "+item);
            }
        }

        private int produceItem() {
            Random r = new Random();
            return r.nextInt(100);
        }
    }

    static class Consumer implements Runnable {
        public void run() {
            int item;
            while (true) {
                item = m.remove();
                consumeItem(item);
            }
        }

        private void consumeItem(int item) {
            System.out.println("Consumindo item "+item);
        }
    }

    static class Monitor {
        private int buffer[] = new int[N];
        private int count = 0;
        private int lo = 0;
        private int hi = 0;

        public synchronized void insertItem(int val) {
            if (count == N) {
                goToSleep();
            }
            buffer[hi] = val;
            hi = (hi + 1) % N;
            count++;
            if (count == 1) {
                notify();
            }
        }

        public synchronized int remove() {
            int val;
            if (count == 0) {
                goToSleep();
            }
            val = buffer[lo];
            lo = (lo + 1) % N;
            count--;
            if (count == N - 1) {
                notify();
            }
            return val;
        }

        private void goToSleep() {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
