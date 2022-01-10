import java.util.Scanner;
import java.util.*;

public class Showroom {
    static Database db = new Database();
    
    static public double calculatePrice() {

        System.out.println("Enter number of products : ");
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        double total = 0;
        int ref;
        Map<Produit, Integer> currentList = new HashMap<Produit, Integer>();
        for (int i = 0; i < num; i++) {
            System.out.print("Please enter reference number : ");
            ref = scan.nextInt();
            total += db.inv.intProduct.get(ref).prix;
            currentList.merge(db.inv.intProduct.get(ref), 1, (oldValue, newValue) -> oldValue + newValue);
        }
        System.out.print("Enter Client's name : ");
        String name = scan.next();
        db.loadDB();
        Client client = db.checkClient(name);
        System.out.print("Confirm the purshase? Y/N : ");
        char confirm = scan.next().charAt(0);
        if (confirm == 'Y' || confirm == 'y') {
            for (Produit produit : currentList.keySet()) {
                db.inv.removeItem(produit, currentList.get(produit));
                client.Bought(produit, currentList.get(produit));
            }
            db.addClient(client);
        }
        return total;
    }
    public static void main(String[] args) {
        // Produit telephone = new Produit("Iphone", 4521, "Telephone mobile", 13000, db.inv.cat1);
        // Produit telephone2 = new Produit("galaxy", 5412, "Telephone mobile", 8000, db.inv.cat1);
        // db.inv.addItem(telephone, 3);
        // db.inv.addItem(telephone, 2);
        // db.inv.removeItem(telephone, 4);
        // db.inv.addItem(telephone2, 5);
        // db.inv.reserveItem(telephone2, 2);
        // System.out.println("Items : " + db.inv.cat2.toString());
        // System.out.println("Reserved items : " + db.inv.catReserver.toString());
        // Client client1 = new Client("Amine", "Belgaid", "bab", "12345");
        // client1.Bought(telephone, 3);
        // System.out.println("Number of products :" + client1.getNumber(telephone.category));
        // client1.Bought(telephone, 2);
        // System.out.println("Number of products in the 2nd time  :" + client1.getNumber(telephone.category));
        // client1.Bought(telephone, 3);
        // db.addClient(client1);
        // client1.checkAccount();
        System.out.println("Before : ");
        db.printClients();
        System.out.println();
        db.loadDB();
        System.out.println("After : \n");
        db.printClients();
        System.out.println("Agents : ");
        db.printAgents();
        // db.addAgent(new AgentCommercial("Mine", "456"));
        db.updateDB();
        db.loadDB();
        int choice;
        do {

            System.out.println("Are you a ? ");
            System.out.println("1 - Agent.");
            System.out.println("2 - Client.");
            System.out.println("3 - exit.");
            System.out.println("Enter your choice ");
            Scanner scan = new Scanner(System.in);
            choice = scan.nextInt();
            int choice2;
            String name, password, message;
            Client client;
            boolean found;

            switch (choice) {
                case 1:
                    System.out.println("Enter your name : ");
                    name = scan.next();
                    System.out.println("Enter your passowrd : ");
                    password = scan.next();
                    System.out.println("name : " + name + " , passowrd : " + password + ".");
                    found = db.checkAgent(name, password);
                    message = found ? "Successfully Logged in" : "Invalid credentials.";
                    System.out.println(message);
                    if (found) {
                        do {
                            System.out.println("1-Calculate Price");
                            System.out.println("2-Return item");
                            System.out.println("3-Reserve Item");
                            System.out.println("4-check stock");
                            System.out.println("5-exit");
                            System.out.println("choose your option");
                            choice2 = scan.nextInt();
                            switch (choice2) {
                                case 1:
                                    calculatePrice();
                                    break;
                                case 4:
                                    db.inv.printStock();
                                    break;
                                default:
                                    break;
                            }
                        } while (choice2 != 4);
                    }
                    break;
                case 2:
                    int user;
                    do {
                        System.out.println("\n1 - New User");
                        System.out.println("2 - Existing user");
                        System.out.println("3 - exit");
                        System.out.println("Enter your choice");
                        user = scan.nextInt();
                        switch (user) {
                            case 1:
                                System.out.println("Enter your name : ");
                                name = scan.next();
                                System.out.println("Enter your lastName : ");
                                String lastName = scan.next();
                                System.out.println("Enter your Adress : ");
                                String adress = scan.next();
                                System.out.println("Enter your passowrd : ");
                                password = scan.next();
                                System.out.println("Before\n");
                                db.printClients();
                                db.addClient(new Client(name, lastName, adress, password));
                                System.out.println("after\n");
                                db.printClients();

                                break;
                            case 2:
                                db.loadDB();
                                System.out.println("Enter your name : ");
                                name = scan.next();
                                System.out.println("Enter your passowrd : ");

                                password = scan.next();
                                client = db.checkClient(name, password);
                                message = (client != null) ? "Successfully Logged in" : "Invalid credentials.";
                                System.out.println(message);
                                if (client != null) {
                                    do {

                                        System.out.println("Options :");
                                        System.out.println("1- check your account");
                                        System.out.println("3-  exit");
                                        choice2 = scan.nextInt();
                                        switch (choice2) {
                                            case 1:
                                                client.checkAccount();
                                                break;
                                            // case 2:
                                            //     double prive = inv.calculatePrice();
                                            //     System.out.println("Total =" + prive);
                                            //     break;
                                            default:
                                                break;
                                        }

                                    } while (choice2 != 3);
                                }
                                break;

                            default:
                                break;
                        }

                    } while (user != 3);
                    break;
                default:
                    break;
            }
        } while (choice != 3);

    }
}
