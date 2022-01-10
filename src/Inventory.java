import java.util.*;
public class Inventory {
    Map<Produit, Integer> productInt;
    Map<Integer, Produit> intProduct;
    // private Database db= new Database();
     Category cat1 = new Category("Informatique & Mobiles");
     Category cat2 = new Category("Electronique & Electromenager");
     Category cat3 = new Category("Kits Solairs");
     Category catReserver = new Category("Reserved");
    public Inventory() {
        this.productInt = new HashMap<Produit,Integer>();
        this.intProduct = new HashMap<Integer,Produit>();
        // db.loadDB();
    }
    
    public void addItem(Produit produit, int x) {
        
        produit.getCat().add(produit, x);
        productInt.put(produit, produit.reference);
        intProduct.put(produit.reference, produit);
        // System.out.println("Key = "+this.intProduct.containsKey(4521));
    }

    public void removeItem(Produit produit, int x) {
        if (produit.getCat().getQuantity(produit)<x) {
            System.out.println("Not enough products in storage");
            return;
        }
        produit.getCat().remove(produit, x);
        // intProduct.remove(produit.reference);
    }
    public void addItem(int reference, int x) {
        Produit produit = intProduct.get(reference);
        produit.getCat().add(produit, x);
        productInt.put(produit, reference);
        intProduct.put(reference, produit);
    }
    public void removeItem(int reference, int x) {
        Produit produit = intProduct.get(reference);
        if (produit.getCat().getQuantity(produit) < x) {
            System.out.println("Not enough products in storage");
            return;
        }
        produit.getCat().remove(produit, x);
        
    }
    
    public int getQuantity(Produit produit) {
        
        return produit.category.getQuantity(produit);
    }
    public int getQuantity(int reference) {
        Produit produit = intProduct.get(reference);
        return this.getQuantity(produit);
    }
    
    public void reserveItem(Produit produit, int x) {
        if (produit.getCat().getQuantity(produit) < x) {
            System.out.println("Not enough products in storage");
            return;
        }
        removeItem(produit, x);
        catReserver.add(produit, x);
    }
    public void reserveItem(int reference, int x) {
        Produit produit = intProduct.get(reference);
        if (produit.getCat().getQuantity(produit) < x) {
            System.out.println("Not enough products in storage");
            return;
        }
        removeItem(produit, x);
        catReserver.add(produit, x);
    }
    
    public void newItem(String name, int reference, String description, double prix, Category category, int quantity) {
        Produit produit = new Produit(name, reference, description, prix, category);
        if (productInt.containsKey(produit)) {
            System.out.println("Product already Exists in the inventory!");
        } else {
            this.addItem(produit, quantity);
            System.out.println("Succesfully added the product to the inventory");
        }
    }

    public void printStock() {
        System.out.println("Category : "+cat1.getType());
        for (Produit produit : cat1.products.keySet()) {
            System.out.println("Product : "+produit.name+" Quantity : "+cat1.products.get(produit));
        }
        System.out.println("Category : "+cat2.getType());
        for (Produit produit : cat2.products.keySet()) {
            System.out.println("Product : "+produit.name+" Quantity : "+cat1.products.get(produit));
        }
        System.out.println("Category : "+cat3.getType());
        for (Produit produit : cat3.products.keySet()) {
            System.out.println("Product : "+produit.name+" Quantity : "+cat1.products.get(produit));
        }
    }
    
    // public double calculatePrice() {
        
    //     System.out.println("Enter number of products : ");
    //     Scanner scan = new Scanner(System.in);
    //     int num = scan.nextInt();
    //     double total = 0;
    //     int ref;
    //     Map<Produit, Integer> currentList = new HashMap<Produit, Integer>();
    //     for (int i = 0; i < num; i++) {
    //         System.out.print("Please enter reference number : ");
    //         ref = scan.nextInt();
    //         total += intProduct.get(ref).prix;
    //         currentList.merge(intProduct.get(ref), 1,(oldValue,newValue)->oldValue+newValue);
    //     }
    //     System.out.print("Enter Client's name : ");
    //     String name = scan.next();
    //     db.loadDB();
    //     Client client = db.checkClient(name);
    //     System.out.print("Confirm the purshase? Y/N : ");
    //     char confirm = scan.next().charAt(0);
    //     if (confirm == 'Y' || confirm == 'y') {
    //         for (Produit produit : currentList.keySet()) {
    //             removeItem(produit, currentList.get(produit));
    //             client.Bought(produit, currentList.get(produit));
    //         }
    //         db.addClient(client);
    //     }
    //     return total;
    // }

        
    
    
}
