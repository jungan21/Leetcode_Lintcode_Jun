package company.wayfair.design.ordertracking;

public enum OrderStatus {
    Received("Received"),
    Assigned("Assigned"),
    Delivered("Delivered"),
    PickedUp("PickedUp");

    public String description;

    OrderStatus(String description) {
        this.description = description;
    }
}
