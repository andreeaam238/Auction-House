package AuctionHouseProject.People;

import AuctionHouseProject.AuctionSystem.Auction;
import AuctionHouseProject.AuctionSystem.AuctionHouse;
import AuctionHouseProject.AuctionSystem.Offer;
import AuctionHouseProject.DataStructures.Pair;
import AuctionHouseProject.Products.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Broker.
 * <p>
 * Class that describes the behaviour of a broker that is employed by an Auction House. It is characterized by a list
 * of clients and his extra earnings from his clients that win auctions. He is "the middle man" between the Auction
 * House and the Clients.
 */
public class Broker extends Employee {
    /**
     * Revolutionary List of Clients that also stores the auctions that the clients have enrolled using the current
     * broker and their offers for each one of them.
     */
    private Map< Client, List< Pair< Auction, Offer > > > clients;
    /**
     * The brokers earnings for winning auctions for his clients.
     */
    private double earnings;

    /**
     * Instantiates a new Broker.
     */
    public Broker() {
        super();
        this.earnings = 0;
        this.clients = new HashMap<>();
    }

    /**
     * Gets the broker's client list.
     *
     * @return the broker's client list
     */
    public Map< Client, List< Pair< Auction, Offer > > > getClientList() {
        return clients;
    }

    /**
     * Sets the broker's client list.
     *
     * @param clients the broker's client list
     */
    public void setClients(Map< Client, List< Pair< Auction, Offer > > > clients) {
        this.clients = clients;
    }

    /**
     * Gets the broker's earnings.
     *
     * @return the broker's earnings
     */
    public double getEarnings() {
        return earnings;
    }

    /**
     * Sets the broker's earnings.
     *
     * @param earnings the broker's earnings
     */
    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

    /**
     * Add client to the broker's clients list.
     *
     * @param client       the client
     * @param auction      the auction that he signed up for
     * @param offeredPrice the maximum price he is able to pay for the product
     */
    public void addClient(Client client, Auction auction, double offeredPrice) {
        // If the client is already in the broker's client list only add the new auction to the client's list of
        // auctions, else first add the client, then the auction.
        if (clients.containsKey(client)) {
            clients.get(client).add(new Pair<>(auction, new Offer(offeredPrice)));
        } else {
            ArrayList< Pair< Auction, Offer > > auctions = new ArrayList<>();
            auctions.add(new Pair<>(auction, new Offer(offeredPrice)));
            clients.put(client, auctions);
        }
    }

    /**
     * Remove client from the broker's client list.
     *
     * @param client the client to be removed
     */
    public void removeClient(Client client) {
        this.clients.remove(client);
    }

    /**
     * Notify clients of the new price of the product and request and process their new offers for it..
     *
     * @param auction  the auction
     * @param newPrice the product's new price
     */
    public void notifyClients(Auction auction, double newPrice) {
        for (Client client : clients.keySet()) {
            for (Pair< Auction, Offer > auctionOfferPair : clients.get(client)) {
                if (auction == auctionOfferPair.getX()) {
                    double clientMaxPrice = auctionOfferPair.getY().getMaxPrice();
                    double clientCurrentPrice = auctionOfferPair.getY().getCurrentPrice();

                    if (newPrice < clientMaxPrice && newPrice != clientCurrentPrice) {
                        double pretNouClient = client.updateOffer(newPrice, clientMaxPrice);
                        Offer offer = auctionOfferPair.getY();
                        offer.setCurrentPrice(pretNouClient);
                        auctionOfferPair.setY(offer);
                    }
                    break;
                }
            }
        }
    }

    /**
     * Notify clients of the auction's ending.
     *
     * @param auction the auction
     * @param winner  the auction's winner
     * @param product the product
     */
    public void notifyClientsOfAuctionEnding(Auction auction, Client winner, Product product) {
        List< Client > exClients = new ArrayList<>();

        for (Client client : clients.keySet()) {
            for (Pair< Auction, Offer > auctionOfferPair : clients.get(client)) {
                if (auction == auctionOfferPair.getX()) {

                    // Notify the client.
                    notifyByTypeOfEnding(winner, product, client);

                    // Remove the auction from the list and if necessary, the client.
                    clients.get(client).remove(auctionOfferPair);
                    if (clients.get(client).isEmpty()) {
                        exClients.add(client);
                    }

                    break;
                }
            }
        }

        for (Client client : exClients) {
            removeClient(client);
        }
    }

    /**
     * Method used for notifying a client of the auction's ending: he won, he lost or the product wasn't sold.
     *
     * @param winner  the auction's winner
     * @param product the product that the winner has won
     * @param client  the client to be informed
     */
    private void notifyByTypeOfEnding(Client winner, Product product, Client client) {
    /* If the client is the winner of the auction send him a congratulations message and request the
       broker's fee. Also the broker whose client has won the auction has to remove the product from the
       product's available for auction list.
    */
        if (winner == client) {
            Thread brokerThread = new Thread(new RunnableBroker(product));
            brokerThread.start();
            while (brokerThread.isAlive()) {
            }

            earnings += client.getAuctionResult("Congratulations, you have won the auction for the "
                    + "product " + product.getName() + ".", computeCommission(winner, product));
        }
        // If the client has lost the auction send him an apology message.
        else if (winner != null) {
            client.getAuctionResult("Unfortunately you have lost the auction for the product " +
                    product.getName() + ".");
        }
        // If the product hasn't been sold inform the client about it.
        else {
            client.getAuctionResult("Unfortunately you have lost the auction for the product " +
                    product.getName() + ", but it hasn't been sold either and it's still available for " +
                    "auction.");
        }
    }

    /**
     * Compute the Broker's fee using the client's info.
     *
     * @param client  the client that has won the auction
     * @param product the product that the client bought
     * @return the broker's fee
     */
    private double computeCommission(Client client, Product product) {
        double commission = 0;
        double pret = product.getSellingPrice();

        if (client instanceof NaturalPerson) {
            if (client.getNoParticipations() < 5) {
                commission = 20 * pret / 100;
            } else {
                commission = 15 * pret / 100;
            }
        } else if (client instanceof LegalPerson) {
            if (client.getNoParticipations() < 25) {
                commission = 25 * pret / 100;
            } else {
                commission = 10 * pret / 100;
            }
        }

        return commission;
    }

    /**
     * The type Runnable Broker.
     */
    private class RunnableBroker implements Runnable {
        /**
         * The Product.
         */
        Product product;

        /**
         * Instantiates a new Runnable broker.
         *
         * @param product the product
         */
        public RunnableBroker(Product product) {
            this.product = product;
        }

        /**
         * Method used for removing a product from the Auction System's product list after it was bought in an auction.
         */
        @Override
        public void run() {
            try {
                synchronized (AuctionHouse.getInstance().getProducts()) {
                    AuctionHouse.getInstance().removeProduct(product);
                    Thread.sleep((int) (Math.random() * 1000));
                }
            } catch (InterruptedException ignored) {
            }
        }
    }
}
