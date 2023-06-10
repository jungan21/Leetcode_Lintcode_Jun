package company.walmart.design;

interface Coffee{

    public double getCost();
    public String getIngredients();
}

class CoffeeDecorator implements Coffee {

    protected final Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public double getCost() {
        return coffee.getCost();
    }

    public String getIngredients() {
        return coffee.getIngredients();
    }
}

class WithMilk extends CoffeeDecorator {

    public WithMilk(Coffee coffee) {
        super(coffee);
    }

    public double getCost() {
        return super.getCost() + 0.5;
    }

    public String getIngredients() {
        return super.getIngredients() + ", Milk";
    }
}

class WithSugar extends CoffeeDecorator {

    public WithSugar(Coffee coffee) {
        super(coffee);
    }


    public double getCost() {
        return super.getCost() + 0.5;
    }


    public String getIngredients() {
        return super.getIngredients() + ", Sugar";
    }
}
public class CoffeeDecoratorPattern {
}
