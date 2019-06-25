package com.kp.study.algorithms.old.stacksandqueues;

import java.util.LinkedList;
import java.util.List;

public class GenericQueue<T> {
    List<T> data;

    public GenericQueue() {
        super();
        data = new LinkedList<T>();
    }

    public void add(T item) {
        data.add(item);
    }

    public T remove() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            return data.remove(0);
        }
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }
}
