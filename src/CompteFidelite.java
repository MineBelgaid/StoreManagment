import java.util.HashMap;
import java.util.Map;

/**
 * CompteFidelite
 */
public abstract class CompteFidelite {
    public String password;
    public String name;
    public String Lastname;
    protected String adress;

    public Map<Category, Integer> productsBought;
    public Map<Category, Integer> reductions;

    public CompteFidelite(String name, String LastName, String adress,String password) {
        this.password = password;
        this.name = name;
        this.adress = adress;
        this.password = password;
        productsBought = new HashMap<Category,Integer>();
        reductions = new HashMap<Category,Integer>();
    }

    public void Bought(Produit produit, int number) {
        // productsBought.put(produit.category, number);
        productsBought.merge(produit.category, number, (oldValue, newValue) -> oldValue + newValue);
    }
    public void Sold(Produit produit,int number) {
        productsBought.merge(produit.category, number, (oldValue, newValue) -> oldValue - newValue);
    }

    public int getNumber(Category cat){
        return productsBought.get(cat);
    }

}