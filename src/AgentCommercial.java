
public class AgentCommercial {
    String name;
    private String password;
    public AgentCommercial(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public boolean checkPassword(String password) {
        if (this.password.equals(password)) {
            return true;
        }
        return false;
    }
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
