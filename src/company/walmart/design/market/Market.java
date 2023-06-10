package company.walmart.design.market;

import java.util.*;

public class Market implements Notifier {

    private final Map<ProductType, List<Product>> productMap;

    private final Map<String, Integer> storage;

    private final List<Supplier> suppliers;

    public Market() {
        this.productMap = new HashMap<>();
        this.storage = new HashMap<>();
        this.suppliers = new ArrayList<>();
    }

    public void init(List<Product> productList) {
        productList.forEach(p -> {
            addIfAbsent(p);
            storage.putIfAbsent(p.getName(), p.getCount());
        });
    }

    public void purchase(Product product) {

        String name = product.getName();
        Integer count = product.getCount();

        storage.put(name, storage.containsKey(name) ? (storage.get(name) + count) : count);
        if (nonExist(product)) {
            addIfAbsent(product);
        } else {
            addIfPresent(product);
        }

    }

    public void sale(List<Request> requests) {

        requests.forEach(this::sale);

    }

    public void sale(Request request) {

        Product product = getProduct(request);
        Integer pStorage = storage.get(request.getName());

        if (pStorage < request.getTotal()) {
            System.out.println("\n***************");
            System.out.println("Buy " + request.getName() + " Error: Out of stock, please reselect!");
            System.out.println("***************");
        } else {
            int totalPrice = product.getPrice() * request.getTotal();
            System.out.println("\n<---------------");
            System.out.println("Buy " + request.getTotal() + " " + request.getName() + ". Total price: " + totalPrice);
            System.out.println("<---------------");
            storage.put(request.getName(), storage.get(request.getName()) - request.getTotal());
            // 因为此时product的库存改变了 需要进行更新
            product.setCount(storage.get(product.getName()));
            addIfPresent(product);
        }
        if (needPurchase(product)) {
            notifySupplier(product);
        }

    }

    public Map<String, Integer> getStorage() {
        return storage;
    }

    public Map<ProductType, List<Product>> getProductMap() {
        return productMap;
    }

    private Product getProduct(Request request) {
        List<Product> productList = productMap.get(request.getType());
        return productList.stream()
                .filter(p -> p.getName().equals(request.getName()))
                .findFirst()
                .orElse(null);
    }

    private void resetProduct(Product product) {
        ProductType type = product.getType();
        List<Product> productList = productMap.get(type);
        productMap.put(type,
                productList.stream()
                        .peek(p -> {
                            if (p.getName().equals(product.getName())) {
                                p.setCount(product.getCount());
                            }
                        })
                        .collect(Collectors.toList()));
    }

    private void addIfAbsent(Product product) {

        ProductType type = product.getType();
        productMap.putIfAbsent(type, new ArrayList<>());
        List<Product> products = productMap.get(type);

        if (products.stream().noneMatch(p -> p.getName().equals(product.getName()) )) {
            productMap.get(type).add(product);
        }

    }

    private void addIfPresent(Product product) {

        ProductType type = product.getType();
        List<Product> products = productMap.get(type);

        for (Product p : products) {
            if (product.getName().equals(p.getName())) {
                p.setCount(storage.get(p.getName()));
                return;
            }
        }

    }

    private boolean nonExist(Product product) {
        ProductType type = product.getType();
        List<Product> products = productMap.get(type);

        return products.stream().noneMatch(p -> p.getName().equals(product.getName()));
    }

    private boolean needPurchase(Product product) {
        return storage.get(product.getName()) < 5;
    }

    @Override
    public void addSupplier(Supplier supplier) {
        this.suppliers.add(supplier);
    }

    @Override
    public void notifySupplier(Product product) {

        for (Supplier supplier : suppliers) {
            supplier.supplyProducts(product);
        }

    }

}
