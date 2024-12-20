// Creating enums for pizza attributes

import java.util.ArrayList;
import java.util.List;

enum Size {
    SMALL, MEDIUM, LARGE
}

enum Crust {
    THIN, THICK
}

enum Sauce {
    TOMATO, BBQ
}

enum Topping {
    JALAPENO, OLIVES, PINEAPPLE, MUSHROOMS
}

class Pizza {
    private Size size;
    private Crust crust;
    private Sauce sauce;
    private List<Topping> toppings;

    public Pizza() {
        this.toppings = new ArrayList<>();
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Crust getCrust() {
        return crust;
    }

    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    public Sauce getSauce() {
        return sauce;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    @Override
    public String toString() {
        return "Pizza(size=" + size +
                ", crust=" + crust +
                ", sauce=" + sauce +
                ", toppings=" + toppings + ")";
    }
}