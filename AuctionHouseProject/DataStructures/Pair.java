package AuctionHouseProject.DataStructures;

/**
 * The type Pair.
 * <p>
 * Class that describes the behaviour of a Pair. It is a generic type so it can store any combination of 2 different
 * types. It is characterized by two fields of generic type.
 *
 * @param <X> the type parameter
 * @param <Y> the type parameter
 */
public class Pair< X, Y > {
    /**
     * The first element of the Pair.
     */
    private X x;
    /**
     * The second element of the Pair.
     */
    private Y y;

    /**
     * Instantiates a new Pair.
     *
     * @param x the pair's first element
     * @param y the pair's second element
     */
    public Pair(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the pair's first element.
     *
     * @return the pair's first element
     */
    public X getX() {
        return x;
    }

    /**
     * Sets the pair's first element.
     *
     * @param x the pair's first element
     */
    public void setX(X x) {
        this.x = x;
    }

    /**
     * Gets the pair's second element.
     *
     * @return the pair's second element
     */
    public Y getY() {
        return y;
    }

    /**
     * Sets the pair's second element.
     *
     * @param y the pair's second element
     */
    public void setY(Y y) {
        this.y = y;
    }
}