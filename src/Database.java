import java.util.*;
import java.io.*;

public class Database {
    private Map<String, AgentCommercial> agents = new HashMap<String, AgentCommercial>();
    private Map<String, Client> clients = new HashMap<String, Client>();
    private Map<Produit, Integer> products = new HashMap<Produit, Integer>();
    public Inventory inv = new Inventory();
    public static String outputFilePath = "A:/Projects/Project OOP/Poo Projet/data/agents.txt";
    public static String outputFilePath2 = "A:/Projects/Project OOP/Poo Projet/data/clients.txt";
    public static String outputFilePath3 = "A:/Projects/Project OOP/Poo Projet/data/products.txt";

    public void updateDB() {
        File file = new File(outputFilePath);
        BufferedWriter bf = null;
        try {
            bf = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<String, AgentCommercial> entry : agents.entrySet()) {
                bf.write(entry.getKey() + ":" + entry.getValue().name + "," + entry.getValue().getPassword());
                bf.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                bf.close();
            } catch (Exception e) {
            }
        }
        File file3 = new File(outputFilePath3);
        BufferedWriter bf3 = null;
        try {
            bf3 = new BufferedWriter(new FileWriter(file3));
            for (Produit produit : inv.cat1.products.keySet()) {
                products.merge(produit, inv.cat1.getQuantity(produit), (a, b) -> a + b);
            }
            for (Produit produit : inv.cat2.products.keySet()) {
                products.merge(produit, inv.cat2.getQuantity(produit), (a, b) -> a + b);
            }
            for (Produit produit : inv.cat3.products.keySet()) {
                products.merge(produit, inv.cat3.getQuantity(produit), (a, b) -> a + b);
            }
            for (Map.Entry<Produit, Integer> entry : products.entrySet()) {
                Produit produit = entry.getKey();
                bf3.write(produit.getName() + "," + produit.getCat().getType() + "," + produit.getReference() + ","
                        + produit.getDescription() + "," + produit.getPrix() + ":" + products.get(produit));
                bf3.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                bf3.close();
            } catch (Exception e) {
            }
        }
        File file2 = new File(outputFilePath2);
        BufferedWriter bf2 = null;
        try {
            bf2 = new BufferedWriter(new FileWriter(file2));
            for (Map.Entry<String, Client> entry : clients.entrySet()) {
                // System.out.println(
                // entry.getKey() + ":" + entry.getValue().name + "," +
                // entry.getValue().getPassword() + ","
                // + entry.getValue().getLastName() + "," + entry.getValue().getAdress());
                Client client = entry.getValue();
                String dbString = "";
                for (Category category : client.productsBought.keySet()) {
                    String cat = "";
                    if (category.getType().equals("Informatique & Mobiles")) {

                        cat = "first" + ":" + Integer.toString(client.getNumber(category));
                    } else if (category.getType().equals("Electronique & Electromenager")) {

                        cat = "second" + ":" + Integer.toString(client.getNumber(category));
                    } else if (category.getType().equals("Kits Solairs")) {
                        cat = "third" + ":" + Integer.toString(client.getNumber(category));
                    }
                    dbString = String.join("", dbString, cat, ",");
                }
                if (dbString.charAt(dbString.length() - 1) == ',') {
                    dbString = dbString.substring(0, dbString.length() - 1);
                }
                bf2.write(entry.getKey() + ":" + entry.getValue().name + "," + entry.getValue().getPassword() + ","
                        + entry.getValue().getLastName() + "," + entry.getValue().getAdress() + "@" + dbString);
                bf2.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                bf2.close();
            } catch (Exception e) {
            }
        }

    }

    public void loadDB() {
        BufferedReader br = null;

        try {

            // create file object
            File file = new File(outputFilePath);

            // create BufferedReader object from the File
            br = new BufferedReader(new FileReader(file));

            String line = null;

            // read file line by line
            while ((line = br.readLine()) != null) {

                // split the line by :
                String[] parts = line.split(":");

                String name = parts[0].trim();
                String[] second = parts[1].trim().split(",");
                String nameAgent = second[0].trim();
                String password = second[1].trim();

                if (!name.equals("") && !nameAgent.equals("") && !password.equals(""))
                    agents.put(name, new AgentCommercial(nameAgent, password));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                }

            }
        }
        BufferedReader br3 = null;

        try {

            File file = new File(outputFilePath3);

            br3 = new BufferedReader(new FileReader(file));

            String line = null;

            while ((line = br3.readLine()) != null) {

                String[] parts = line.split(":");

                int quantity = Integer.parseInt(parts[1].trim());
                String[] second = parts[0].trim().split(",");
                Category cat = null;
                if (second[1].trim().equals(inv.cat1.getType())) {
                    cat = inv.cat1;
                }
                if (second[1].trim().equals(inv.cat2.getType())) {
                    cat = inv.cat2;
                }
                if (second[1].trim().equals(inv.cat3.getType())) {
                    cat = inv.cat3;
                }
                Produit product =new Produit(second[0].trim(), Integer.parseInt(second[2].trim()),
                        second[2].trim(), Double.parseDouble(second[3].trim()), cat);
                products.put(product, quantity);
                inv.addItem(product, quantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (br3 != null) {
                try {
                    br3.close();
                } catch (Exception e) {
                }

            }
        }
        BufferedReader br2 = null;

        try {

            // create file object
            File file = new File(outputFilePath2);

            // create BufferedReader object from the File
            br2 = new BufferedReader(new FileReader(file));

            String line = null;

            // read file line by line
            while ((line = br2.readLine()) != null) {

                // split the line by :
                String[] first = line.split("@");

                String[] parts = first[0].trim().split(":");
                String name = parts[0].trim();
                String[] second = parts[1].trim().split(",");
                String nameAgent = second[0].trim();
                String password = second[1].trim();
                String lastName = second[2].trim();
                String adress = second[3].trim();
                // System.out.println("name : "+nameAgent+" LastName: "+ lastName+" Adress :
                // "+adress + "Password"+password);
                // not empty
                if (!name.equals("") && !nameAgent.equals("") && !password.equals("")) {

                    Client newClient = new Client(name, lastName, adress, password);
                    newClient.Lastname = lastName;
                    if (line.charAt(line.length() - 1) != '@') {

                        String[] categories = first[1].split(",");
                    
                        for (String string : categories) {

                            String[] subCat = string.split(":");
                            if (subCat[0].equals("first")) {
                                newClient.Bought(inv.cat1, Integer.parseInt(subCat[1].trim()));
                            } else if (subCat[0].equals("second")) {
                                newClient.Bought(inv.cat2, Integer.parseInt(subCat[1].trim()));
                            } else if (subCat[0].equals("third")) {
                                newClient.Bought(inv.cat3, Integer.parseInt(subCat[1].trim()));
                            }
                        }
                    }
                    // System.out.println("namess = " + newClient.name + ", Lastname : "
                    // + newClient.getLastName() + ", Adress : " + newClient.getAdress());
                    clients.put(name,
                            newClient);
                    // System.out.println("name = " + clients.get(newClient.name).name + ", Lastname
                    // : "
                    // + clients.get(newClient.name).getLastName() + ", Adress : " +
                    // clients.get(newClient.name).getAdress());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // Always close the BufferedReader
            if (br2 != null) {
                try {
                    br2.close();
                } catch (Exception e) {
                }

            }
        }
    }

    public boolean checkAgent(String name, String password) {
        if (agents.containsKey(name)) {
            AgentCommercial agent = agents.get(name);

            if (agent.checkPassword(password)) {
                return true;
            }
        }
        return false;
    }

    public Client checkClient(String name) {
        Client client = null;
        if (clients.containsKey(name)) {

            client = clients.get(name);

            return client;
        }
        return null;
    }

    public Client checkClient(String name, String password) {
        Client client = null;
        if (clients.containsKey(name)) {

            client = clients.get(name);

            if (client.checkPassword(password)) {
                return client;
            }
        }
        return null;
    }

    public void addAgent(AgentCommercial agent) {
        agents.put(agent.name, agent);
        this.updateDB();

    }

    public void addClient(Client client) {
        clients.put(client.name, client);
        this.updateDB();

    }
    public void addProduct(Produit product,int number) {
        products.merge(product, number,(oldValue,newValue)->oldValue+newValue);
        this.updateDB();

    }

    public Client getClient(String name) {
        return clients.get(name);
    }

    public void printAgents() {
        for (String agent : agents.keySet()) {
            System.out.println("name = " + agents.get(agent).name);
        }
    }

    public void printClients() {
        for (String client : clients.keySet()) {
            System.out.println("name = " + clients.get(client).name + ", Lastname : "
                    + clients.get(client).getLastName() + ", Adress : " + clients.get(client).getAdress());
        }
    }
}
