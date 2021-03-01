package AuctionHouseProject.Products;

/**
 * The type Furniture.
 * <p>
 * Class that describes the behaviour of a piece of Furniture. It is characterized by a type and a material.
 */
public class Furniture extends Product {
    /**
     * The Furniture's type.
     */
    private final String type;
    /**
     * The Furniture's material.
     */
    private final String material;

    /**
     * Instantiates a new piece of Furniture.
     *
     * @param builder the furniture builder
     */
    private Furniture(Builder builder) {
        super(builder);
        this.type = builder.type;
        this.material = builder.material;
    }

    /**
     * The Furniture in human-readable format.
     *
     * @return the furniture in human-readable format
     */
    @Override
    public String toString() {
        return "The furniture " + super.getName() + " with the ID " + super.getId() + " is a/an " + type + " made of "
                + material + " in " + super.getYear() + " and has the minimum selling price " + super.getMinPrice() +
                ".";
    }

    /**
     * Gets the furniture's type.
     *
     * @return the furniture's type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the furniture's material.
     *
     * @return the furniture's material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * The type Builder.
     * <p>
     * Used for building a piece of Furniture.
     */
    public static class Builder extends Product.Builder< Builder > {
        /**
         * The type.
         */
        private String type;
        /**
         * The material.
         */
        private String material;

        /**
         * Sets the type.
         *
         * @param type the type
         * @return the type
         */
        public Builder setType(String type) {
            this.type = type;
            return this;
        }

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
         * Build the piece of Furniture.
         *
         * @return the built piece of furniture
         */
        public Furniture build() {
            return new Furniture(this);
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
