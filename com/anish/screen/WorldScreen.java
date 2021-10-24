package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;


import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.Destination;
import com.anish.calabashbros.DfsConductor;
import com.anish.calabashbros.World;
import com.anish.calabashbros.FootUp;
import com.anish.calabashbros.FootDown;
import com.anish.calabashbros.FootRight;
import com.anish.calabashbros.FootLeft;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Calabash bro;
    private String[] conductSteps;
    private DfsConductor dfsConductor;
    public WorldScreen() {
        world = new World();
        dfsConductor = new DfsConductor();
        dfsConductor.loadMaze(world.getTiles());
        dfsConductor.dfs(0,0);
        bro = new Calabash(new Color(204, 0, 0), 1, world);
        world.put(bro, 0, 0);
        world.put(new Destination(world),29,29);
        conductSteps = dfsConductor.parseSteps();

    }

    private void execute(Calabash bro, String step) {
        
        //Floor floor = new Floor(this.world);
        //this.world.put(floor,bro.getX(),bro.getY());

        String[] couple = step.split(",");
        String dir = bro.getDirection(Integer.parseInt(couple[1]),Integer.parseInt(couple[0]));

        if(dir == "UP"){
            FootUp footUp = new FootUp((this.world));
            this.world.put(footUp,bro.getX(),bro.getY());
        }
        else if(dir == "DOWN"){
            FootDown footDown = new FootDown((this.world));
            this.world.put(footDown,bro.getX(),bro.getY());
        }
        else if(dir == "RIGHT"){
            FootRight footRight = new FootRight((this.world));
            this.world.put(footRight,bro.getX(),bro.getY());
        }
        else{
            FootLeft footLeft = new FootLeft((this.world));
            this.world.put(footLeft,bro.getX(),bro.getY());
        }

        bro.moveTo(Integer.parseInt(couple[1]), Integer.parseInt(couple[0]));        
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        
        if (i < this.conductSteps.length) {
            this.execute(bro, conductSteps[i]);
            i++;
        }
        
        return this;
    }
}
