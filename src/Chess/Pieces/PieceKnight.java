package Chess.Pieces;

import Chess.Pos;

public class PieceKnight extends Piece {
    /**
     * Lovag típus konstruktor,
     * létrehoz egy ló/lovag típusú bábu objektumot,
     * paraméterben kapott koordinátával, névvel és színnel.
     *
     * @param y A bábu helyzetének y értéke.
     * @param x A bábu helyzetének x értéke.
     * @param c A bábu színe.
     * @param n A bábu neve.
     */
    public PieceKnight(int y, int x, String c, String n) {
        super(y, x, c, n);
    }

    @Override
    public String toString() {
        if (color.equals("B")) {
            return " ♞";
        } else {
            return " ♘";
        }
    }

    @Override
    public boolean step(Pos dest) {
        if (((dest.getPosX() == where().getPosX() + 1) && (dest.getPosY() == where().getPosY() + 2))
                || ((dest.getPosX() == where().getPosX() + 1) && (dest.getPosY() == where().getPosY() - 2))
                || ((dest.getPosX() == where().getPosX() - 1) && (dest.getPosY() == where().getPosY() + 2))
                || ((dest.getPosX() == where().getPosX() - 1) && (dest.getPosY() == where().getPosY() - 2))

                || ((dest.getPosX() == where().getPosX() + 2) && (dest.getPosY() == where().getPosY() + 1))
                || ((dest.getPosX() == where().getPosX() + 2) && (dest.getPosY() == where().getPosY() - 1))
                || ((dest.getPosX() == where().getPosX() - 2) && (dest.getPosY() == where().getPosY() + 1))
                || ((dest.getPosX() == where().getPosX() - 2) && (dest.getPosY() == where().getPosY() - 1))) {
            setPos(dest);
            return true;
        }
        return false;
    }

    @Override
    public String getType() {
        return "Knight";
    }
}
