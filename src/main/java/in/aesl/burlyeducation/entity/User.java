package in.aesl.burlyeducation.entity;
import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;


    @Column(name = "username")
    private String uname;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String srole;



   

    public User() {

    }

    public User(String firstName, String lastName, String email,String username,String roles) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.uname=username;
        this.srole=roles;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
   
    public String getSname() {
        return uname;
    }

    public void setSname(String sname) {
        this.uname = sname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSrole() {
        return srole;
    }

    public void setSrole(String srole) {
        this.srole = srole;
    }
}
