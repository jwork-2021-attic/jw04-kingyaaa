package com.anish.calabashbros;

public interface Conductor {
    public void loadMaze(Tile<Thing>[][] t);
    public void initMatrix();
    public String getSteps();
}
