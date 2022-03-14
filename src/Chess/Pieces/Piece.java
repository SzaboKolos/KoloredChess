package Chess.Pieces;

import Chess.Pos;

public abstract class Piece implements java.io.Serializable {
    /**
     * Bábu objektum pozíciója.
     */
    public Pos pos;
    /**
     * Bábu objektum színe.
     */
    protected String color;
    /**
     * Bábu objektum neve.
     */
    protected String name;

    /**
     * Létrehoz egy statikus típusként használható bábu objektumot.
     * Nem példányosítható
     *
     * @param y A bábu helyzetének y értéke.
     * @param x A bábu helyzetének x értéke.
     * @param c A bábu színe.
     * @param n A bábu neve.
     */
    public Piece(int y, int x, String c, String n) {
        pos = new Pos(y, x);
        name = n;
        color = c;
    }

    //--------------------------------------

    /**
     * Lépés vagy pozíció-hardset. (pl. bábuletevés létrehozáskor)
     * Paraméterben kapott pozícióra állítja az objektum helyértékét.
     *
     * @param p Pozíció ( oszlop, sor értékekkel)
     */
    public void setPos(Pos p) {
        pos = p;
    }

    /**
     * Lekérdezi a bábu helyzetét.
     *
     * @return Visszatérési értéke a bábu jelenlegi (Pos) pos attributuma.
     */
    public Pos where() {
        return pos;
    }

    /**
     * Lekérdezi a bábu nevét.
     * (Kiválasztáshoz kell.)
     *
     * @return Visszatérési értéke a bábu (String) name attributuma.
     */
    public String getName() {
        return name;
    }

    /**
     * Lekérdezi a bábu színét.
     *
     * @return Visszatérési értéke a bábu (String) color attributuma.
     */
    public String getColor() {
        return color;
    }

    /**
     * A bábu pozícióját paraméterben kapott (Pos) dest pozícióra állítja, ha megfelel a
     * bábu típusához tartozó léptetési szabállyal.
     *
     * @param dest (Pos) erre a pozícióra állítja a bábu helyzetét.
     * @return Igaz értékkel tér vissza, ha sikerült a lépés, hamissal, ha nem.
     */
    public boolean step(Pos dest) {
        pos = dest;
        return true;
    }

    /**
     * Megvisgálja, hogy a paraméterben kapott (Pos) dest célhelyre szabad-e lépnie,
     * majd visszalép az eredeti pozíciójára.
     *
     * @param dest (Pos) Pozíció - (oszlop, sor értékekkel)
     * @return Igaz értékkel tér vissza ha megtehető volt a lépés, hamissal ha nem.
     */
    public boolean validStep(Pos dest) {
        Pos oldpos = pos;
        if (step(dest)) {
            setPos(oldpos);
            return true;
        }
        setPos(oldpos);
        return false;
    }

    /**
     * Lekérdezi a bábu típusát.
     *
     * @return Visszatérési értéke a bábu típusa.
     */
    public abstract String getType();

    /**
     * Kiiratás céljából, minden bábu színtől függően a hozzá tartozó karaktert kapja.
     * @return A bábu UTF8-as karakterét adja vissza.
     */
    public abstract String toString();

}
