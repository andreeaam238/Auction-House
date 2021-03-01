package AuctionHouseProject.AuctionSystem;

/**
 * The type Auction.
 * <p>
 * Class that describes the behaviour of an Auction. It is characterized by an id, a minimum number of participants,
 * the current number of participants, the auctioned product's id and the number of steps for the auction.
 */
public class Auction {
    /**
     * The auction's id.
     */
    private int id;
    /**
     * The minimum number of participants that the auction requires in order to start.
     */
    private int noParticipants;
    /**
     * The current number of participants.
     */
    private int currentNoParticipants = 0;
    /**
     * The auctioned product's id.
     */
    private int productId;
    /**
     * The number of steps in which the auction is supposed to happen.
     */
    private int maxNoSteps;

    /**
     * Paramless Constructor.
     * <p>
     * Instantiates a new Auction.
     */
    public Auction() {
    }

    /**
     * Instantiates a new Auction.
     *
     * @param id             the auction's id
     * @param noParticipants the minimum number of participants for the auction
     * @param productId      the auctioned product's id
     * @param maxNoSteps     the maximum number of steps for the auction
     */
    public Auction(int id, int noParticipants, int productId, int maxNoSteps) {
        this.id = id;
        this.noParticipants = noParticipants;
        this.productId = productId;
        this.maxNoSteps = maxNoSteps;
    }

    /**
     * Gets the current number of participants.
     *
     * @return the current number of participants
     */
    public int getCurrentNoParticipants() {
        return currentNoParticipants;
    }

    /**
     * Sets the current number of participants.
     *
     * @param currentNoParticipants the current number of participants
     */
    public void setCurrentNoParticipants(int currentNoParticipants) {
        this.currentNoParticipants = currentNoParticipants;
    }

    /**
     * Gets the auction's id.
     *
     * @return the auction's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the auction's id.
     *
     * @param id the auction's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the minimum number of participants.
     *
     * @return the minimum number of participants
     */
    public int getNoParticipants() {
        return noParticipants;
    }

    /**
     * Sets the minimum number of participants.
     *
     * @param noParticipants the minimum number of participants
     */
    public void setNoParticipants(int noParticipants) {
        this.noParticipants = noParticipants;
    }

    /**
     * Gets the auctioned product's id.
     *
     * @return the auctioned product's id
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets the auctioned product's id.
     *
     * @param productId the auctioned product's id
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Gets the maximum number of steps for the auction.
     *
     * @return the maximum number of steps for the auction
     */
    public int getMaxNoSteps() {
        return maxNoSteps;
    }

    /**
     * Sets the maximum number of steps for the auction.
     *
     * @param maxNoSteps the maximum number of steps for the auction
     */
    public void setMaxNoSteps(int maxNoSteps) {
        this.maxNoSteps = maxNoSteps;
    }

}
