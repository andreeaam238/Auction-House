package AuctionHouseProject.People;

/**
 * The type Natural person.
 * <p>
 * Class that describes the behaviour of a Natural Person in the Auction System. It is characterized by a birth date.
 */
public class NaturalPerson extends Client {
    /**
     * The Natural Person's birth date.
     */
    private final String birthDate;

    /**
     * Instantiates a new Natural person.
     *
     * @param name      the natural person's name
     * @param address   the natural person's address
     * @param birthDate the natural person's birth date
     */
    public NaturalPerson(String name, String address, String birthDate) {
        super(name, address);
        this.birthDate = birthDate;
    }

    /**
     * Gets the natural person's birth date.
     *
     * @return the natural person's birth date
     */
    public String getBirthDate() {
        return birthDate;
    }

}
