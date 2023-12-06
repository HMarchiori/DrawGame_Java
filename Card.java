import java.util.Arrays;

public class Card {
    public int saleValue;
    public int[] table1;
    public int[] table2;
    public int hitCounter1;
    public int hitCounter2;

    public int getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(int saleValue) {
        this.saleValue = saleValue;
    }

    public int[] getTable1() {
        return table1;
    }

    public void setTable1(int[] table1) {
        this.table1 = table1;
    }

    public int[] getTable2() {
        return table2;
    }

    public void setTable2(int[] table2) {
        this.table2 = table2;
    }

    public int getHitCounter1() {
        return hitCounter1;
    }

    public void setHitCounter1(int hitCounter1) {
        this.hitCounter1 = hitCounter1;
    }

    public int getHitCounter2() {
        return hitCounter2;
    }

    public void setHitCounter2(int hitCounter2) {
        this.hitCounter2 = hitCounter2;
    }

    public Card(int saleValue) {
        this.saleValue = saleValue;
        this.table1 = drawNumbers();
        this.table2 = drawNumbers();
        this.hitCounter1 = 0;
        this.hitCounter2 = 0;
    }

    private int[] drawNumbers() {
        int[] table = new int[25];
        for (int i = 0; i < table.length; i++) {
            int randomValue = (int) ((Math.random() * 60) + 1);
            for (int j = 0; j < i; j++) {
                if (table[j] == randomValue) {
                    randomValue = (int) ((Math.random() * 60) + 1);
                    j = -1;
                }
            }
            table[i] = randomValue;
        }
        return table;
    }

    public boolean isDrawn(int num, int[] table) {
        for (int element : table) {
            if (element == num) {
                return true;
            }
        }
        return false;
    }

    public void HitCounter(int num, int[] table, int hitCounter) {
        if (isDrawn(num, table)) {
            hitCounter++;
        }
    }

    public void increaseHitCounter1(int num) {
        HitCounter(num, table1, hitCounter1);
    }

    public void increaseHitCounter2(int num) {
        HitCounter(num, table2, hitCounter2);
    }

    @Override
    public String toString() {
        return "Card{" +
                "saleValue=" + saleValue +
                ", table1=" + Arrays.toString(table1) +
                ", table2=" + Arrays.toString(table2) +
                ", hitCounter1=" + hitCounter1 +
                ", hitCounter2=" + hitCounter2 +
                '}';
    }
}