package ti.model;

import java.io.Serializable;
import java.nio.file.attribute.FileAttributeView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

// 1 - zalogowany/standardowy user
// 2 - administrator

@Entity
@Table( name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "role")
    private Integer role;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Favorites> favorites = new HashSet<Favorites>();

    public User() {
        this.enabled = true;
        this.role = -1;
    }
    public int getId(){
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Set<Favorites> getFavorites() {
       return favorites;
    }

    public void setFavorites(Set<Favorites> favorites) {
       this.favorites = favorites;
    }

    public String toString(){




        return  "username: " + username + "\n" +
                "password: " + password + "\n" +
                "email: " + email + "\n" +
                "enabled: " + enabled+ "\n" +
                "role: " + role + "\n"

                ;
    }
}
