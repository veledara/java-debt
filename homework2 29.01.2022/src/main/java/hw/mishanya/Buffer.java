package hw.mishanya;

import java.util.ArrayList;

public class Buffer {
    ArrayList<Integer> data;
    int capacity;

    public Buffer(int capacity) {
        data = new ArrayList<>(capacity);
        this.capacity = capacity;
    }
}
