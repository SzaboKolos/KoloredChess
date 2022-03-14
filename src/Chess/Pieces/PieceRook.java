package Chess.Pieces;

import Chess.Pos;

public class PieceRook extends Piece {
    /**
     * Bástya típus konstruktor,
     * létrehoz egy bástya típusú bábu objektumot,
     * paraméterben kapott koordinátával, névvel és színnel.
     *
     * @param y A bábu helyzetének y értéke.
     * @param x A bábu helyzetének x értéke.
     * @param c A bábu színe.
     * @param n A bábu neve.
     */
    public PieceRook(int y, int x, String c, String n) {
        super(y, x, c, n);
    }

    @Override
    public String toString() {
        if (color.equals("B")) {
            return " ♜";
        } else {
            return " ♖";
        }
    }

    @Override
    public boolean step(Pos dest) {
        if ((where().getPosX() == dest.getPosX()) || (where().getPosY() == dest.getPosY())) {
            setPos(dest);
            return true;
        }
        return false;
    }

    @Override
    public String getType() {
        return "Rook";
    }
}
