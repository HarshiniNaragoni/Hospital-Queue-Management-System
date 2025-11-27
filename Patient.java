package myproject;
public class Patient {
    private int tokenNumber;
    private String name;

    public Patient(int tokenNumber, String name) {
        this.tokenNumber = tokenNumber;
        this.name = name;
    }

    public int getTokenNumber() {
        return tokenNumber;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Token: " + tokenNumber + " | Name: " + name;
    }
}
