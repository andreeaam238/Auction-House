package AuctionHouseProject.AuctionSystem;

import AuctionHouseProject.DataStructures.Pair;
import AuctionHouseProject.People.Broker;
import AuctionHouseProject.People.Client;
import AuctionHouseProject.PrintingFeature.ConsoleColors;
import AuctionHouseProject.Products.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The type Auction house.
 * <p>
 * Class that describes the behaviour of an Auction House. It is characterized a list of products, a list of clients, a
 * list of active auctions and also one of finished auctions and a list of brokers. It also has a lock field and an
 * instance of its own type.
 */
public class AuctionHouse {
    /**
     * Instance of the Auction House.
     */
    private static AuctionHouse instanceOfAuctionHouse = null;

    /**
     * The list of Products.
     */
    private final List< Product > products;
    /**
     * The list of Clients.
     */
    private final List< Client > clients;
    /**
     * The list of active Auctions.
     */
    private final List< Auction > activeAuctions;
    /**
     * The list of finished Auctions.
     */
    private final List< Auction > finishedAuctions;
    /**
     * The list of Brokers.
     */
    private final List< Broker > brokers;

    /**
     * The Lock.
     */
    private final Lock lock = new ReentrantLock();

    /**
     * Instantiates a new Auction House.
     */
    private AuctionHouse() {
        this.products = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.activeAuctions = Collections.synchronizedList(new ArrayList<>());
        this.finishedAuctions = new ArrayList<>();
        this.brokers = new ArrayList<>();
    }

    /**
     * Gets the instance of the Auction House.
     * <p>
     * If the Auction House hasn't been instantiated yet a new one is created and returned, else it returns the
     * instance of the existing Auction House.
     *
     * @return the instance of the Auction House
     */
    public static AuctionHouse getInstance() {
        if (instanceOfAuctionHouse == null) {
            instanceOfAuctionHouse = new AuctionHouse();
        }

        return instanceOfAuctionHouse;
    }

    /**
     * Gets the list of products available for auction.
     *
     * @return the list of products available for auction
     */
    public List< Product > getProducts() {
        return products;
    }

    /**
     * Gets the list of clients.
     *
     * @return the list of clients
     */
    public List< Client > getClients() {
        return clients;
    }

    /**
     * Gets the list of active auctions.
     *
     * @return the list of active auctions
     */
    public List< Auction > getActiveAuctions() {
        return activeAuctions;
    }

    /**
     * Gets the list of finished auctions.
     *
     * @return the list of finished auctions
     */
    public List< Auction > getFinishedAuctions() {
        return finishedAuctions;
    }

    /**
     * Gets the list of brokers.
     *
     * @return the list of brokers
     */
    public List< Broker > getBrokers() {
        return brokers;
    }

    /**
     * Sign up client into the Auction House system.
     *
     * @param client the client
     */
    void signUpClient(Client client) {
        // Add the client to the client list and give him an id.
        clients.add(client);
        client.setId(clients.size());
    }

    /**
     * Gets the auction for a product by its id.
     *
     * @param productId the product's id
     * @return the auction
     */
    Auction getAuction(int productId) {
        /* Find the auction for the specified product and return it. If there isn't any active auction for the product
           then null is returned.
         */
        for (Auction auction : activeAuctions) {
            if (auction.getProductId() == productId) {
                return auction;
            }
        }

        return null;
    }

    /**
     * Create an auction for a specified product, add it to the active auctions list and return it.
     *
     * @param noParticipants the number of participants
     * @param ProductId      the product's id
     * @param maxNoSteps     the maximum number of steps for the auction
     * @return the auction
     */
    Auction createAuction(int noParticipants, int ProductId, int maxNoSteps) {
        int auctionId = 1;
        if (activeAuctions.size() != 0) {
            auctionId = activeAuctions.get(activeAuctions.size() - 1).getId() + 1;
        }

        Auction newAuction = new Auction(auctionId, noParticipants, ProductId, maxNoSteps);
        activeAuctions.add(newAuction);

        return newAuction;
    }

