//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet{
    public static Main app;

    private ArrayList<Category> categories;
    private String[] yellowWords;
    private String[] greenWords;
    //private String[] blueWords;
    //private String[] purpleWords;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public Main() {
        app = this;
        yellowWords = new String[]{"Tie Ceremony", "FDD", "Senior Mom Dance","Arillaga Speaker"};
        greenWords = new String[]{"Cut shirts", "Sweats", "Ugg slippers","Jeans"};
    }

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        categories.add(new Category("Traditions", yellowWords, "Yellow", 1));
        categories.add(new Category("New Banned Uniform Items", greenWords, "Green", 2));
        //(int)(Math.random())
    }

    public void draw() {
        background(255);
        fill(0);
        textSize(50);
        text("Connections", 270,100);
        textSize(25);
    }
    public void resorting() {

    }
}