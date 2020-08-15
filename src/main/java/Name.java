import java.util.ArrayList;

public class Name {
    private String firstName;
    private String lastName;
    private ArrayList<String> middleNames ;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        middleNames = new ArrayList<>();
    }

}
