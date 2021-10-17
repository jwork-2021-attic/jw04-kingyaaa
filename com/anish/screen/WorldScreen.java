package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.monsters.BubbleSorter;
import com.anish.monsters.Monster;
import com.anish.monsters.GetColor;
import com.anish.monsters.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Monster[][] monsters;
    String[] sortSteps;
    GetColor getColor;

    public WorldScreen() {
        world = new World();

        int length = 8;
        monsters = new Monster[length][length];
        
        getColor = new GetColor(length,length * length);

        getColor.getRgbColor();//从c256.png中读取RGB

        for(int i = 0;i < length;i++){
            for(int j = 0;j < length;j++){
                monsters[i][j] = new Monster(getColor.getRandomColor(), getColor.getMyRandom(), world);
                world.put(monsters[i][j],10 + i * 2,10 + j * 2);
            }
        }
        /*
        bros[3] = new Calabash(new Color(204, 0, 0), 1, world);
        bros[5] = new Calabash(new Color(255, 165, 0), 2, world);
        bros[1] = new Calabash(new Color(252, 233, 79), 3, world);
        bros[0] = new Calabash(new Color(78, 154, 6), 4, world);
        bros[4] = new Calabash(new Color(50, 175, 255), 5, world);
        bros[6] = new Calabash(new Color(114, 159, 207), 6, world);
        bros[2] = new Calabash(new Color(173, 127, 168), 7, world);

        world.put(bros[0], 10, 10);
        world.put(bros[1], 12, 10);
        world.put(bros[2], 14, 10);
        world.put(bros[3], 16, 10);
        world.put(bros[4], 18, 10);
        world.put(bros[5], 20, 10);
        world.put(bros[6], 22, 10);
        */


        BubbleSorter<Monster> b = new BubbleSorter<>();
        b.load(monsters);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Monster[][] monsters, String step) {
        //System.out.println(step);
        String[] couple = step.split("<->");
        getBroByRank(monsters, Integer.parseInt(couple[0])).swap(getBroByRank(monsters, Integer.parseInt(couple[1])));
    }

    private Monster getBroByRank(Monster[][] monsters, int rank) {
        for(int i = 0;i < monsters.length;i++){
            for(int j = 0;j < monsters[0].length;j++){
                if(monsters[i][j].getRank() == rank){
                    return monsters[i][j];
                }
            }
        }
        return null;
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

        if (i < this.sortSteps.length) {
            this.execute(monsters, sortSteps[i]);
            i++;
        }

        return this;
    }

}
