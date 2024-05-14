//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet{
    public static Main app;
    public String[][] grid;
    private ArrayList<Category> categories;
    private ArrayList<String> categoryWords;
    private String[] yellowWords;
    private String[] greenWords;
    private String[] blueWords;
    private String[] purpleWords;
    private boolean startScreen;
    private boolean draw;
    private int x;
    private int y;
    private int mistakesRemaining;
    private final int NUM_PANELS_HORIZONTAL = 4;
    private final int NUMS_PANELS_VERTICAL = 4;
    private ArrayList<Panel> panels;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public Main() {
        categories = new ArrayList<Category>();
        categoryWords = new ArrayList<String>();
        grid = new String[4][4];
        startScreen = true;
        draw = true;
        app = this;
        yellowWords = new String[]{"Tie Ceremony", "FDD", "Senior Mom Dance","Arillaga Speaker"};
        greenWords = new String[]{"Cut shirts", "Sweats", "Ugg slippers","Jeans"};
        blueWords = new String[] {"Emo", "Zest", "Lemon", "Moon"};
        purpleWords = new String[] {"Free Dress", "Off Campus", "College Sweatshirts", "Red Clothing"};
        mistakesRemaining = 4;
    }

    public void settings() {
        size(1200, 750);
    }

    public void setup() {
        Panel p;
        panels = new ArrayList<Panel>();
        //size is NUMS_PANELS_VERTICAL*NUM_PANELS_HORIZONTAL;
        int w = width/NUM_PANELS_HORIZONTAL;
        int h = height/NUMS_PANELS_VERTICAL;
        for (int i = 0; i <NUMS_PANELS_VERTICAL; i++) {
            for (int j = 0; j < NUM_PANELS_HORIZONTAL; j++) {
                int x = j*w;
                int y = i*h;
                p = new Panel(x, y, w, h,"");
                panels.add(p);
            }
        }
        categories.add(new Category("Traditions", yellowWords, "Yellow", 1));
        categories.add(new Category("New Banned Uniform Items", greenWords, "Green", 2));
        categories.add(new Category("Traditions", blueWords, "Blue", 3));
        categories.add(new Category("New Banned Uniform Items", purpleWords, "Purple", 4));
        setCategoryWords();
        shuffle();
        draw = true;
    }

    /*public void draw() {
        if(draw) {
            if(startScreen) {
                background(255);
                fill(0);
                textSize(50);
                text("Connections", 450,100);
                textSize(25);
            }
            else{
                background(255);
                fill(0);
                textSize(50);
                text("Connections", 450,100);
                textSize(25);
                for (int i = 0; i < grid.length; i++) {
                    for(int j = 0; j < grid[0].length;j++) {
                        if (i == 0 && j == 0) {
                            x = 170;
                            y = 170;
                        }
                        if(i>0 && j ==0) {
                            x=170;
                            y+=120;
                        }
                        else if(j>0) {
                            x += 225;
                        }
                        if(mouseX > x && mouseX > 200 && mouseY > y && mouseY < 100) {
                            stroke(240, 236, 228);
                            fill(240, 236, 228);
                        }
                        else {
                            stroke(240, 236, 228);
                            fill(240, 236, 228);
                        }
                        rect(x, y, 200, 100);
                        textSize(30);
                        fill(0);
                        if(grid[i][j].length() <= 6) {
                            text(grid[i][j], x + 60, y + 60);
                        }
                        else {
                            text(grid[i][j], x + 10, y + 60);
                        }
                    }
                }
                fill(0);
                text("Mistakes remaining: " + mistakesRemaining, 475, 725);
                draw = false;
            }
        }
    }*/
    public void draw() {
        if(startScreen) {
            background(255);
            fill(0);
            textSize(50);
            text("Connections", 450,100);
            textSize(25);
        }
        else {
            for (Panel panel : panels) {
                panel.display();
            }
            /*for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[0].length; j++) {
                    fill(0);
                    if(grid[i][j].length() <= 6) {
                        text(grid[i][j], x + 60, y + 60);
                    }
                    else {
                        text(grid[i][j], x + 10, y + 60);
                    }
                }
            }*/
        }
    }
    public void resorting() {
        setCategoryWords();
        shuffle();
    }
    public void shuffle(){
        for (Panel panel : panels){
            int word = (int)(Math.random()*categoryWords.size());
            panel.setWord(categoryWords.get(word));
            categoryWords.remove(word);
        }
        setCategoryWords();
    }
    public void keyPressed() {
        if(key == ENTER) {
            if(startScreen) {
                draw = true;
                startScreen = false;
            }
            else {
                draw = true;
                startScreen = true;
            }
        }
        if (key == 's') {
            background(255);
            fill(0);
            textSize(50);
            text("Connections", 450,100);
            textSize(25);
            shuffle();
            draw = true;
        }
    }
    public void setCategoryWords() {
        for(int i = 0; i < categories.size(); i++) {
            for(int j =0; j < categories.get(i).getWords().length; j++) {
                categoryWords.add(categories.get(i).getWords()[j]);
            }
        }
    }
    public void foundCategory() {
        //put found category at top
        //remove category from arrayList
    }
    public void mouseClicked() {
        handleMouseClicked(mouseX, mouseY);
    }
    public void handleMouseClicked(int mX, int mY) {
        if (mX > x && mX < (x+200) && mY > y && mY < (y+100)){
            System.out.println("Mouse clicked at (" + x + ", " + y + ")");
        }
    }
}