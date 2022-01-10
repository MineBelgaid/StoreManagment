public class Produit {
    String name;
    int reference;
    String description;
    double prix;
    Category category;

    public Category getCat() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReference() {
        return this.reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return this.prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ", reference='" + getReference() + "'" +
                ", description='" + getDescription() + "'" +
                ", prix='" + getPrix() + "'" +
                ", category='" + getCategory() + "'" +
                "}";
    }

    private String getCategory() {
        return this.category.getType();
    }

    public Produit(String name, int reference, String description, double prix, Category category) {
        this.name = name;
        this.reference = reference;
        this.description = description;
        this.prix = prix;
        this.category = category;
    }
}

/**
 * Reference
 */
