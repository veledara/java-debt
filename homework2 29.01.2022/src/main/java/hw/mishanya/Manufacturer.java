package hw.mishanya;

import java.util.Random;

public class Manufacturer extends Thread {
    final Buffer buffer;
    Random random = new Random();

    public Manufacturer(Buffer buffer) {
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
                if (buffer.data.size() == buffer.capacity) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        finish();
                    }
                } else {
                    int number = random.nextInt(1, 10);
                    buffer.data.add(number);
                    buffer.notifyAll();
                    System.out.println(number + " was added.");
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
