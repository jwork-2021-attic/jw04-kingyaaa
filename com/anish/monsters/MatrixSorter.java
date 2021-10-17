package com.anish.monsters;

public interface MatrixSorter<T extends Comparable<T>> {
    public void load(T[][] elements);

    public void sort();

    public String getPlan();
}