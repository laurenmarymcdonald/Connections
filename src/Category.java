import java.util.ArrayList;
public class Category {
    private String categoryName;
    private String[] words;
    private String color;
    private boolean guessed;
    public Category (String categoryName, String[] words, String color){
        this.categoryName = categoryName;
        this.words = words;
        this.color = color;
        guessed = false;
    }
    public String getCategoryName(){
        return categoryName;
    }
    public String[] getWords(){
        return words;
    }
    public String getColor(){
        return color;
    }
    public boolean getGuessed(){
        return guessed;
    }
    public void setGuessed(boolean guessed){
        this.guessed = guessed;
    }
}

