package users.model;

/**
 *
 * @author alumne
 */
public class User {
    
    private String username;
    private String password;
    private Role role;

    /**
     * Crea un usuari sense rol.
     * @param u usuari
     * @param p contrassenya
     */
    public User (String u, String p) {
        username = u;
        password = p;
        role = Role.USER;
    }
    
    /**
     * Crea un usuari amb rol USER.
     * @param u usuari
     * @param p contrassenya
     * @param r Rol: USER o ADMIN
     */
    public User (String u, String p, Role r) {
        username = u;
        password = p;
        role = r;
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
    
    public Role getRole() {
        return role;
    }
    
}
