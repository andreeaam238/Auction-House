package AuctionHouseProject.People;

import AuctionHouseProject.AuctionSystem.AuctionHouse;
import AuctionHouseProject.Products.Product;

/**
 * The type Admin.
 * <p>
 * Class that describes the behaviour of an Admin of an Auction House. It is characterized by an Auction House that he
 * runs.
 */
public class Admin extends Employee {
    /**
     * The Auction House.
     */
    private AuctionHouse auctionHouse;

    /**
     * Instantiates a new Admin.
     */
    public Admin() {
        super(1);
        this.auctionHouse = AuctionHouse.getInstance();
    }

    /**
     * Gets the auction house.
     *
     * @return the auction house
     */
    public AuctionHouse getAuctionHouse() {
        return auctionHouse;
    }

    /**
     * Sets the auction house.
     *
     * @param auctionHouse the auction house
     */
    public void setAuctionHouse(AuctionHouse auctionHouse) {
        this.auctionHouse = auctionHouse;
    }

    /**
     * Add a product to the auction house.
     *
     * @param product                     the product
     * @param maxNoStepsForAuction        the maximum number of steps for the auction
     * @param minNoParticipantsForAuction the minimum number of participants for the auction
     */
    public void addProductToAuctionHouse(Product product, int maxNoStepsForAuction,
                                         int minNoParticipantsForAuction) {
        Thread adminThread = new Thread(new RunnableAdmin(product, maxNoStepsForAuction,
                minNoParticipantsForAuction));

        adminThread.start();
        while (adminThread.isAlive()) {
        }
    }

    /**
     * The type Runnable Admin.
     */
    private class RunnableAdmin implements Runnable {
        /**
         * The Product.
         */
        Product product;
        /**
         * The maximum number of steps for the auction.
         */
        int maxNoStepsForAuction;
        /**
         * The minimum number of participants for the auction.
         */
        int minNoParticipantsForAuction;

        /**
         * Instantiates a new Runnable admin.
         *
         * @param product                     the product
         * @param maxNoStepsForAuction        the maximum number of steps for the auction
         * @param minNoParticipantsForAuction the minimum number of participants for the auction
         */
        public RunnableAdmin(Product product, int maxNoStepsForAuction, int minNoParticipantsForAuction) {
            this.product = product;
            this.maxNoStepsForAuction = maxNoStepsForAuction;
            this.minNoParticipantsForAuction = minNoParticipantsForAuction;
        }

        /**
         * Method used for adding a new product to the Auction House.
         */
        @Override
        public void run() {
            synchronized (AuctionHouse.getInstance().getProducts()) {
                AuctionHouse.getInstance().addProduct(product, maxNoStepsForAuction, minNoParticipantsForAuction);

                try {
                    Thread.sleep((int) (Math.random() * 1000));
                } catch (InterruptedException ignored) {
                }
            }
        }
    }
}
