package company.wayfair.design.ordertracking;

public interface Observer<T> {
    public void onChange(T subject);
}