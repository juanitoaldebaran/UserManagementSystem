package User;

public abstract class User implements UserInterface {
    private int ID;
    private String name;
    private String email;
    private int age;
    private Role role;

    public User(int ID, String name, String email, int age, Role role) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.age = age;
        this.role = role;
    }

    public abstract void details();

    @Override
    public int getID() {
        return ID;
    }
    @Override
    public void setID(int ID) {
        this.ID = ID;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getEmail() {
        return email;
    }
    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public int getAge() {
        return age;
    }
    @Override
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public Role getRole() {
        return role;
    }
    @Override
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", role=" + role +
                '}';
    }
}
