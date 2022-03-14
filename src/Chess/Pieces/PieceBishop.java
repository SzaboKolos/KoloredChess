package Chess.Pieces;

import Chess.Pos;

public class PieceBishop extends Piece {
    /**
     * Futó típus konstruktor,
     * létrehoz egy futó típusú bábu objektumot,
     * paraméterben kapott koordinátával, névvel és színnel.
     *
     * @param y A bábu helyzetének y értéke.
     * @param x A bábu helyzetének x értéke.
     * @param c A bábu színe.
     * @param n A bábu neve.
     */
    public PieceBishop(int y, int x, String c, String n) {
        super(y, x, c, n);
    }

    @Override
    public String toString() {
        if (color.equals("B")) {
            return " ♝";
        } else {
            return " ♗";
        }
    }

    @Override
    public boolean step(Pos dest) {
        for (int i = 1; i < 8; i++) {
            if ((dest.getPosX() == (where().getPosX()) + i) && (dest.getPosY() == (where().getPosY()) + i)
                    || (dest.getPosX() == (where().getPosX()) - i) && (dest.getPosY() == (where().getPosY()) - i)
                    || (dest.getPosX() == (where().getPosX()) + i) && (dest.getPosY() == (where().getPosY()) - i)
                    || (dest.getPosX() == (where().getPosX()) - i) && (dest.getPosY() == (where().getPosY()) + i)) {
                setPos(dest);
                return true;
            }
        }
        return false;
    }

    @Override
    public String getType() {
        return "Bishop";
    }
}
