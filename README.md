Connections
A playable clone of the NYT Connections word game, built in Java with the Processing library. The puzzle is themed around Castilleja School traditions.


How to play
Press Space to start from the title screen.
Click four words you think belong together, then press Enter to submit.
If three of your four words are right, the game tells you you're "one away."
You get 4 mistakes before the game ends and shows all the answers.
Press S to shuffle the remaining words.

How it works
Main.java: game loop, input handling, guess checking, and the screens for solved categories and game over
Panel.java: each word tile, which draws itself and toggles when clicked
Category.java: a category's name, its four words, color, and whether it has been solved

Running it
Open the project in IntelliJ IDEA.
Add Processing's core.jar as a library.
Run Main.
