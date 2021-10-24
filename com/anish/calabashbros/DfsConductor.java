package com.anish.calabashbros;
import java.awt.Color;
import asciiPanel.AsciiPanel;
public class DfsConductor implements Conductor{
    private Tile<Thing>[][] tiles;
    private int rows;
    private int columns;
    public String path = "";
    public String shortestPath = "";
    private int[][] maze;
    private boolean[][]visit;
    private String steps;
    
    @Override
    public void loadMaze(Tile<Thing>[][] t){
        this.tiles = t;
        initMatrix();
    }
    @Override
    public void initMatrix(){
        this.rows = tiles.length;
        this.columns = tiles[0].length;
        maze = new int[tiles.length][tiles[0].length];
        visit = new boolean[tiles.length][tiles[0].length];
        for(int i = 0;i < tiles.length;i++){
            for(int j = 0;j < tiles[0].length;j++){
                if(this.getColor(i,j) == AsciiPanel.cyan){//Wall
                    maze[j][i] = 1;
                }
                else{//Floor
                    maze[j][i] = 0;
                }
                visit[i][j] = false;
            }
        }
        /*
        System.out.println("maze");
        for(int i = 0;i < this.rows;i++){
            for(int j = 0;j < this.columns;j++){
                System.out.print(maze[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("\n");
        */
    }
    @Override
    public String getSteps(){
        return this.steps;
    }
    public int[][] getMaze(){
        return this.maze;
    }
    public int getRows(){
        return tiles.length;
    }
    public int getColumns(){
        return tiles[0].length;
    }
    public Color getColor(int i,int j){
        return this.tiles[i][j].getThing().getColor();
    }
    public void dfs(int x,int y){
        if(x < 0 || y < 0){
            return;
        }
        if(x > rows - 1 || y > columns - 1 || maze[x][y] == 1){
            return;
        }
        if(visit[x][y] == true){
            return;
        }
        if(x == rows - 1 && y == columns -1){
            path = path + x + "," + y;
            if(shortestPath.length() == 0 || shortestPath.length() > path.length()){
                shortestPath = path;
                this.steps = shortestPath;
            }
            //System.out.println(path);
            return;
        }
        String temp = path;
        path = path + x + "," + y + " ";
        visit[x][y] = true;
        dfs(x + 1,y);
        dfs(x - 1,y);
        dfs(x,y + 1);
        dfs(x,y - 1);
        path = temp;
    }
    public String[] parseSteps(){
        return this.steps.split(" ");
        //for(String s: eachStep){
            //String[] couple = s.split(",");
            
        //}
    } 
}
