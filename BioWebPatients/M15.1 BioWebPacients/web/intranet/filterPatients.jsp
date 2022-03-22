<%@page import="patients.model.IPatientsDAO" %>
<%@page import="patients.model.PatientsMemoryDAO" %>
<%@page import="patients.model.Patient" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BIOINFORMATICS FILTER PATIENTS</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/vendors/bootstrap-4.1.3-dist/css/bootstrap.min.css">
        <script src="<%= request.getContextPath() %>/vendors/jquery/jquery-3.3.1.min.js"></script>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
    </head>
    <body>
        <header>
            <%@include file="../templates/menu.jsp" %>
            <%@include file="./userValidation.jsp" %>
            <% List<Patient> patientsList = new ArrayList<>();
               if(request.getAttribute("patientsList")!=null) {
                     patientsList = (List<Patient>) request.getAttribute("patientsList"); 
               } 
             %>
        </header>
        <main>
            <div class="container mw-100"> 
                <h3>FILTER Patients</h3>
                    <!-- Aquí irá el formulario de filtro -->
                    <form class="form-block" id="filter_form" 
                          method="post" action="<%= request.getContextPath() %>/patient">
                        <div class="form-group row">
                            <label class="input-group-prepend col-sm-2" for="classfication_filter">
                                RH Filter
                            </label>
                            <select class="form-control custom-select col-sm-3" name="rh_form" id="rh_form">
                                <option value="*">---</option>
                                <option value="+">RH+</option>
                                <option value="-">RH-</option>
                            </select>
                        </div>
                        <div class="form-group row">
                            <label class="input-group-prepend col-sm-2" for="bloodType_form">
                                Blood Type Filter
                            </label>
                            <select class="form-control custom-select col-sm-3" name="bloodType_form" id="bloodType_form">
                                <option value="*">---</option>
                                <option value="A">A</option>
                                <option value="B">B</option>
                                <option value="AB">AB</option>
                                <option value="O">O</option>
                            </select>
                        </div>
                        <button type="submit" form="filter_form" class="btn btn-primary col-sm-2" 
                            name="action" value="Filter">Filter</button>
                    </form>
                        <ul>
                        <% 
                            for(Patient pa: patientsList) {
                        %>
                            <li><%=pa.toString()%></li>
                        <% 
                            }
                        %>
                        </ul>
                        <% if(patientsList.size()>0) { %>
                            <p><%=patientsList.size()%> patients found.</p>
                        <% } else {%>
                            <p class="error">No patients found.</p>
                        <% } %>
            </div>
        </main>
        <footer>
            <%@include file="../templates/footer.jsp" %>
        </footer>
    </body>
</html>
