package AuctionHouseProject.AuctionSystem;

/**
 * The type Offer.
 * <p>
 * Class that describes the behaviour of an offer made by a client for a product. It is characterized by a maximum
 * Price that the client is able to offer and his current offer.
 */
public class Offer {
    /**
     * The maximum price that the client is able to offer for a product.
     */
    private final double maxPrice;
    /**
     * The client's current offered price for the product.
     */
    private double currentPrice = 0;

    /**
     * Instantiates a new Offer.
     *
     * @param maxPrice the maximum price that the client is able to offer for a product
     */
    public Offer(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * Gets the maximum price that the client is able to offer for a product.
     *
     * @return the maximum price that the client is able to offer for a product
     */
    public double getMaxPrice() {
        return maxPrice;
    }

    /**
     * Gets the client's current offered price for a product.
     *
     * @return the client's current offered price for a product
     */
    public double getCurrentPrice() {
        return currentPrice;
    }

    /**
     * Sets the client's current offered price for a product.
     *
     * @param currentPrice the client's current offered price for a product
     */
    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
