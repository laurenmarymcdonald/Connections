//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet{
    public static Main app;
    private ArrayList<Category> categories;
    private ArrayList<String> categoryWords;
    private String[][] grid;
    private String[] yellowWords;
    private String[] greenWords;
    private String[] blueWords;
    private String[] purpleWords;
    private boolean startScreen;
    private boolean draw;
    private int x;
    private int y;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public Main() {
        categories = new ArrayList<Category>();
        categoryWords = new ArrayList<String>();
        grid = new String[4][4];
        startScreen = true;
        //draw = true;
        app = this;
        yellowWords = new String[]{"Tie Ceremony", "FDD", "Senior Mom Dance","Arillaga Speaker"};
        greenWords = new String[]{"Cut shirts", "Sweats", "Ugg slippers","Jeans"};
        blueWords = new String[] {"Emo", "Zest", "Lemon", "Moon"};
        purpleWords = new String[] {"Free Dress", "Off Campus", "College Sweatshirts", "Red Clothing"};
    }

    public void settings() {
        size(800, 800);
    }

    public void setup() {
        categories.add(new Category("Traditions", yellowWords, "Yellow", 1));
        categories.add(new Category("New Banned Uniform Items", greenWords, "Green", 2));
        categories.add(new Category("Traditions", blueWords, "Blue", 3));
        categories.add(new Category("New Banned Uniform Items", purpleWords, "Purple", 4));
        setCategoryWords();
        shuffle();
        for(int i = 0; i < grid.length;i++) {
            for(int j = 0; j < grid[0].length;j++) {
                System.out.println(grid[i][j]);
            }
        }
        draw = true;
    }

    public void draw() {
        if(draw) {
            if(startScreen) {
                background(255);
                fill(0);
                textSize(50);
                text("Connections", 270,100);
                textSize(25);
            }
            else{
                background(255);
                fill(0);
                textSize(50);
                text("Connections", 270,100);
                textSize(25);
                for (int i = 0; i < grid.length; i++) {
                    for(int j = 0; j < grid[0].length;j++) {
                        if (i == 0 && j == 0) {
                            x = 125;
                            y = 150;
                        }
                        if(i>0 && j ==0) {
                            x=125;
                            y+=200;
                        }
                        else if(j>0) {
                            x += 150;
                        }


                        fill(0);
                        rect(x, y, 100, 100);
                        textSize(30);
                        fill(255);
                        text(grid[i][j], x + 40, y + 60);
                    }
                }
                draw = false;
            }
        }
    }
    public void resorting() {
        setCategoryWords();
        shuffle();
    }
    public void shuffle(){
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                int word = (int)(Math.random()*categoryWords.size());
                System.out.println(word);
                grid[i][j] = categoryWords.get(word);
                categoryWords.remove(word);
            }
        }
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
        /*if (key == 's') {
            shuffle();
            draw = true;
        }*/ //shuffle method
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
}