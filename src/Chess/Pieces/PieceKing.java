package Chess.Pieces;

import Chess.Pos;

public class PieceKing extends Piece {
    /**
     * Boolean változó, amely megadja, hogy a király sakkban van-e.
     */
    private boolean check;
    /**
     * Boolean változó, amely megadja, hogy a király sakk-mattban van-e,
     * (ha igaz, vége a játéknak)
     */
    private boolean checkmate;

    /**
     * Király típus konstruktor,
     * létrehoz egy király típusú bábu objektumot,
     * paraméterben kapott koordinátával, névvel és színnel.
     *
     * @param y A bábu helyzetének y értéke.
     * @param x A bábu helyzetének x értéke.
     * @param c A bábu színe.
     * @param n A bábu neve.
     */
    public PieceKing(int y, int x, String c, String n) {
        super(y, x, c, n);
    }

    //------------ Setters -----------------

    /**
     * Beállítja a király sakk értékét.
     *
     * @param check Ha igaz értéket kap akkor a király sakkban lesz.
     */
    public void setCheck(boolean check) {
        this.check = check;
    }

    /**
     * @return Visszaadja a király sakk értékét.
     */
    public boolean getCheck() {
        return (this.check);
    }

    /**
     * A függvény meghívásánál az adott király sakk-matt értéke igaz lesz,
     * ami az ellenfél csapat győzelmét okozza.
     */
    public void setCheckmate() {
        this.checkmate = true;
    }

    /**
     * Lekérdezi a sakk-matt értékét.
     *
     * @return Visszaajda, hogy sakk-mattban van-e a király.
     */
    public boolean getCheckmate() {
        return checkmate;
    }

    //--------------------------------------
    @Override
    public String toString() {
        if (color.equals("B")) {
            return " ♚";
        } else {
            return " ♔";
        }
    }

    @Override
    public boolean step(Pos dest) {
        if (((Math.abs(where().getPosX() - dest.getPosX()) <= 1) && ((Math.abs(where().getPosY() - dest.getPosY()) <= 1)))) {
            setPos(dest);
            return true;
        }
        return false;
    }

    @Override
    public String getType() {
        return "King";
    }
}
