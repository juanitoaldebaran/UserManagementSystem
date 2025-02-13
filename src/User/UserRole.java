package User;

public class UserRole extends User{

    public UserRole(int ID, String name, String email, int age) {
        super(ID, name, email, age, Role.USER);
    }

    @Override
    public void details() {
        System.out.println("This is User");
    }

}
