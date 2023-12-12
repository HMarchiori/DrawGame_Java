public class Main {
    public static void main(String[] args) {
        // Creates an instance of Application and calls the method that starts the game
        Application application = new Application();
        // Insert the names of the players and the number of cards they will have
        application.insertNameCards();
        // Creates the cards for each player
        application.createCards();
        // Starts the game
        application.draw();
        // Prints the information of the winners
        application.printInformation();
    }
}
