//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet{
    public static Main app;
    public String[][] grid;
    public ArrayList<String> selectedWords;
    private ArrayList<Category> categories;
    private ArrayList<String> categoryWords;
    private String[] yellowWords;
    private String[] greenWords;
    private String[] blueWords;
    private String[] purpleWords;
    private boolean startScreen;
    private int mistakesRemaining;
    private final int NUM_PANELS_HORIZONTAL = 4;
    private final int NUMS_PANELS_VERTICAL = 4;
    private ArrayList<Panel> panels;
    private String categoryFound;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public Main() {
        categories = new ArrayList<Category>();
        categoryWords = new ArrayList<String>();
        selectedWords = new ArrayList<String>();
        grid = new String[4][4];
        startScreen = true;
        app = this;
        yellowWords = new String[]{"Tie Ceremony", "FDD", "Senior Mom Dance","Arillaga Speaker"};
        greenWords = new String[]{"Cut shirts", "Sweats", "Ugg slippers","Jeans"};
        blueWords = new String[] {"Emo", "Zest", "Lemon", "Moon"};
        purpleWords = new String[] {"Free Dress", "Off Campus", "College Sweatshirts", "Red Clothing"};
        mistakesRemaining = 4;
        categoryFound = "";
    }

    public void settings() {
        size(1200, 750);
    }

    public void setup() {
        Panel p;
        panels = new ArrayList<Panel>();
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
        categories.add(new Category("Traditions", yellowWords, "Yellow"));
        categories.add(new Category("New Banned Uniform Items", greenWords, "Green"));
        categories.add(new Category("Traditions", blueWords, "Blue"));
        categories.add(new Category("New Banned Uniform Items", purpleWords, "Purple"));
        setCategoryWords();
        shuffle();
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
            background(255);
            fill(0);
            textSize(50);
            text("Connections", 470,100);
            textSize(25);
            for (Panel panel : panels) {
                panel.display();
            }
            text("Mistakes Remaining: " + mistakesRemaining, 470,740);
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
        reset();
    }

    public void keyPressed() {
        if(key == ' ') {
            if(startScreen) {
                startScreen = false;
            }
            else {
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
        }
        if(key == 'm') {
            System.out.println(selectedWords.size());
        }
        if(key == ENTER) {
            if(selectedWords.size() == 4) {
                categoryFound = foundCategory();
                removeCategory(categoryFound);
                setCategoryWords();
                draw();
                if(foundCategory().equals("W") || foundCategory().equals("1")) {
                    mistakesRemaining--;
                    reset();
                }
            }
            else {
                System.out.println("not enough words selected");
            }
        }
    }

    public void setCategoryWords() {
        for(int i = 0; i < categories.size(); i++) {
            for(int j =0; j < categories.get(i).getWords().length; j++) {
                categoryWords.add(categories.get(i).getWords()[j]);
            }
        }
    }
    public void removeCategory(String c) {
        if(c == "Y") {
            for(String str: yellowWords) {
                categoryWords.remove(categoryWords.indexOf(str));
            }
            for(int i = 0; i < categories.size();i++) {
                if(categories.get(i).getCategoryName().equals("Yellow")) {
                    categories.remove(i);
                    return;
                }
            }
        }
        else if(c == "G") {
            for(String str: greenWords) {
                categoryWords.remove(categoryWords.indexOf(str));
            }
            for(int i = 0; i < categories.size();i++) {
                if(categories.get(i).getCategoryName().equals("Green")) {
                    categories.remove(i);
                    return;
                }
            }
        }
        else if(c == "B") {
            for(String str: blueWords) {
                categoryWords.remove(categoryWords.indexOf(str));
            }
            for(int i = 0; i < categories.size();i++) {
                if(categories.get(i).getCategoryName().equals("Blue")) {
                    categories.remove(i);
                    return;
                }
            }
        }
        else if(c == "P") {
            for(String str: purpleWords) {
                categoryWords.remove(categoryWords.indexOf(str));
            }
            for(int i = 0; i < categories.size();i++) {
                if(categories.get(i).getCategoryName().equals("Purple")) {
                    categories.remove(i);
                    return;
                }
            }
        }
    }

    public String foundCategory() {
        int count = 0;
        for (String string : selectedWords) {
            for (String str : yellowWords) {
                if (string.equals(str)) {
                    count++;
                }
            }
        }
        if (count == 4) {
            return "Y"; //returns yellow category
        } else if (count == 3) {
            return "1";
        }
        count = 0;
        for (String s : selectedWords) {
            for (String str : greenWords) {
                if (s.equals(str)) {
                    count++;
                }
            }
        }
        if (count == 4) {
            return "G"; //returns green category
        } else if (count == 3) {
            return "1";
        }
        count = 0;
        for (String word : selectedWords) {
            for (String str : blueWords) {
                if (word.equals(str)) {
                    count++;
                }
            }
        }
        if (count == 4) {
            return "B"; //returns blue category
        } else if (count == 3) {
            return "1";
        }
        count = 0;
        for (String selectedWord : selectedWords) {
            for (String str : purpleWords) {
                if (selectedWord.equals(str)) {
                    count++;
                }
            }
        }
        if (count == 4) {
            return "P"; //returns purple category
        } else if (count == 3) {
            return "1";
        }
        return "W";
    }

    public void mouseClicked() {
        for (Panel panel : panels) {
            panel.handleMouseClicked(mouseX, mouseY);
        }
    }

    public void removeSelectedWord(int index) {
        selectedWords.remove(index);
    }

    public void reset() {
        for(Panel p: panels) {
            p.setDefaultColor(true);
        }
        selectedWords.clear();
        draw();
    }
}
//x values are 140,380,620,860
//y values are 180, 307, 434, 561