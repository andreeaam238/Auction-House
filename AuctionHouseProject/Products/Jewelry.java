package AuctionHouseProject.Products;

/**
 * The type Jewelry.
 * <p>
 * Class that describes the behaviour of a piece of Jewelry. It is characterized by a material and a gemstone (that can
 * be whether present or not).
 */
public class Jewelry extends Product {
    /**
     * The Jewelry's material.
     */
    private final String material;
    /**
     * Whether the Jewelry has a gemstone or not.
     */
    private final boolean gemstone;

    /**
     * Instantiates a new piece of Jewelry.
     *
     * @param builder the jewelry builder
     */
    private Jewelry(Builder builder) {
        super(builder);
        this.material = builder.material;
        this.gemstone = builder.gemstone;
    }

    /**
     * Gets the jewelry's material.
     *
     * @return the jewelry's material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Whether the jewelry has a gemstone or not.
     *
     * @return true if the jewelry has a gemstone, else false
     */
    public boolean isGemstone() {
        return gemstone;
    }

    /**
     * The Jewelry in human-readable format.
     *
     * @return the jewelry in human-readable format
     */
    @Override
    public String toString() {
        if (gemstone) {
            return "The jewelry " + super.getName() + " with gemstone and and with ID " + super.getId() + " is made of "
                    + material + " in " + super.getYear() + " and has the minimum selling price " + super.getMinPrice()
                    + ".";
        } else {
            return "The jewelry " + super.getName() + " without gemstone and with the ID " + super.getId() +
                    ", is made of " + material + " in " + super.getYear() + " and has the minimum selling price " +
                    super.getMinPrice() + ".";
        }

    }

    /**
     * The type Builder.
     * <p>
     * Used for building a piece of Jewelry.
     */
    public static class Builder extends Product.Builder< Builder > {
        /**
         * The material.
         */
        private String material;
        /**
         * Has gemstone or not.
         */
        private boolean gemstone;

        /**
         * Sets the material.
         *
         * @param material the material
         * @return the material
         */
        public Builder setMaterial(String material) {
            this.material = material;
            return this;
        }

        /**
         * Sets whether it has a gemstone or not.
         *
         * @param gemstone whether it has a gemstone or not
         * @return whether it has a gemstone or not
         */
        public Builder setGemstone(boolean gemstone) {
            this.gemstone = gemstone;
            return this;
        }

        /**
         * Build the piece of Jewelry.
         *
         * @return the built piece of jewelry
         */
        public Jewelry build() {
            return new Jewelry(this);
        }

        /**
         * Instance of the Builder.
         *
         * @return instance of the builder
         */
        @Override
        protected Builder self() {
            return this;
        }

    }
}