    /**
     * Sign up client for the auction of the product that he desires.
     *
     * @param productId    the product's id
     * @param offeredPrice the maximum offered price for the product
     * @param client       the client
     */
    public void signUpRequestForAuction(int productId, double offeredPrice, Client client) {
        // Check if the desired product exists in the product list.
        Product desiredProduct = null;

        for (Product product : products) {
            if (product.getId() == productId) {
                desiredProduct = product;
            }
        }

        if (desiredProduct == null) {
            System.out.println(ConsoleColors.RED_BOLD + "There is no product with the ID " + productId + " in the " +
                    "system." + ConsoleColors.RESET);
            return;
        }

        // Check if the client's offered price is greater than the minimum price in order for the product to be sold.
        if (offeredPrice < desiredProduct.getMinPrice()) {
            System.out.println(ConsoleColors.RED_BOLD + "The request for signing up for the auction of the product " +
                    desiredProduct.getName() + ", sent by the client " + client.getName() + ", with the ID " +
                    client.getId() + ", was denied because of the offered price." + ConsoleColors.RESET);
            return;
        }

        // Get the auction for the product and increment its number of participants.
        Auction auction = getAuction(productId);
        auction.setCurrentNoParticipants(auction.getCurrentNoParticipants() + 1);

        // Assign a broker to the client.
        Broker broker = brokers.get(auction.getCurrentNoParticipants() % brokers.size());
        broker.addClient(client, auction, offeredPrice);

        System.out.println(ConsoleColors.BLUE_BRIGHT + client.getName() + " has signed up for the auction of the " +
                "product " + desiredProduct.getName() + "." + ConsoleColors.RESET);

        System.out.println(ConsoleColors.BLUE + auction.getCurrentNoParticipants() + "/" + auction.getNoParticipants() +
                " people have signed up for the auction of the product " + desiredProduct.getName() + "." +
                ConsoleColors.RESET);

        // Check if the auction may start.
        if (auction.getCurrentNoParticipants() >= auction.getNoParticipants()) {
            System.out.println(ConsoleColors.RED_BOLD + "The auction for the product " + desiredProduct.getName() +
                    " will start now." + ConsoleColors.RESET);
            auctionTime(auction, desiredProduct);
        }
    }

    /**
     * Add broker to the brokers list.
     *
     * @param broker the broker
     */
    void addBroker(Broker broker) {
        int brokerId = 2;
        if (brokers.size() != 0) {
            brokerId = brokers.get(brokers.size() - 1).getId() + 1;
        }
        broker.setId(brokerId);
        brokers.add(broker);
    }

    /**
     * Method used for simulating the auction.
     * <p>
     * The brokers send request for prices to their clients and the maximum offered price at each step is computed.
     *
     * @param auction the auction
     * @param product the product
     */
    public void auctionTime(Auction auction, Product product) {
        // Set a random starting price lower than the minimum selling price of the product.
        double currentPrice = Math.round(product.getMinPrice() * new Random().nextDouble() / 2) + 1;
        double priceAtCurrentStep = currentPrice;

        // For keeping the client that wins the auction.
        Client winner = null;

        System.out.println(ConsoleColors.RED_BRIGHT + "The starting price for the product " + product.getName() +
                " is " + currentPrice + "." + ConsoleColors.RESET);

        // Simulate each step of the auction.
        for (int i = 1; i <= auction.getMaxNoSteps(); i++) {

            // The brokers request offers from each of their clients that have signed up for this auction.
            for (Broker broker : brokers) {
                broker.notifyClients(auction, currentPrice);

                for (Client client : broker.getClientList().keySet()) {
                    for (Pair< Auction, Offer > auctionOfferPair : broker.getClientList().get(client)) {

                        if (auctionOfferPair.getX() == auction) {

                            System.out.println(ConsoleColors.RED_BRIGHT + client.getName() + " has offered " +
                                    auctionOfferPair.getY().getCurrentPrice() + " at the set " + i + " of the auction."
                                    + ConsoleColors.RESET);

                            // Check if the client's offer is better than the actual best one.
                            if (checkPrice(priceAtCurrentStep, auctionOfferPair.getY().getCurrentPrice(), winner,
                                    client)) {
                                priceAtCurrentStep = auctionOfferPair.getY().getCurrentPrice();
                                winner = client;
                            }
                        }
                    }
                }
            }
            // Update the product's price at the end of each step of the auction.
            currentPrice = priceAtCurrentStep;
            System.out.println(ConsoleColors.RED_BRIGHT + "The product " + product.getName() + " has, at the end of " +
                    "step " + i + " of the auction, the price " + currentPrice + "." + ConsoleColors.RESET);
        }

        /* If the product's current price is lower than the product's minimum selling price then the product can't be
           sold. Each broker should notify their clients that have signed up for this auction of this event.
        */
        if (currentPrice < product.getMinPrice()) {
            System.out.println(ConsoleColors.RED_BOLD + "The auction has ended without selling the product " +
                    product.getName() + "." + ConsoleColors.RESET);
            notifyOfAuctionsEnd(auction, product, null);
            return;
        }

        // Set the product's selling price.
        product.setSellingPrice(currentPrice);

        System.out.println(ConsoleColors.RED_BOLD + "The product " + product.getName() + " has been sold for "
                + product.getSellingPrice() + "." + ConsoleColors.RESET);

        // Each broker notifies their clients that have signed up for this auction of its result.
        notifyOfAuctionsEnd(auction, product, winner);

        // Remove the auction from the active auctions list and add it to the finished ones list.
        activeAuctions.remove(auction);
        finishedAuctions.add(auction);
    }

