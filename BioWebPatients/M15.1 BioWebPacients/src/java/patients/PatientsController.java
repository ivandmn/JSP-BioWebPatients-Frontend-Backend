package patients;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import patients.model.IPatientsDAO;
import patients.model.Patient;
import patients.model.PatientsMemoryDAO;

/**
 *
 * @author mamorosal
 */
@WebServlet(name = "PatientsController", urlPatterns = {"/patient"})
public class PatientsController extends HttpServlet {

    /**
     * Llama a la clase Manager de los pacientes de la app.
     */
    IPatientsDAO patientsDAO;
    
    @Override
    public void init(ServletConfig config) throws ServletException{
        patientsDAO = new PatientsMemoryDAO();
        super.init(config);
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
                case "ListAll":
                    listAll(request,response);
                break;
                case "Filter":
                    filter(request,response);
                break;
            }  
        } else{
            response.sendRedirect("login.jsp");
        }
    }
    
    private void listAll(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Agafem les dades del formulari de filtre.
//        String username=request.getParameter("username");
//        String password=request.getParameter("password"); 

        // 1. Verifiquem la sessió.
        // Agafem les dades de la sessió.
        HttpSession session=request.getSession();
        if(session.getAttribute("user")==null){
            response.sendRedirect("login.jsp");
        } else {
            // 2. Obtenim la llista de pacients.
            PatientsMemoryDAO daoPatients = new PatientsMemoryDAO();
            List<Patient> resultList = daoPatients.listAllPatients();

            // 3. Enviem la llista resultant a la JSP 
            request.setAttribute("patientsList", resultList);
            RequestDispatcher rd = request.getRequestDispatcher("listAllPatients.jsp");
            rd.forward(request, response);
        }
    }

    private void filter(HttpServletRequest request, HttpServletResponse response) 
             throws ServletException, IOException {
        // 1. Verifiquem la sessió.
        // Agafem les dades de la sessió.
        HttpSession session=request.getSession();
        if(session.getAttribute("user")==null){
            response.sendRedirect("login.jsp");
        } else {
            // 2. Agafem les dades del filtre de pacients.
            String rh_form = request.getParameter("rh_form");
            String bloodType_form = request.getParameter("bloodType_form");
            
            // 2.2 Validem les dades del formulari. Si ens retornen valor nul 
            // (en el nostre cas un valor -)
            boolean RHSelected = !rh_form.equals("*");
            boolean BloodSelected = !bloodType_form.equals("*");   
            
            // 3. Cridem PatientsDAO per a què ens retorni la llista de pacients 
            // filtrada.
            PatientsMemoryDAO daoPatients = new PatientsMemoryDAO();
            List<Patient> resultList = new ArrayList<Patient>(); 
            
            if(RHSelected && BloodSelected) {
                resultList 
                    = daoPatients.listPatientsByBloodTypeAndRH
                        (bloodType_form,rh_form);
            }
            if(RHSelected && !BloodSelected) {
               resultList 
                    = daoPatients.listPatientsByRH(rh_form); 
            }
            if(!RHSelected && BloodSelected) {
                resultList 
                    = daoPatients.listPatientsByBloodType(bloodType_form);
            }
            if(!RHSelected && !BloodSelected) {
                resultList 
                    = daoPatients.listAllPatients();
            }
            // 4. Enviem la llista resultant a la JSP 
            request.setAttribute("patientsList", resultList);
            // ./intranet/filterPatients.jsp
            // response.sendRedirect("../intranet/filterPatients.jsp");
            // https://stackoverflow.com/questions/24905788/dispatch-request-to-jsp-page-in-specific-folder
            RequestDispatcher rd = request.getRequestDispatcher("./intranet/filterPatients.jsp");
            // no error rutas pero no envia nada.
            // response.sendRedirect("./intranet/filterPatients.jsp");
            // error rutas.
            rd.forward(request, response);
        }
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
