package users;

import users.model.EncryptAndDecryptSHA1;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import users.model.IUserDAO;
import users.model.UsersManagerDAOMemory;

/**
 * @author Institut Provençana, 2022.
 */
@WebServlet(name = "UserController", urlPatterns = {"/user"})
public class UserController extends HttpServlet {

   
    /**
     * Interfaz Manager dels usuaris de la app.
     */
    private IUserDAO usersManager;
    /**
     * Classe utilitat per encriptar els usuaris de la app.
     */
    private EncryptAndDecryptSHA1 encripterService;
        
    /**
     * Número d'intents de login.
     */
    private int intentsLogin;
    /**
     * Número d'intents de login màxims, configurable i fixe.
     */
    private final int MAXIM_INTENTS_LOGIN = 10;
    
    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        // Inyectamos el DAO de la fuente que preferimos.
        String path = getServletContext().getRealPath("/WEB-INF");
        // usersManager = new UserManagerDAOJSON(path);
        usersManager = new UsersManagerDAOMemory();
        encripterService = new EncryptAndDecryptSHA1();
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(request.getParameter("action")!=null){
            String action=request.getParameter("action");
            switch(action){
                case "Validate":
                    login(request,response);
                break;
//                case "ValidateCookie":
//                    ValidateCookie(request,response);
//                break;
                case "AdminPage":
                    listUsersAsAdmin(request,response);
                break;
                case "Invalidate":
                    logout(request,response);
                break;
            }  
        } else{
            closeUserSession(response, request);
            response.sendRedirect("login.jsp?error=1");
        }
        
        
    }
    
    private void listUsersAsAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Agafem les dades de la sessió.
        HttpSession session=request.getSession();
        if(session.getAttribute("user")==null){
            closeUserSession(response, request);
            response.sendRedirect("login.jsp?error=1");
         } else {
             if(!session.getAttribute("role").equals("ADMIN")){
                 closeUserSession(response, request);
                 response.sendRedirect("login.jsp?error=1");
             } else {
                session.setAttribute("usersList", usersManager.listAllUsers());
                response.sendRedirect("./intranet/admin/usersList.jsp");
            }
        }
    }
        
    private void login(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        // Si l'usuari no ha excedit el límit d'intents de login.
        if(intentsLogin > MAXIM_INTENTS_LOGIN){
            String errorMessage = "Error: Number of Login Attempts Exceeded";
            request.setAttribute("errorMessage", errorMessage);
            response.sendRedirect("login.jsp?error=1");
        } else {
           // Agafem les dades del formulari.
           String username=request.getParameter("username");
           String password=request.getParameter("password");    
           // Si l'usuari amb contrassenya existeix a la nostra base de dades.
           if(usersManager.login(username,password)){
               try {
                   // Creem una nova variable de sessió
                   // Per prevenir el robatori de sessió o hickjacking: hijacking
                   // Consultat de: https://www.youtube.com/watch?v=IZ-lnQ4G_uI
                   HttpSession session=request.getSession();
                   session.setAttribute("user", username);
                   // Setting session to expiry in 30 mins
                   session.setMaxInactiveInterval(30*60);
                   
                   // Afegim una cookie per registrar a l'usuari.
                   Cookie userName = new Cookie("user", username);
                   // TODO.
                   // Encriptar el contenido de la Cookie:
                   // nombre encriptado (ideal algoritmo SHA-1 o similar)
                   String usernameCookie = encripterService.encrypt(username, "claveprivada");
                   userName.setMaxAge(30*60);
                   response.addCookie(userName);
                   // Track login attempts (combats: brute force attacks)
                   intentsLogin++;
                   session.setAttribute("intentsLogin", intentsLogin);
                   // Indiquem a la vista si l'usuari es admin o user.
                   session.setAttribute("role", usersManager.getRole(username));
                   response.sendRedirect("./intranet/adn.jsp");
               } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException ex) {
                   Logger.getLogger("").log(Level.SEVERE, null, ex);
               }
           } else {
            // Si l'usuari amb contrassenya no existeix a la nostra base de dades, se l'redirigeix a la pantalla de login.
            closeUserSession(response, request);
            response.sendRedirect("login.jsp?error=1");
           }
        }
    }
    
//    private void ValidateCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Cookie[] cookies = request.getCookies();
//    }
    
    private void logout(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        response.setContentType("text/html");
        closeUserSession(response, request);
    	  // System.out.println("User="+session.getAttribute("user"));
    	  /* Codi redundant.
        if(session != null){
            session.removeAttribute("user");
            session.removeAttribute("role");
            session.invalidate();
    	   }
        */
        response.sendRedirect("login.jsp");
    }

    private void closeUserSession(HttpServletResponse response, HttpServletRequest request) {
        // https://kodejava.org/how-do-i-delete-a-cookie-in-servlet/
        Cookie cookieUser = new Cookie("user", "");
        cookieUser.setMaxAge(0);
        response.addCookie(cookieUser);
        Cookie cookieJSESSIONID = new Cookie("JSESSIONID","");
        cookieJSESSIONID.setMaxAge(0);
        response.addCookie(cookieJSESSIONID);
        // Invalidate the session if exists
        HttpSession session = request.getSession();
        // System.out.println("User="+session.getAttribute("user"));
        session.invalidate();
    }
             
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    

}
