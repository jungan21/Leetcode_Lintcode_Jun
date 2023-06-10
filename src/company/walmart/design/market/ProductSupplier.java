package company.walmart.design.market;

public class ProductSupplier implements Supplier {

    private final Market market;

    public ProductSupplier(Market market) {
        this.market = market;
    }

    @Override
    public void supplyProducts(Product product) {

        Product purchase = new Product(product.getType(), product.getName(), 10, product.getPrice());
        market.purchase(purchase);

        System.out.println("\n--------------->");
        System.out.println("Supply " + purchase.toString() + " to Market.");
        System.out.println("--------------->");
        System.out.println(purchase.getName() + " in Market Storage: " + market.getStorage().get(purchase.getName()));
        System.out.println("--------------->");

    }

}
