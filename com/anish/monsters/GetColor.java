package com.anish.monsters;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.Random;
import java.awt.Color;

public class GetColor{
    class RGB{
        int R;
        int G;
        int B;
    }

    private RGB[] rgb;
    private Random random;
    private int num;//Matrix Size
    private boolean visit[];
    private int vcount;
    private int myRandom;
    private int length;

    public GetColor(int length,int num){
        this.rgb = new RGB[num];
        for(int i = 0;i < this.rgb.length;i++){
            this.rgb[i] = new RGB();
        }
        this.visit = new boolean[num];
        this.random = new Random();
        for(boolean v: visit){
            v = false;
        }
        this.vcount = 0;
        this.num = num;
        this.length = length;
    }
    private void setRGB(int r,int g,int b,int index){
        this.rgb[index].R = r;
        this.rgb[index].G = g;
        this.rgb[index].B = b;
    }
    public int getR(int index){
        return this.rgb[index].R;
    }
    public int getG(int index){
        return this.rgb[index].G;
    }
    public int getB(int index){
        return this.rgb[index].B;
    }
    public int getMyRandom(){
        return this.myRandom;
    }

    public Color getRandomColor(){
        int i = this.random.nextInt(this.num);
        while(this.visit[i] != false && vcount != this.num){
            i = this.random.nextInt(this.num);
        }
        this.visit[i] = true;
        this.vcount += 1;
        this.myRandom = i;
        Color color = new Color(this.getR(i),this.getG(i),this.getB(i));
        return color;
    }

    public void getRgbColor(){
    //public static void main(String args[]){
        int[] rgb = new int[3];
        File file = new File("resources/c256.png");
        BufferedImage bi = null;
        try{
            bi = ImageIO.read(file);
        }
        catch(Exception e){
        }
        int width = bi.getWidth();//572
        int height = bi.getHeight();//428
        int minx = bi.getMinX();//0
        int miny = bi.getMinY();//0
        int unitWidth = (width - minx)/this.length;
        int uintHeight = (height - miny)/this.length;
        int count = 0;
        for(int i = 0;i < this.length;i++){
            for(int j = 0;j < this.length;j++){
                int x = (int)(unitWidth / 2 + unitWidth * j);
                int y = (int)(uintHeight / 2 + uintHeight * i);
                int pixel = bi.getRGB(x,y);
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                setRGB(rgb[0], rgb[1], rgb[2],count++);
            }
        }
    }
}
