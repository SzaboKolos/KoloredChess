package Chess;

public class Pos implements java.io.Serializable {
    /**
     * A koordináta oszlop értéke.
     */
    private short posY;
    /**
     * A koordináta sor értéke.
     */
    private short posX;
    /**
     * Létrehoz egy pozíciót y és x értékekkel.
     *
     * @param y koordináta y értéke.
     * @param x koordináta x értéke.
     */
    public Pos(int y, int x) {
        posY = (short) y;
        posX = (short) x;
    }
    /**
     * Üres pozíció létrehozása.
     */
    public Pos() { }
    //--------------------------------------

    /**
     * A koordináta oszlop értékének lekérdezése.
     * @return Pozíció posY attributuma
     */
    public int getPosY() {
        return posY;
    }

    /**
     * A koordináta sor értékének lekérdezése.
     * @return Pozíció posX attributuma
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Átalakítja a felhasználó által könnyebben olvasható formátumra a pozíciót.
     *
     * @return Visszatérési értéke egy [A3] -re hasonlító koordináta.
     */
    public String toString() {
        String yChar = switch (posY) {
            case 0 -> "A";
            case 1 -> "B";
            case 2 -> "C";
            case 3 -> "D";
            case 4 -> "E";
            case 5 -> "F";
            case 6 -> "G";
            case 7 -> "H";
            default -> "N";
        };
        short xChar = (short) (posX + 1);
        return ("[" + yChar + xChar + "]");
    }

    /**
     * Bemeneti betű+szám kombinációból készít egy a program által olvasható pozíció objektumot.
     *
     * @param yx String érték [sor,oszlop]
     * @return Visszatér egy pozícióval.
     */
    public static Pos stringToPos(String yx) {
        char yChar = yx.toUpperCase().charAt(0);
        int y = -1;
        int x = (Integer.parseInt(String.valueOf(yx.charAt(1))) - 1);
        switch (yChar) {
            case 'A' -> y = 0;
            case 'B' -> y = 1;
            case 'C' -> y = 2;
            case 'D' -> y = 3;
            case 'E' -> y = 4;
            case 'F' -> y = 5;
            case 'G' -> y = 6;
            case 'H' -> y = 7;
            default -> {
            }
        }

        return (new Pos(y, x));
    }

}
