import processing.core.PApplet;
import java.util.ArrayList;
import processing.core.PImage;

public class Main extends PApplet{
    public static Main app;
    public ArrayList<String> selectedWords;
    private ArrayList<Category> categories;
    private ArrayList<String> categoryWords;
    private ArrayList<String> categoriesShown;
    private String[] yellowWords;
    private String[] greenWords;
    private String[] blueWords;
    private String[] purpleWords;
    private boolean startScreen;
    private int mistakesRemaining;
    private int NUM_PANELS_HORIZONTAL = 4;
    private int NUMS_PANELS_VERTICAL = 4;
    private ArrayList<Panel> panels;
    private String categoryFound;
    private int place;
    private int x2;
    private int y2;
    private int track;
    private PImage img;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public Main() {
        categories = new ArrayList<Category>();
        categoryWords = new ArrayList<String>();
        selectedWords = new ArrayList<String>();
        categoriesShown = new ArrayList<String>();
        startScreen = true;
        app = this;
        yellowWords = new String[]{"Tie Ceremony", "FDD", "Senior Mom Dance","Arillaga Speaker"};
        greenWords = new String[]{"Cut shirts", "Sweats", "Ugg slippers","Jeans"};
        blueWords = new String[] {"Yellow", "Orange", "Green", "Blue"};
        purpleWords = new String[] {"Free Dress", "Off Campus", "College Sweatshirts", "Red"};
        mistakesRemaining = 4;
        categoryFound = "";
        place = 0;
        x2 = 200;
        y2 = 180;
        track = 0;
    }

    public void settings() {
        size(1200, 750);
    }

