package AuctionHouseProject.Products;

import AuctionHouseProject.Enums.Colors;

/**
 * The type Painting.
 * <p>
 * Class that describes the behaviour of a Painting. It is characterized by a painter that has painted it and the type
 * of paint that he used.
 */
public class Painting extends Product {
    /**
     * The Painting's painter.
     */
    private final String painterName;
    /**
     * The Painting's type of paint.
     */
    private final Colors color;

    /**
     * Instantiates a new piece of Painting.
     *
     * @param builder the painting builder
     */
    private Painting(Builder builder) {
        super(builder);
        this.painterName = builder.painterName;
        this.color = builder.color;
    }

    /**
     * Gets the painter's name.
     *
     * @return the painter's name
     */
    public String getPainterName() {
        return painterName;
    }

    /**
     * Gets the painting's type of paint.
     *
     * @return the painting's type of paint
     */
    public Colors getColor() {
        return color;
    }

    /**
     * The Painting in human-readable format.
     *
     * @return the painting in human-readable format
     */
    @Override
    public String toString() {
        return "The painting " + super.getName() + " with the ID " + super.getId() + " was painted by " + painterName +
                " with " + color + " in " + super.getYear() + " and has the minimum selling price " +
                super.getMinPrice() + ".";
    }

    /**
     * The type Builder.
     * <p>
     * Used for building a Painting.
     */
    public static class Builder extends Product.Builder< Builder > {
        /**
         * The painter's name.
         */
        private String painterName;
        /**
         * The type of paint.
         */
        private Colors color;

        /**
         * Sets the painter's name.
         *
         * @param painterName the painter's name
         * @return the painter's name
         */
        public Builder setPainterName(String painterName) {
            this.painterName = painterName;
            return this;
        }

        /**
         * Sets the paint's type.
         *
         * @param color the paint's type
         * @return the paint's type
         */
        public Builder setColor(Colors color) {
            this.color = color;
            return this;
        }

        /**
         * Build Painting.
         *
         * @return the built painting
         */
        public Painting build() {
            return new Painting(this);
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
