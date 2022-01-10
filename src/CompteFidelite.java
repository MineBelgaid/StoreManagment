import java.util.HashMap;
import java.util.Map;

/**
 * CompteFidelite
 */
public abstract class CompteFidelite {
    public String password;
    public String name;
    public String Lastname;
    public String adress;

    public Map<Category, Integer> productsBought;
    public Map<Category, Boolean> reductions;

    public CompteFidelite(String name, String LastName, String adress,String password) {
        this.password = password;
        this.name = name;
        this.Lastname = LastName;
        this.adress = adress;
        this.password = password;
        productsBought = new HashMap<Category,Integer>();
        reductions = new HashMap<Category,Boolean>();
    }

    public void Bought(Produit produit, int number) {
        // productsBought.put(produit.category, number);
        productsBought.merge(produit.category, number, (oldValue, newValue) -> oldValue + newValue);
        if (!reductions.containsKey(produit.category)) {
            
            reductions.put(produit.category, false);
        }
        if (productsBought.get(produit.category)>=5) {
            reductions.put(produit.category, true);
            
        }
    }
    public void Bought(Category category, int number) {
        // productsBought.put(produit.category, number);
        productsBought.merge(category, number, (oldValue, newValue) -> oldValue + newValue);
        if (!reductions.containsKey(category)) {
            
            reductions.put(category, false);
        }
        if (productsBought.get(category)>=5) {
            reductions.put(category, true);
        }
    }
    public void Sold(Produit produit,int number) {
        productsBought.merge(produit.category, number, (oldValue, newValue) -> oldValue - newValue);
    }
    public void Sold(Category category,int number) {
        productsBought.merge(category, number, (oldValue, newValue) -> oldValue - newValue);
    }

    public int getNumber(Category cat){
        return productsBought.get(cat);
    }

}