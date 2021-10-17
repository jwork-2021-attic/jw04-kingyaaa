package com.anish.monsters;
import java.util.ArrayList;
import java.util.Collections;

public class BubbleSorter<T extends Comparable<T>> implements MatrixSorter<T> {

    private T[][] a;
    private ArrayList<T> aLine;

    @Override
    public void load(T[][] a) {
        this.a = a;
        this.aLine = new ArrayList<T>(a.length * a[0].length);
        for(int i = 0;i < a.length; i++){
            for(int j = 0;j < a[0].length;j++){
                this.aLine.add(a[i][j]);
            }
        }
    }

    private void swap(int i, int j) {
        Collections.swap(this.aLine,i,j);
        plan += "" + aLine.get(i) + "<->" + aLine.get(j) + "\n";
    }

    private String plan = "";

    @Override
    public void sort() {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < aLine.size() - 1; i++) {
                if (aLine.get(i).compareTo(aLine.get(i+1)) > 0) {
                    swap(i, i + 1);
                    sorted = false;
                }
            }
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}