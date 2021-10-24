package com.anish.calabashbros;
import com.anish.maze_generator.MazeGenerator;

public class World {
    
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    

    private Tile<Thing>[][] tiles;
    private MazeGenerator mazeGenerator;

    public World() {

        
        if (tiles == null) {
            tiles = new Tile[WIDTH][HEIGHT];
        }
        
        mazeGenerator = new MazeGenerator(30);
        mazeGenerator.generateMaze();

        String mazeString = mazeGenerator.getRawMaze();
        String[] mazeRaws = mazeString.split("\n");
        //System.out.println(mazeString);
        for(int i = 0;i < mazeRaws.length;i++){
            String[] mazeColumn = mazeRaws[i].split(" ");
            for(int j = 0;j < mazeColumn.length;j++){
                tiles[j][i] = new Tile<>(j,i);
                String block = mazeColumn[j];
                if(block.equals("0")){
                    tiles[j][i].setThing(new Wall(this));
                }
                else{
                    tiles[j][i].setThing(new Floor(this));
                }
            }
        }
        /*
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile<>(i, j);
                tiles[i][j].setThing(new Floor(this));
            }
        }
        */
    }

    public Thing get(int x, int y) {
        return this.tiles[x][y].getThing();
    }

    public void put(Thing t, int x, int y) {
        this.tiles[x][y].setThing(t);
    }

    public Tile<Thing>[][] getTiles()
    {
        return tiles;
    }
}
