import java.util.Scanner;
public class Application {
    private final User[] userList;
    private final User[] winnersList;
    private final Card[] cardList;
    private final int[] drawnNumbers;
    private int salesAmount;
    private int totalSales;

    public Application() {
        userList = new User[20];
        winnersList = new User[20];
        cardList = new Card[300];
        drawnNumbers = new int[60];
    }

    public void insertNameCards() {
        Scanner userInput = new Scanner(System.in);
        for (int i = 0; i < 20; i++) {
            System.out.println("Insert the name of the user " + (i + 1) + ":");
            String name = userInput.nextLine();

            System.out.println("Insert the amount of cards of the user " + (i + 1) + ":");
            int cardAmount = userInput.nextInt();
            userInput.nextLine();

            userList[i] = new User(name, cardAmount);
        }
    }

    public void createCards() {
        for (User user : userList) {
            if (user != null) {
                int cardsToAdd = user.getCardArray().length;
                salesAmount += cardsToAdd;
                addCardsToUser(user, cardsToAdd);
            }
        }
        totalSales = salesAmount * 10;
    }

    public void draw() {
        for (int i = 0; i < 25; i++) {
            int drawnNumber;
            do {
                drawnNumber = (int) (Math.random() * 60 + 1);
            } while (repeatedNumber(drawnNumber));

            insertDrawnArray(drawnNumber);
            searchSets(drawnNumber);

            for (User user : userList) {
                if (user != null && isUserWinner(user)) {
                    return;
                }
            }
        }
    }

    public Card returnCard() {
        Card returnCard = new Card(10);
        for (int i = 0; i < cardList.length; i++) {
            if (cardList[i] != null) {
                returnCard = cardList[i];
                cardList[i] = null;
                return returnCard;
            }
        }
        return returnCard;
    }

    private void addCardsToUser(User user, int numCards) {
        for (int i = 0; i < numCards; i++) {
            user.insertCard(returnCard());
        }
    }

    private boolean isUserWinner(User user) {
        for (Card card : user.getCardArray()) {
            if (card.getHitCounter1() == 25 || card.getHitCounter2() == 25) {
                insertWinner(user);
                return true;
            }
        }
        return false;
    }

    public void printInformation() {
        printDraw();
        printWinners();
        printSales();
    }

    public void printDraw() {
        System.out.println("The drawn numbers are:");
        for (int drawnNumber : drawnNumbers) {
            System.out.print(drawnNumber + " ");
        }
        System.out.println();
    }

    public void printWinners() {
        System.out.println("The winners are:");
        for (User user : winnersList) {
            if (user != null) {
                System.out.println(user.getName());
            }
        }
    }

    public void printSales() {
        System.out.println("Amount of cards sold: " + salesAmount);
        System.out.println("Total sales: " + totalSales);
        System.out.println("Total prize: " + totalSales * 0.8);
        System.out.println("Profit: " + totalSales * 0.2);
    }

    private boolean repeatedNumber(int num) {
        for (int drawnNumber : drawnNumbers) {
            if (drawnNumber == num) {
                return true;
            }
        }
        return false;
    }

    public void insertDrawnArray(int num) {
        for (int i = 0; i < drawnNumbers.length; i++) {
            if (drawnNumbers[i] == 0) {
                drawnNumbers[i] = num;
                break;
            }
        }
    }

    public void searchSets(int num) {
        for (User user : userList) {
            if (user != null) {
                for (Card card : user.getCardArray()) {
                    if (card.isDrawn(num, card.getTable1())) {
                        card.setHitCounter1(card.getHitCounter1() + 1);
                    }
                    if (card.isDrawn(num, card.getTable2())) {
                        card.setHitCounter2(card.getHitCounter2() + 1);
                    }
                }
            }
        }
    }

    public void insertWinner(User user) {
        for (int i = 0; i < winnersList.length; i++) {
            if (winnersList[i] == null) {
                winnersList[i] = user;
                break;
            }
        }
    }
}