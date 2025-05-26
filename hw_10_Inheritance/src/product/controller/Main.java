package product.controller;

import product.model.Food;
import product.model.MeatFood;
import product.model.MilkFood;
import product.model.Product;

public class Main {
    public static void main(String[] args) {
        Product[] shop = new Product[5];

        shop[0] = new Product(1000_2000_3000L, "Rope", 5.5);
        shop[1] = new Product(1001_2001_3001L, "Soap", 3.25);
        shop[2] = new Food(1002_2003_3003L, "Chips", 4.0, true, "26.06.2026");
        shop[3] = new MeatFood(1003_2003_3003L, "Ham", 30, false,"30.05.2025", "pork");
        shop[4] = new MilkFood(1004_2004_3004L, "Cheese", 25, true,"15.06.2025", "cow milk", 20);

        allProductsInfo(shop);
        kosherProductsInfo(shop);
        notFoodProductsInfo(shop);
        showTotalCost(shop);
    }

    public static void allProductsInfo(Product[] shop){
        for(Product product : shop){
            if (product == null)
                continue;
            System.out.println(product);
        }
        System.out.println("=".repeat(100));
    }

    private static void kosherProductsInfo(Product[] shop) {
        for(Product product : shop){
            if (product == null)
                continue;
            if (product instanceof Food food && food.isKosher())
                    System.out.println(food);
        }
        System.out.println("=".repeat(100));
    }

    private static void notFoodProductsInfo(Product[] shop) {
        for(Product product : shop){
            if (product == null)
                continue;
            if (!(product instanceof Food))
                System.out.println(product);
        }
        System.out.println("=".repeat(100));
    }

    private static void showTotalCost(Product[] shop) {
        double allProductsPrice = 0;
        double kosherProductsPrice = 0;
        for(Product product : shop){
            if (product == null)
                continue;
            allProductsPrice += product.getPrice();
            if (product instanceof Food food && food.isKosher()){
                kosherProductsPrice += food.getPrice();
            }
        }
        System.out.println("Products total cost: " + allProductsPrice);
        System.out.println("Kosher products total cost: " + kosherProductsPrice);
        System.out.println("=".repeat(100));
    }
}