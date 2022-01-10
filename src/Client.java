import java.util.*;

public class Client extends CompteFidelite {

    public Client(String name, String LastName, String adress, String password) {
        super(name, LastName, adress, password);
    }

    public boolean checkPassword(String password) {
        if (this.password.equals(password)) {
            return true;
        }
        return false;
    }

    public void checkAccount() {
        for (Map.Entry<Category, Integer> Entry : productsBought.entrySet()) {
            System.out.println("Category : " + Entry.getKey().getType() + " | Items bought : " + Entry.getValue());
        }
    }
    
    public String getPassword() {
        return this.password;
    }

    public String getLastName() {
        return this.Lastname;
    }
    public String getAdress() {
        return this.adress;
    }
}