    public void setup() {
        createPanels();
        categories.add(new Category("Traditions", yellowWords, "Yellow"));
        categories.add(new Category("New Banned Uniform Items", greenWords, "Green"));
        categories.add(new Category("Class Colors", blueWords, "Blue"));
        categories.add(new Category("Senior Privileges", purpleWords, "Purple"));
        img = loadImage("image/ENTER.png");
        setCategoryWords();
        shuffle();
    }
    public void createPanels() {
        //if (foundCategory().equals("Y")||foundCategory().equals("B")||foundCategory().equals("G")||foundCategory().equals("P")){
        Panel p;
        panels = new ArrayList<Panel>();
        int w = width/NUM_PANELS_HORIZONTAL-60;
        int h = height/NUMS_PANELS_VERTICAL-60;//subtract makes panels closer together
        height -= 160;
        if(NUMS_PANELS_VERTICAL > 0) {
            for (int i = 0; i < NUMS_PANELS_VERTICAL; i++) {
                for (int j = 0; j < NUM_PANELS_HORIZONTAL; j++) {
                    int x = j * w + 140;
                    int y = i * h + 180 + place;
                    p = new Panel(x, y, w, h, "");
                    panels.add(p);
                }
            }
            place += 120;
        }
        /*} else {
            Panel p;
            panels = new ArrayList<Panel>();
            int w = width/NUM_PANELS_HORIZONTAL-60;
            int h = height/NUMS_PANELS_VERTICAL-60;//subtract makes panels closer together
            for (int i = 0; i <NUMS_PANELS_VERTICAL; i++) {
                for (int j = 0; j < NUM_PANELS_HORIZONTAL; j++) {
                    int x = j*w+140;
                    int y = i*h+180 + place;
                    p = new Panel(x, y, w, h,"");
                    panels.add(p);
                }
            }
        }*/
    }
    public void draw() {
        if(startScreen) {
            image(img,0,0);
        }
        else if(mistakesRemaining == 0 || categories.size() == 1) {
            background(255);
            fill(0);
            textSize(50);
            text("Connections", 470,100);
            fill(249, 223, 109);
            stroke(249, 223, 109);
            rect(200,180,800,100);
            fill(160, 195, 90);
            stroke(160, 195, 90);
            rect(200,307,800,100);
            fill(176, 196, 239);
            stroke(176, 196, 239);
            rect(200,434,800,100);
            fill(187, 129, 197);
            stroke(187, 129, 197);
            rect(200,561,800,100);
            fill(0);
            textSize(35);
            text("Traditions",515,220);
            text("New Banned Uniform Items",400,347);
            text("Class Colors",500,474);
            text("Senior Privileges",480,601);
            textSize(20);
            text("Tie Ceremony, FDD, Senior Mom Dance, Arillaga Speaker",380,260);
            text("Cut shirts, Sweats, Ugg slippers,Jeans",430,387);
            text("Yellow, Orange, Green, Blue",480,514);
            text("Free Dress, Off Campus, College Sweatshirts, Red",385,641);
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
            categoriesShown.add(categoryFound);
            for (String s : categoriesShown) {
                showCategory(s);
            }
            textSize(30);
            text("Mistakes Remaining: " + mistakesRemaining, 470,740);
        }
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
                System.out.println(categoryWords);
                categoryFound = foundCategory();
                removeCategory(categoryFound);
                //setCategoryWords();
                System.out.println(categoryWords);
                NUMS_PANELS_VERTICAL--;
                if(foundCategory().equals("W") || foundCategory().equals("1")) {
                    NUMS_PANELS_VERTICAL++;
                    mistakesRemaining--;
                    reset();
                }
                else{
                    track++;
                    draw();
                    createPanels();
                    shuffle();
                }
            }
            else {
                System.out.println("not enough words selected");
                System.out.println(selectedWords.size());
            }
        }
        if(key == 'v') {
            mistakesRemaining = 0;
        }
    }

    public void setCategoryWords() {
        for(int i = 0; i < categories.size(); i++) {
            for(int j =0; j < categories.get(i).getWords().length; j++) {
                categoryWords.add(categories.get(i).getWords()[j]);
            }
        }
    }
    public void showCategory(String c) {
        if (c.equals("Y")) {
            if (track == 1){
                fill(249, 223, 109);
                stroke(249, 223, 109);
                rect(200,180,800,100);
                fill(0);
                textSize(35);
                text("Traditions",515,220);
                textSize(20);
                text("Tie Ceremony, FDD, Senior Mom Dance, Arillaga Speaker",380,260);

            } else if (track == 2){
                fill(249, 223, 109);
                stroke(249, 223, 109);
                rect(200,307,800,100);
                fill(0);
                textSize(35);
                text("Traditions",515,347);
                textSize(20);
                text("Tie Ceremony, FDD, Senior Mom Dance, Arillaga Speaker",380,387);
            } else if (track == 3){
                fill(249, 223, 109);
                stroke(249, 223, 109);
                rect(200,434,800,100);
                fill(0);
                textSize(35);
                text("Traditions",515,474);
                textSize(20);
                text("Tie Ceremony, FDD, Senior Mom Dance, Arillaga Speaker",380,514);
            }
        } else if(c.equals("G")) {
            if (track == 1){
                fill(160, 195, 90);
                stroke(160, 195, 90);
                rect(200,180,800,100);
                fill(0);
                textSize(35);
                text("New Banned Uniform Items",400,220);
                textSize(20);
                text("Cut shirts, Sweats, Ugg slippers,Jeans",430,260);
            } else if (track == 2){
                fill(160, 195, 90);
                stroke(160, 195, 90);
                rect(200,307,800,100);
                fill(0);
                textSize(35);
                text("New Banned Uniform Items",400,347);
                textSize(20);
                text("Cut shirts, Sweats, Ugg slippers,Jeans",430,387);
            } else if (track == 3){
                fill(160, 195, 90);
                stroke(160, 195, 90);
                rect(200,434,800,100);
                fill(0);
                textSize(35);
                text("New Banned Uniform Items",400,474);
                textSize(20);
                text("Cut shirts, Sweats, Ugg slippers,Jeans",430,514);
            }
        } else if(c.equals("B")) {
            if (track == 1){
                fill(176, 196, 239);
                stroke(176, 196, 239);
                rect(200,180,800,100);
                fill(0);
                textSize(35);
                text("Class Colors",500,220);
                textSize(20);
                text("Yellow, Orange, Green, Blue",480,260);
            } else if (track == 2){
                fill(176, 196, 239);
                stroke(176, 196, 239);
                rect(200,307,800,100);
                fill(0);
                textSize(35);
                text("Class Colors",500,347);
                textSize(20);
                text("Yellow, Orange, Green, Blue",480,387);
            } else if (track == 3) {
                fill(176, 196, 239);
                stroke(176, 196, 239);
                rect(200, 434, 800, 100);
                fill(0);
                textSize(35);
                text("Class Colors",500,474);
                textSize(20);
                text("Yellow, Orange, Green, Blue",480,514);
            }
        } else if(c.equals("P")){
            if (track == 1){
                fill(187, 129, 197);
                stroke(187, 129, 197);
                rect(x2,y2,800,100);
                fill(0);
                textSize(35);
                text("Senior Privileges",480,220);
                textSize(20);
                text("Free Dress, Off Campus, College Sweatshirts, Red",385,260);
            } else if (track == 2){
                fill(187, 129, 197);
                stroke(187, 129, 197);
                rect(200,307,800,100);
                fill(0);
                textSize(35);
                text("Senior Privileges",480,347);
                textSize(20);
                text("Free Dress, Off Campus, College Sweatshirts, Red",385,387);
            } else if (track == 3) {
                fill(187, 129, 197);
                stroke(187, 129, 197);
                rect(200, 434, 800, 100);
                fill(0);
                textSize(35);
                text("Senior Privileges",480,474);
                textSize(20);
                text("Free Dress, Off Campus, College Sweatshirts, Red",385,514);
            }
        } else if(c.equals("1")) {
            textSize(35);
            text("One away",525,150);
        }
        else if(c.equals("W")) {
            textSize(35);
            text("Wrong answer",500,150);
        }

    }
    public void removeCategory(String c) {
        if(c.equals("Y")) {
            for(String str: yellowWords) {
                categoryWords.remove(str);
            }
            for(int i = 0; i < categories.size();i++) {
                if(categories.get(i).getColor().equals("Yellow")) {
                    categories.remove(i);
                    return;
                }
            }
            NUM_PANELS_HORIZONTAL--;
        }
        else if(c.equals("G")) {
            for(String str: greenWords) {
                categoryWords.remove(str);
            }
            for(int i = 0; i < categories.size();i++) {
                if(categories.get(i).getColor().equals("Green")) {
                    categories.remove(i);
                    return;
                }
            }
            NUM_PANELS_HORIZONTAL--;
        }
        else if(c.equals("B")) {
            for(String str: blueWords) {
                categoryWords.remove(str);
            }
            for(int i = 0; i < categories.size();i++) {
                if(categories.get(i).getColor().equals("Blue")) {
                    categories.remove(i);
                    return;
                }
            }
            NUM_PANELS_HORIZONTAL--;
        }
        else if(c.equals("P")) {
            for(String str: purpleWords) {
                categoryWords.remove(str);
                setCategoryWords();
            }
            for(int i = 0; i < categories.size();i++) {
                if(categories.get(i).getColor().equals("Purple")) {
                    categories.remove(i);
                    return;
                }
            }
            NUM_PANELS_HORIZONTAL--;
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