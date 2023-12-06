public class User {
    public  String name;
    public Card[] cardArray;
    public double prizeValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card[] getCardArray() {
        return cardArray;
    }

    public void setCardArray(Card[] cardArray) {
        this.cardArray = cardArray;
    }

    public double getPrizeValue() {
        return prizeValue;
    }

    public void setPrizeValue(double prizeValue) {
        this.prizeValue = prizeValue;
    }

    public User(String name, int cardAmount) {
        cardArray = new Card[cardAmount];
        this.name = name;
        prizeValue = 0;
    }

    public boolean insertCard(Card card) {
        for (int i = 0; i < cardArray.length; i++) {
            if (cardArray[i] == null) {
                cardArray[i] = card;
                return true;
            }
        }
        return false;
    }
}
