package AuctionHouseProject.People;

import AuctionHouseProject.AuctionSystem.AuctionHouse;

import java.util.Random;

/**
 * The type Client.
 * <p>
 * Class that describes the behaviour of a Client. It is characterized by an id, a name, an address, a number of
 * participations and a number of auctions that he has won.
 */
public abstract class Client {
    /**
     * The Client's id.
     */
    private int id;
    /**
     * The Client's name.
     */
    private String name;
    /**
     * The Client's address.
     */
    private String address;
    /**
     * The number of auctions to which the Client has participated.
     */
    private int noParticipations;
    /**
     * The number of auctions the Client has won.
     */
    private int noWonAuctions;

    /**
     * Instantiates a new Client.
     *
     * @param name    the Client's name
     * @param address the Client's address
     */
    public Client(String name, String address) {
        this.name = name;
        this.address = address;
        this.noParticipations = 0;
        this.noWonAuctions = 0;
    }

    /**
     * The Client in human-readable format.
     *
     * @return the client in human-readable format
     */
    @Override
    public String toString() {
        return (name + " tocmai s-a alaturat sistemului de licitatii online.");
    }

    /**
     * Gets the Client's id.
     *
     * @return the client's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the client's id.
     *
     * @param id the client's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the client's name.
     *
     * @return the client's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the client's name.
     *
     * @param name the client's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the client's address.
     *
     * @return the client's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the client's address.
     *
     * @param address the client's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the client's number of participations in an auction.
     *
     * @return the client's number of participations in an auction.
     */
    public int getNoParticipations() {
        return noParticipations;
    }

    /**
     * Sets the client's number of participations in an auction.
     *
     * @param noParticipations the client's number of participations in an auction
     */
    public void setNoParticipations(int noParticipations) {
        this.noParticipations = noParticipations;
    }

    /**
     * Gets the number of auctions the client has won.
     *
     * @return the number of auctions the client has won
     */
    public int getNoWonAuctions() {
        return noWonAuctions;
    }

    /**
     * Sets the number of auctions the client has won.
     *
     * @param noWonAuctions the number of auctions the client has won
     */
    public void setNoWonAuctions(int noWonAuctions) {
        this.noWonAuctions = noWonAuctions;
    }

    /**
     * Compute the client's new offered price for an auction.
     *
     * @param currentPrice the current price
     * @param maxPrice     the maximum price
     * @return the new price
     */
    public double updateOffer(double currentPrice, double maxPrice) {
        return Math.round(currentPrice + (maxPrice - currentPrice) * new Random().nextDouble());
    }

    /**
     * Gets auction's result.
     * <p>
     * The client has won the auctions.
     *
     * @param message   the message
     * @param brokerFee the broker's request for fee
     * @return the broker's fee
     */
    public double getAuctionResult(String message, double brokerFee) {
        noParticipations += 1;
        noWonAuctions += 1;
        return brokerFee;
    }

    /**
     * Gets auction's result.
     * <p>
     * The client has lost the auction or the product wasn't sold.
     *
     * @param message the message
     */
    public void getAuctionResult(String message) {
        noParticipations += 1;
    }

    /**
     * Request list of products available for auction in teh Auction System.
     */
    public void requestListOfProducts() {
        System.out.println("Request for listing the products available for sale from the client " + name +
                " with the ID " + id + ":");
        Thread clientThread = new Thread(new RunnableClient());
        clientThread.start();
        while (clientThread.isAlive()) {
        }
    }

    /**
     * The type Runnable Client
     */
    private class RunnableClient implements Runnable {
        /**
         * Method used for removing requesting a list of products available for auction in the Auction House.
         */
        @Override
        public void run() {
            try {
                synchronized (AuctionHouse.getInstance().getProducts()) {
                    AuctionHouse.getInstance().listProducts();
                    Thread.sleep((int) (Math.random() * 1000));
                }
            } catch (InterruptedException ignored) {
            }
        }
    }
}
