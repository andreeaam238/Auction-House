package AuctionHouseProject.AuctionSystem;

import java.util.Scanner;


/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // Open the control panel.
        AuctionHouseService storeOwnerInterface = new AuctionHouseService();
        Scanner scanner = new Scanner(System.in);

        // Give instructions that should be executed on the Auction System.
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            storeOwnerInterface.controlPanel(command);
        }

    }
}
