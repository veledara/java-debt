package hw.mishanya;

import java.util.Random;

public class Consumer extends Thread {
    final Buffer buffer;
    Random random = new Random();

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    private boolean threadFinish = false;

    private void finish() {
        threadFinish = true;
    }

    @Override
    public void run() {
        while (!threadFinish) {
            synchronized (buffer) {
                if (buffer.data.size() == 0) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        finish();
                    }
                }
                else {
                    int index = random.nextInt(0, buffer.data.size());
                    int number = buffer.data.get(index);
                    buffer.data.remove(index);
                    buffer.notifyAll();
                    System.out.println(number + " was removed.");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        finish();
                    }
                }
            }
        }
    }
}
