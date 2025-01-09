package org.edgar.cookies.entity;

import java.io.Serializable;

public class Counter implements Serializable {
    private int count;

    public int getCount() {
        return count;
    }

    public void increment() {
        count++;
    }
}