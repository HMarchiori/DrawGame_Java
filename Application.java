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

    // Métodos relacionados à inicialização do jogo
    public void insertNameCards() {
        String[] sortedNames = {"João", "Maria", "José", "Ana", "Francisco", "Antônia", "Carlos", "Adriana", "Paulo", "Luiza", "Pedro", "Fernanda", "Lucas", "Mariana", "Luiz", "Patrícia", "Mateus", "Aline", "Marcos", "Camila"};
        String[] letterNames = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
        for (int i = 0; i < 20; i++) {
            int numCards = (int) (Math.random() * 15 + 1);
            String name = sortedNames[(int) (Math.random() * 20)] + " " + letterNames[(int) (Math.random() * 12)];
            userList[i] = new User(name, numCards);
            System.out.println("Name: " + name);
            System.out.println("Number of cards: " + numCards);
            System.out.println("-----");
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

    // Métodos auxiliares
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
                        card.increaseHitCounter1();
                    }
                    if (card.isDrawn(num, card.getTable2())) {
                        card.increaseHitCounter2();
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


    // Métodos relacionados ao sorteio e verificação de vencedores
    public void draw() {
        int drawnNumberCounter = 0;

        do {
            int drawnNumber;

            do {
                drawnNumber = (int) (Math.random() * 60 + 1);
            } while (repeatedNumber(drawnNumber));

            insertDrawnArray(drawnNumber);
            searchSets(drawnNumber);
            drawnNumberCounter++;

        } while (drawnNumberCounter < 60 && !hasWinner());
        System.out.println("Drawn numbers: " + drawnNumberCounter);
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

    private boolean hasWinner() {
        for (User user : userList) {
            if (user != null) {
                for (Card card : user.getCardArray()) {
                    if (card.getHitCounter1() == 25 || card.getHitCounter2() == 25) {
                        insertWinner(user);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Métodos relacionados à impressão de informações
    public void printInformation() {
        printDraw();
        printWinners();
        printSales();
    }

    public void printDraw() {
        System.out.println("The drawn numbers are:\n");
        int numbersPrinted = 0;

        for (int drawnNumber : drawnNumbers) {
            if (drawnNumber != 0) System.out.print(drawnNumber + " ");

            numbersPrinted++;

            if (numbersPrinted % 5 == 0) {
                System.out.println(); // Quebra de linha a cada 5 números
            }

            if (numbersPrinted % 25 == 0) {
                System.out.println(); // Quebra de linha após a tabela 1
            }
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
}
