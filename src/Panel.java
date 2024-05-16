import processing.core.PImage;

public class Panel {
    private int x, y;
    private int w, h;
    private String word;
    private boolean defaultColor;

    public Panel(int x, int y, int w, int h, String word){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.word = word;
        defaultColor = true;
    }
    public void display(){
        if(defaultColor) {
            Main.app.fill(240, 236, 228);
            Main.app.stroke(240, 236, 228);
        }
        else {
            Main.app.fill(90, 89, 78);
            Main.app.stroke(90, 89, 78);
        }
        Main.app.rect(x, y, 200, 100);
        Main.app.textSize(30);
        Main.app.fill(0);
        if(word.length() <= 6) {
            Main.app.text(word, x + 60, y + 60);
        }
        else {
            Main.app.text(word, x + 10, y + 60);
        }
    }

    public void handleMouseClicked(int mX, int mY){
        if (mX > x && mX < (x + w) && mY > y && mY < (y + h)) {
            System.out.println("Mouse clicked Panel at (" + x + ", " + y + ")");
            if (Main.app.selectedWords.size() < 4 && !Main.app.selectedWords.contains(word)) {
                Main.app.selectedWords.add(word);
                defaultColor = false;
            }
            else if(Main.app.selectedWords.contains(word)) {
                int index = Main.app.selectedWords.indexOf(word);
                Main.app.removeSelectedWord(index);
                defaultColor = true;
            }
            else {
                defaultColor = true;
            }
        }
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return w;
    }

    public int getHeight(){
        return h;
    }

    public void setX(int _x){
        x = _x;
    }

    public void setY(int _y){
        y = _y;
    }
    public String getWord() {
        return word;
    }
    public void setWord(String w) {
        word = w;
    }
}
