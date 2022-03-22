package users.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Modulo de autenticación de usuarios provisional.
 * @author mamorosal
 */
public class UsersManagerDAOMemory implements IUserDAO {
    
    private Map<String,User> users;
    
    public UsersManagerDAOMemory() {
        users = new HashMap<>();
        ArrayList<User> usersFile;
        usersFile = readUsersFromFile();
        for(int x = 0; x < usersFile.size(); x++){
            User userFile = usersFile.get(x);
            users.put(userFile.getUsername(), userFile);
        }  
    }  
    public ArrayList<User> readUsersFromFile(){
    ArrayList<User> usersList = new ArrayList<User>();
        try {
          //String path = System.getProperty("user.dir") + "/users.txt";
          File myObj = new File("/home/ivandmn/NetBeansProjects/M15.1 BioWebPacients/users.txt");
          Scanner myReader = new Scanner(myObj);
          //Quitamos la cabecera
          myReader.nextLine();
          while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] datosUsuario = data.split(";");
            if ("ADMIN".equals(datosUsuario[2])){
                User user = new User(datosUsuario[0], datosUsuario[1], Role.ADMIN);
                usersList.add(user);
            } else{}
            if ("USER".equals(datosUsuario[2])){
                User user = new User(datosUsuario[0], datosUsuario[1], Role.USER);
                usersList.add(user);
            } else{}
          }
          myReader.close();
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
        }
    return usersList;
    }
    
    @Override
    public void addUser(String username,String password,String role,User user){
        users.put(username,user);
        try {
            FileWriter myWriter = new FileWriter("/home/ivandmn/NetBeansProjects/M15.1 BioWebPacients/users.txt", true);
            BufferedWriter bw = new BufferedWriter(myWriter);
            String line = username + ";" + password + ";" + role + "\n";
            bw.write(line);
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
    
    @Override
    public String getRole(String username) {
        // Existe nombre de usuario ?
        boolean exists = users.containsKey(username);
        // Contraseña correcta ? 
        User dbUser = users.get(username);
        if(dbUser!=null) {
            Role role = dbUser.getRole();
            return role.toString();
        } else {
           return null; 
        }
    }
    
    @Override
    public ArrayList<User> listAllUsers() {
        // https://www.baeldung.com/java-convert-collection-arraylist
        Collection<User> colUsers = users.values();
        ArrayList<User> usersList = new ArrayList<>(colUsers);
        return usersList;
    }

    @Override
    public boolean login(String username, String password) {
        // Existe nombre de usuario ?
        boolean exists = users.containsKey(username);
        User dbUser;
        String pwForm = "";
        // Contraseña correcta ? 
        if(exists) {
            dbUser = users.get(username);
            pwForm = dbUser.getPassword();
        }
        return exists && password.equals(pwForm);
    }

    @Override
    public boolean logout(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
