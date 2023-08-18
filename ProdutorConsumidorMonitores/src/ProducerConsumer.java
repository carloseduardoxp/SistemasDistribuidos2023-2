import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProducerConsumer {

    static final int N = 1000;
    static final int THREADS = 10;
    static Monitor m = new Monitor();
    public static void main(String[] args) throws Exception {
       for (int i = 0; i < THREADS;i++) {
            Thread produtor = new Thread(new Producer(),"Thread produtor"+i);
            Thread consumidor = new Thread(new Consumer(),"Thread consumidor"+i);
            produtor.start();
            consumidor.start();
       }
    }

    static class Producer implements Runnable {
        public void run() {
            int item;
            while (true) {
                item = produceItem();
                m.insertItem(item);
                System.out.println("Inserindo item produtor "+item);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
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
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
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
                System.out.println("a thread mimiu "+Thread.currentThread().getName());
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