    /**
     * Method used for notifying each broker's clients of the ending of the auction.
     *
     * @param auction the auction
     * @param product the product
     * @param winner  the auction's winner
     */
    private void notifyOfAuctionsEnd(Auction auction, Product product, Client winner) {
        for (Broker broker : brokers) {
            broker.notifyClientsOfAuctionEnding(auction, winner, product);
        }
    }

    /**
     * Method used for comparing the product's current price with a client's offer.
     *
     * @param currentPrice           the product's current price
     * @param offeredPrice           the client's offered price
     * @param currentFavouriteClient the client that offered the current price for the product
     * @param client                 the client that made the new offer
     * @return True if the new offer is greater than the current one, else False
     */
    private Boolean checkPrice(double currentPrice, double offeredPrice, Client currentFavouriteClient, Client client) {
        /* Compare the offers by their prices and if necessary by the number of auctions won by each of these two
           clients.
        */
        if (offeredPrice > currentPrice) {
            return true;
        } else if (offeredPrice == currentPrice) {
            return currentFavouriteClient == null || currentFavouriteClient.getNoWonAuctions() <=
                    client.getNoWonAuctions();
        }

        return false;
    }

    /**
     * List the products available for auction.
     */
    public void listProducts() {
        lock.lock();
        try {
            if (products.size() == 0) {
                System.out.println(ConsoleColors.RED_BOLD + "At the moment there is no product available for auction." +
                        ConsoleColors.RESET);
            } else {
                products.forEach((product) -> {
                    System.out.println(ConsoleColors.YELLOW + product + ConsoleColors.RESET);
                    try {
                        Thread.sleep((int) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Add product to the auction house.
     *
     * @param product                     the product
     * @param maxNoStepsForAuction        the maximum number of steps for the auction
     * @param minNoParticipantsForAuction the minimum number of participants for the auction
     */
    public void addProduct(Product product, int maxNoStepsForAuction, int minNoParticipantsForAuction) {
        lock.lock();
        try {
            products.add(product);
            Auction auction = createAuction(minNoParticipantsForAuction, product.getId(), maxNoStepsForAuction);

            System.out.println(ConsoleColors.GREEN + "The product " + product.getName() + " with the ID " +
                    product.getId() + " can be bought in the auction with the ID " +
                    auction.getId() + "." + ConsoleColors.RESET);

        } finally {
            lock.unlock();
        }
    }

    /**
     * Remove a product from the auction house.
     *
     * @param product the product to be removed
     */
    public void removeProduct(Product product) {
        lock.lock();
        try {
            products.remove(product);
        } finally {
            lock.unlock();
        }
    }
}
