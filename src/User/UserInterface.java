package User;

public interface UserInterface {
    int getID();
    void setID(int ID);
    String getName();
    void setName(String name);
    String getEmail();
    void setEmail(String email);
    int getAge();
    void setAge(int age);
    Role getRole();
    void setRole(Role role);
}
