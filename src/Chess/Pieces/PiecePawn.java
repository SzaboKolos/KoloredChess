package Chess.Pieces;

import Chess.Pos;

public class PiecePawn extends Piece {
    /**
     * A gyalog menetirány szerinti bal-előre koordinátáján található-e bábu. Igaz, ha igen hamis, ha nem.
     */
    private boolean l;
    /**
     * A gyalog menetirány szerinti jobb-előre koordinátáján található-e bábu. Igaz, ha igen hamis, ha nem.
     */
    private boolean r;

    /**
     * Gyalog típus konstruktor,
     * létrehoz egy gyalog típusú bábu objektumot,
     * paraméterben kapott koordinátával, névvel és színnel.
     *
     * @param y A bábu helyzetének y értéke.
     * @param x A bábu helyzetének x értéke.
     * @param c A bábu színe.
     * @param n A bábu neve.
     */
    public PiecePawn(int y, int x, String c, String n) {
        super(y, x, c, n);
    }

    @Override
    public String toString() {
        if (color.equals("B")) {
            return " ♟";
        } else {
            return " ♙";
        }
    }

    /**
     * Ha igaz értéket kap paraméterben, akkor (menetirány szerinti) bal-előre bábu található,
     * így ha az más szinű mint a kiválasztott gyalog, akkor ki tudja ütni.
     *
     * @param l Igaz értékű, ha bal-előre van bábu.
     */
    public void hitL(boolean l) {
        this.l = l;
    }

    /**
     * Ha igaz értéket kap paraméterben, akkor (menetirány szerinti) jobb-előre bábu található,
     * így ha az más szinű mint a kiválasztott gyalog, akkor ki tudja ütni.
     *
     * @param r Igaz értékű, ha jobb-előre van bábu.
     */
    public void hitR(boolean r) {
        this.r = r;
    }


    @Override
    public boolean step(Pos dest) {
        if (color.equals("W")) {
            if (where().getPosX() == 1) {
                if (((dest.getPosX() == 2) || (dest.getPosX() == 3)) && dest.getPosY() == where().getPosY()) {
                    setPos(dest);
                    return true;
                }

            } else {
                if ((dest.getPosX() == where().getPosX() + 1) && dest.getPosY() == where().getPosY()) {
                    setPos(dest);
                    return true;
                }
            }
        } else {
            if (where().getPosX() == 6) {
                if (((dest.getPosX() == 4) || (dest.getPosX() == 5)) && dest.getPosY() == where().getPosY()) {
                    setPos(dest);
                    return true;
                }
            } else {
                if ((dest.getPosX() == where().getPosX() - 1) && dest.getPosY() == where().getPosY()) {
                    setPos(dest);
                    return true;
                }
            }
        }
        if (l) {
            if (color.equals("W")) {
                if ((dest.getPosX() == where().getPosX() + 1) && dest.getPosY() == where().getPosY() - 1) {
                    setPos(dest);
                    return true;
                }
            } else if ((dest.getPosX() == where().getPosX() - 1) && dest.getPosY() == where().getPosY() - 1) {
                setPos(dest);
                return true;
            }
        }
        if (r) {
            if (color.equals("W")) {
                if ((dest.getPosX() == where().getPosX() + 1) && dest.getPosY() == where().getPosY() + 1) {
                    setPos(dest);
                    return true;
                }
            } else if ((dest.getPosX() == where().getPosX() - 1) && dest.getPosY() == where().getPosY() + 1) {
                setPos(dest);
                return true;
            }
        }
        return false;
    }

    @Override
    public String getType() {
        return "Pawn";
    }
}

