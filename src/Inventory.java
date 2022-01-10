import java.util.*;
public class Inventory {
    Collection<Category> categories;
    Map<Produit, Integer> productInt;
    Map<Integer, Produit> intProduct;

    Category cat1 = new Category("Informatique & Mobiles");
    Category cat2 = new Category("Electronique & Electromenager");
    Category cat3 = new Category("Kits Solairs");
    Category catReserver = new Category("Reserved");
    public Inventory() {
        this.categories = new Vector<Category>();
        this.productInt = new HashMap<Produit,Integer>();
        this.intProduct = new HashMap<Integer,Produit>();
    }

    public void addItem(Produit produit, int x) {
        
        produit.getCat().add(produit, x);
        productInt.put(produit, produit.reference);
    }
    public void removeItem(Produit produit, int x) {
        produit.getCat().remove(produit, x);
        productInt.remove(produit);
    }
    public void addItem(int reference, int x) {
        Produit produit = intProduct.get(reference);
        produit.getCat().add(produit, x);
        intProduct.put(reference, produit);
    }
    public void removeItem(int reference, int x) {
        Produit produit = intProduct.get(reference);
        produit.getCat().remove(produit, x);
        intProduct.put(reference, produit);
    }

    public int getQuantity(Produit produit) {
        
        return produit.category.getQuantity(produit);
    }

    public void reserveItem(Produit produit, int x) {
        removeItem(produit, x);
        catReserver.add(produit, x);
    }

    public static void main(String[] args) {
        Inventory inv = new Inventory();
        Produit telephone = new Produit("Iphone", 4521, "Telephone mobile", 13000, inv.cat2);
        Produit telephone2 = new Produit("galaxy", 5412, "Telephone mobile", 8000, inv.cat2);
        inv.addItem(telephone, 3);
        inv.addItem(telephone, 2);
        inv.removeItem(telephone, 4);
        inv.addItem(telephone2, 5);
        inv.reserveItem(telephone2, 2);
        System.out.println("Items : "+inv.cat2.toString());
        System.out.println("Reserved items : " + inv.catReserver.toString());
        Client client1 = new Client("Amine", "Belgaid", "bab", "12345");
        client1.Bought(telephone, 3);
        System.out.println("Number of products :"+ client1.getNumber(telephone.category));
        client1.Bought(telephone, 2);
        System.out.println("Number of products in the 2nd time  :"+ client1.getNumber(telephone.category));
        
    }

        
    
    
}
