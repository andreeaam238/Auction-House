package AuctionHouseProject.Products;

/**
 * The type Product.
 * <p>
 * Class that describes the behaviour of a Product in an Auction. It is characterized by an id, a name, a year when it
 * was created, a selling price and a minimum selling price.
 */
public abstract class Product {
    /**
     * The Product's id.
     */
    private final int id;
    /**
     * The Product's name.
     */
    private final String name;
    /**
     * The Product's year of manufacture.
     */
    private final int year;
    /**
     * The Product's selling price.
     */
    private double sellingPrice = 0;
    /**
     * The Product's minimum selling price.
     */
    private double minPrice;

    /**
     * Instantiates a new Product.
     *
     * @param builder the product builder
     */
    Product(Builder< ? > builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.minPrice = builder.minPrice;
        this.year = builder.year;
    }

    /**
     * Gets the product's id.
     *
     * @return the product's id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the product's name.
     *
     * @return the product's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the product's selling price.
     *
     * @return the product's selling price
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     * Sets the product's selling price.
     *
     * @param sellingPrice the product's selling price
     */
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * Gets the product's minimum selling price.
     *
     * @return the product's minimum selling price
     */
    public double getMinPrice() {
        return minPrice;
    }

    /**
     * Sets the product's minimum selling price.
     *
     * @param minPrice the product's minimum selling price
     */
    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * Gets the product's year of manufacture.
     *
     * @return the product's year of manufacture
     */
    public int getYear() {
        return year;
    }

    /**
     * The type Builder.
     * <p>
     * Used for building a Product.
     *
     * @param <T> the type parameter
     */
    abstract static class Builder< T extends Builder< T > > {
        /**
         * The id.
         */
        private int id;
        /**
         * The name.
         */
        private String name;
        /**
         * The year of manufacture.
         */
        private int year;
        /**
         * The minimum selling price.
         */
        private double minPrice;

        /**
         * Sets the id.
         *
         * @param id the id
         * @return the id
         */
        public T setId(int id) {
            this.id = id;
            return self();
        }

        /**
         * Sets the name.
         *
         * @param name the name
         * @return the name
         */
        public T setName(String name) {
            this.name = name;
            return self();
        }

        /**
         * Sets the year.
         *
         * @param year the year
         * @return the year
         */
        public T setYear(int year) {
            this.year = year;
            return self();
        }

        /**
         * Sets the minimum selling price.
         *
         * @param minPrice the minimum selling price
         * @return the minimum selling price
         */
        public T setMinPrice(double minPrice) {
            this.minPrice = minPrice;
            return self();
        }

        /**
         * Build product.
         *
         * @return the built product
         */
        abstract Product build();

        /**
         * Instance of the Builder.
         *
         * @return instance of the builder
         */
        protected abstract T self();
    }
}
