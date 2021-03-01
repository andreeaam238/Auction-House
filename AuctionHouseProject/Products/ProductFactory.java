package AuctionHouseProject.Products;

import AuctionHouseProject.Enums.Colors;

/**
 * The type Product factory.
 * <p>
 * Class that describes the behaviour of a Product Factory. It is used for creating new Products of the three types
 * available: Painting, Furniture and Jewelry.
 */
public class ProductFactory {

    /**
     * Create new product depending on its type.
     *
     * @param productType the product's type
     * @param name        the product's name
     * @param year        the product's year of manufacture
     * @param minPrice    the product's minimum selling price
     * @param id          the product's id
     * @param productInfo the product's extra info
     * @return the product
     */
    public Product createProduct(String productType, String name, int year, double minPrice, int id,
                                 String[] productInfo) {
        return switch (productType) {
            case "Painting" -> createPainting(name, year, minPrice, id,
                    productInfo[0].replace("\"", ""), productInfo[1]);
            case "Furniture" -> createFurniture(name, year, minPrice, id,
                    productInfo[0].replace("\"", ""), productInfo[1]);
            case "Jewelry" -> createJewelry(name, year, minPrice, id, productInfo[0].replace("\"", ""),
                    productInfo[1]);
            default -> null;
        };
    }

    /**
     * Create a new painting.
     *
     * @param name        the painting's name
     * @param year        the painting's year of manufacture
     * @param minPrice    the painting's minimum selling price
     * @param id          the painting's id
     * @param painterName the painter's name
     * @param color       the type of paint the painter has used for this painting
     * @return the new painting
     */
    private Painting createPainting(String name, int year, double minPrice, int id, String painterName, String color) {
        return new Painting.Builder().setName(name)
                .setMinPrice(minPrice)
                .setYear(year)
                .setColor(Colors.valueOf(color))
                .setPainterName(painterName)
                .setId(id)
                .build();
    }

    /**
     * Create a new furniture.
     *
     * @param name     the furniture's name
     * @param year     the furniture's year of manufacture
     * @param minPrice the furniture's minimum selling price
     * @param id       the furniture's id
     * @param type     the furniture's type
     * @param material the furniture's material
     * @return the new furniture
     */
    private Furniture createFurniture(String name, int year, double minPrice, int id, String type, String material) {
        return new Furniture.Builder().setName(name)
                .setMinPrice(minPrice)
                .setYear(year)
                .setType(type)
                .setMaterial(material)
                .setId(id)
                .build();
    }

    /**
     * Create a new jewelry.
     *
     * @param name     the jewelry's name
     * @param year     the jewelry's year of manufacture
     * @param minPrice the jewelry's minimum selling price
     * @param id       the jewelry's id
     * @param material the jewelry's material
     * @param gemstone whether the jewelry has or has not a gemstone on it
     * @return the new jewelry
     */
    private Jewelry createJewelry(String name, int year, double minPrice, int id, String material,
                                  String gemstone) {
        return new Jewelry.Builder().setName(name)
                .setMinPrice(minPrice)
                .setYear(year)
                .setMaterial(material)
                .setGemstone(Boolean.parseBoolean(gemstone))
                .setId(id)
                .build();
    }
}
