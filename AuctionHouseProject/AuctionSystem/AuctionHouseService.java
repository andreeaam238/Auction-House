package AuctionHouseProject.AuctionSystem;

import AuctionHouseProject.Enums.Company;
import AuctionHouseProject.People.*;
import AuctionHouseProject.PrintingFeature.ConsoleColors;
import AuctionHouseProject.Products.Product;
import AuctionHouseProject.Products.ProductFactory;

import static java.util.Arrays.copyOfRange;

/**
 * The type Auction house service.
 * <p>
 * Class that describes the behaviour of a server where all the requests for the Auction House arrive, they can be
 * either from the client, brokers or the admin. It is characterized by an auction house and an admin.
 */
public class AuctionHouseService {
    /**
     * The Auction house.
     */
    AuctionHouse auctionHouse;
    /**
     * The Admin.
     */
    Admin admin;

    /**
     * Instantiates a new Auction House Service.
     */
    public AuctionHouseService() {
        auctionHouse = AuctionHouse.getInstance();
        System.out.println(ConsoleColors.RED + "Welcome to the Online Auction House!" + ConsoleColors.RESET);

        admin = new Admin();
        System.out.println(ConsoleColors.RED_BOLD + "The admin has connected." + ConsoleColors.RESET);
    }

    /**
     * Control panel.
     * <p>
     * This method is used for converting commands/requests to actions.
     *
     * @param command the command
     */
    void controlPanel(String command) {
        // Tokenize the command.
        command = command.replace("\n", "");
        String[] commandInfo = command.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

        // Execute the command.
        switch (commandInfo[0]) {
            // Request for adding brokers to the Auction System by the admin.
            case "addBrokers" -> addBrokers(Integer.parseInt(commandInfo[1]));

            // Request for signing up a new client into the Auction System.
            case "addClient" -> {
                Client newClient = null;

                if (commandInfo[1].equals("NaturalPerson")) {
                    newClient = createClientNaturalPerson(commandInfo[2].replace("\"", ""),
                            commandInfo[3], commandInfo[4]);
                } else if (commandInfo[1].equals("LegalPerson")) {
                    newClient = createClientLegalPerson(commandInfo[2].replace("\"", ""),
                            commandInfo[3], commandInfo[4], Double.parseDouble(commandInfo[5]));
                }

                if (newClient != null) {
                    auctionHouse.signUpClient(newClient);
                    System.out.println(ConsoleColors.PURPLE + newClient.getName() + " has joined the online auction " +
                            "system with the client ID " + newClient.getId() + "." + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED_BOLD + "An error has occurred while trying to add the client" +
                            " to the system. Please contact the support team." + ConsoleColors.RESET);
                }
            }

            // Request for adding a new product to the Auction House by the admin.
            case "addProduct" -> {
                String productName = commandInfo[2].replace("\"", "");
                int year = Integer.parseInt(commandInfo[3]);
                double minPrice = Double.parseDouble(commandInfo[4]);
                int productId = 1;
                if (auctionHouse.getProducts().size() != 0) {
                    productId = auctionHouse.getProducts().get(auctionHouse.getProducts().size() - 1).getId() + 1;
                }
                String[] productInfo = copyOfRange(commandInfo, 5, 7);


                Product newProduct = new ProductFactory().createProduct(commandInfo[1], productName, year,
                        minPrice, productId, productInfo);

                if (newProduct != null) {
                    admin.addProductToAuctionHouse(newProduct, Integer.parseInt(commandInfo[7]),
                            Integer.parseInt(commandInfo[8]));
                }
            }

            // Request for printing the products available for auction by a client.
            case "listProducts" -> {
                int clientId = Integer.parseInt(commandInfo[1]);
                for (Client client : auctionHouse.getClients()) {
                    if (client.getId() == clientId) {
                        client.requestListOfProducts();
                        return;
                    }
                }

                System.out.println(ConsoleColors.RED_BOLD + "The client with the ID " + clientId + " was not found in" +
                        " the system." + ConsoleColors.RESET);
            }

            // Request for signing up for an auction by a client.
            case "requestSignUpForAuction" -> {
                Client clientToEnroll = null;
                int idClient = Integer.parseInt(commandInfo[1]);
                for (Client client : auctionHouse.getClients()) {
                    if (client.getId() == idClient) {
                        clientToEnroll = client;
                    }
                }
                if (clientToEnroll != null) {
                    auctionHouse.signUpRequestForAuction(Integer.parseInt(commandInfo[2]),
                            Double.parseDouble(commandInfo[3]), clientToEnroll);
                }
            }
        }

    }

    /**
     * Method used for creating a new client which is a Legal Person.
     *
     * @param name         the client's name
     * @param address      the client's address
     * @param companyType  the client's company type
     * @param shareCapital the client's company's share capital
     * @return the client
     */
    private LegalPerson createClientLegalPerson(String name, String address, String companyType,
                                                double shareCapital) {
        return new LegalPerson(name, address, Company.valueOf(companyType), shareCapital);
    }

    /**
     * Method used for creating a new client which is a Natural Person.
     *
     * @param name      the client's name
     * @param address   the client's address
     * @param birthDate the client's birth date
     * @return the client
     */
    private NaturalPerson createClientNaturalPerson(String name, String address, String birthDate) {
        return new NaturalPerson(name, address, birthDate);
    }

    /**
     * Add a specified number o brokers to the Auction System.
     *
     * @param noBrokers number of brokers to add
     */
    private void addBrokers(int noBrokers) {
        for (int i = 0; i < noBrokers; i++) {
            auctionHouse.addBroker(new Broker());
        }
        System.out.println(ConsoleColors.CYAN + noBrokers + " new brokers have been employed by the auction house."
                + ConsoleColors.RESET);
    }
}
