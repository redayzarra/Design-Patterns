// Creating enums for the different kinds of foods we want to offer
enum MainItem {
    BURGER,
    SANDWICH,
    SALAD,
}

enum SideItem {
    FRIES,
    ONION_RINGS,
    CHIPS,
}

enum Drink {
    SODA,
    JUICE,
    WATER,
}

enum Dessert {
    ICE_CREAM,
    PIE,
    COOKIES,
}

class Meal {
    // Define all the private variables
    private _mainItem: MainItem;
    private _sides: SideItem[];
    private _drink: Drink;
    private _dessert: Dessert;

    // Using the "get" keyword automatically uses the getter when calling this variable: Meal.mainItem
    public get mainItem(): MainItem {
        return this._mainItem;
    }
    // Using the "set" keyword automatically uses the setter when changing this variable
    public set mainItem(value: MainItem) {
        this._mainItem = value;
    }

    public get sides(): SideItem[] {
        return this._sides;
    }
    public set sides(value: SideItem[]) {
        this._sides = value;
    }

    public get drink(): Drink {
        return this._drink;
    }
    public set drink(value: Drink) {
        this._drink = value;
    }

    public get dessert(): Dessert {
        return this._dessert;
    }
    public set dessert(value: Dessert) {
        this._dessert = value;
    }
}

// Define an interface called builder which allows us to implement logic needed to build a meal
interface Builder {
    // We are returning "this" so we can do method chaining
    setMainItem(main: MainItem): this;
    addSide(side: SideItem | SideItem[]): this;
    setDrink(drink: Drink): this;
    setDessert(dessert: Dessert): this;
    build(): Meal;
}

class MealBuilder implements Builder {
    // Create a new meal 
    private meal: Meal = new Meal();

    setMainItem(item: MainItem): this {
        this.meal.mainItem = item;
        return this;
    }

    addSide(side: SideItem | SideItem[]): this {
        // If the side is a list of sides then we want to add all of them
        if (Array.isArray(side)) {
            this.meal.sides.push(...side) // Spread each side into the array
        } else {
            this.meal.sides.push(side) // Just one side
        }
        return this;
    }

    setDrink(drink: Drink): this {
        this.meal.drink = drink;
        return this;
    }

    setDessert(dessert: Dessert): this {
        this.meal.dessert = dessert;
        return this;
    }

    build(): Meal {
        // Store the current meal and then reset the one in this class
        const currentMeal = this.meal;
        this.meal = new Meal();

        // Return the current meal
        return currentMeal;
    }
}
