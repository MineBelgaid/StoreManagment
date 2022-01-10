import java.util.*;

public class Category {
    private String type;
    Map<Produit, Integer> products;

    public void add(Produit product, Integer x) {
        
        if (products.containsKey(product)) {
            
            products.put(product, products.get(product) + x);
        }
        else {
            
            products.put(product,x);
        }   
    }

    public void remove(Produit product, Integer x) {
        if (products.containsKey(product)) {

            products.put(product, products.get(product) - x);
        }
        else {

            products.put(product,x);
        }   
    }

    public Category(String type) {
        this.type = type;
        products = new HashMap<>();
    }

    public String toString() {
        StringBuilder string = new StringBuilder("{");
        for (Produit key : products.keySet()) {
            string.append(key.getName() + " = " + products.get(key) + ",");
        }
        string.append("}");
        return string.toString();
    }
    public void print() {
        System.out.println(products.toString());
    }

    

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getQuantity(Produit produit) {
        return products.get(produit);
    }
}
