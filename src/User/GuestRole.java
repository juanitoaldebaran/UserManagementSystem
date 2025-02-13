package User;

public class GuestRole extends User{
    public GuestRole(int ID, String name, String email, int age) {
        super(ID, name, email, age, Role.GUEST);
    }

    @Override
    public void details() {
        System.out.println("This is Guest");
    }

}
