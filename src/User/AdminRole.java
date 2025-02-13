package User;

public class AdminRole extends User{

    public AdminRole(int ID, String name, String email, int age) {
        super(ID, name, email, age, Role.ADMIN);
    }

    @Override
    public void details() {
        System.out.println("This is Admin");
    }


}
