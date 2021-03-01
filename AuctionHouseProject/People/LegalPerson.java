package AuctionHouseProject.People;

import AuctionHouseProject.Enums.Company;

/**
 * The type Legal person.
 * <p>
 * Class that describes the behaviour of a Legal Person in the Auction System. It is characterized by a company and a
 * share capital.
 */
public class LegalPerson extends Client {
    /**
     * The Legal Person's company.
     */
    private final Company company;
    /**
     * The Legal Person's company's share capital.
     */
    private double shareCapital;

    /**
     * Instantiates a new Legal person.
     *
     * @param name         the legal person's name
     * @param address      the legal person's address
     * @param company      the legal person's company
     * @param shareCapital the legal person's company's share capital
     */
    public LegalPerson(String name, String address, Company company, double shareCapital) {
        super(name, address);
        this.company = company;
        this.shareCapital = shareCapital;
    }

    /**
     * Gets the legal person's company.
     *
     * @return the legal person's company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Gets the legal person's company's share capital.
     *
     * @return the legal person's company's share capital
     */
    public double getShareCapital() {
        return shareCapital;
    }

    /**
     * Sets the legal person's company's share capital.
     *
     * @param shareCapital the legal person's company's share capital
     */
    public void setShareCapital(double shareCapital) {
        this.shareCapital = shareCapital;
    }

}