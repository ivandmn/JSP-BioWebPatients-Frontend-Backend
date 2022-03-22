<%@page import="patients.model.IPatientsDAO" %>
<%@page import="patients.model.PatientsMemoryDAO" %>
<%@page import="patients.model.Patient" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BIOINFORMATICS LIST PATIENTS</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/vendors/bootstrap-4.1.3-dist/css/bootstrap.min.css">
        <script src="<%= request.getContextPath() %>/vendors/jquery/jquery-3.3.1.min.js"></script>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
    </head>
    <body>
        <header>
            <%@include file="../templates/menu.jsp" %>
            <%@include file="./userValidation.jsp" %>
        </header>
        <main>
            <div class="container mw-100"> 
                    <h3>List Patients</h3>
                    <ul>
                    <% 
                        PatientsMemoryDAO daoPatients = new PatientsMemoryDAO();
                        List<Patient> resultList = daoPatients.listAllPatients();
                        for(Patient pa: resultList) {
                    %>
                    <li><%=pa.toString()%></li>
                    <% 
                        }
                    %>
                    </ul>
                    <p><%=resultList.size()%> patients found.</p>
                    <h4>List Woman Patients</h4>
                    <ol style="list-style-type:lower-alpha">
                    <% 
                        List<Patient> resultListWoman = daoPatients.listWomanPatients();
                        for(Patient paW: resultListWoman) {
                    %>
                    <li><%=paW.toString()%></li>
                    <% 
                        }
                    %>
                    </ol>
                    <p><%=resultListWoman.size()%> patients found.</p>
            </div>
        </main>
        <footer>
            <%@include file="../../templates/footer.jsp" %>
        </footer>
    </body>
</html>
