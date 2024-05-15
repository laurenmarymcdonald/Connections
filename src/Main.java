//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet{
    public static Main app;
    public String[][] grid;
    private ArrayList<String> selectedWords;
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
        selectedWords = new ArrayList<String>();
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
        int w = width/NUM_PANELS_HORIZONTAL-60;
        int h = height/NUMS_PANELS_VERTICAL-60;//subtract makes panels closer together
        for (int i = 0; i <NUMS_PANELS_VERTICAL; i++) {
            for (int j = 0; j < NUM_PANELS_HORIZONTAL; j++) {
                int x = j*w+140;
                int y = i*h+180;
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

    public void draw() {
        if(startScreen) {
            background(255);
            fill(0);
            textSize(50);
            text("Connections", 470,100);
            textSize(25);
        }
        else {
            for (Panel panel : panels) {
                panel.display();
            }
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
            text("Connections", 470,100);
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
        for (Panel panel : panels) {
            panel.handleMouseClicked(mouseX, mouseY);
            if(selectedWords.size() <= 4) {
                selectedWords.add(panel.getWord());
            }
            System.out.println(selectedWords.size());
        }
    }
}
//x values are 140,380,620,860
//y values are 180, 307, 434, 561