public class User {
    public  String name;
    public Card[] cardArray;


    public String getName() {
        return name;
    }

    public Card[] getCardArray() {
        return cardArray;
    }

    public User(String name, int cardAmount) {
        cardArray = new Card[cardAmount];
        this.name = name;
    }

    public void insertCard(Card card) {
        for (int i = 0; i < cardArray.length; i++) {
            if (cardArray[i] == null) {
                cardArray[i] = card;
                return;
            }
        }
    }
}
