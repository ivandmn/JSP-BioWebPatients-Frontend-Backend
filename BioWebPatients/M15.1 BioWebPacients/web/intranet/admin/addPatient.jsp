<%@page import="adnutils.*" %>
<%@page import="patients.model.IPatientsDAO" %>
<%@page import="patients.model.PatientsMemoryDAO" %>
<%@page import="patients.model.Patient" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BIOINFORMATICS ADD PATIENT</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/vendors/bootstrap-4.1.3-dist/css/bootstrap.min.css">
        <script src="<%= request.getContextPath() %>/vendors/jquery/jquery-3.3.1.min.js"></script>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
    </head>
    <body>
        <header>
            <%@include file="../../templates/menu.jsp" %>
        </header>
        <main>
            <div class="container mw-100">
                <h3>Add Patient</h3>
                <form method="post" action="addPatient.jsp">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for='name'>Name (*)  </label>
                            <input class="form-control" type="text" required id='name' name="name" 
                                placeholder='Type Name'></input>
                        </div>
                        <div class="form-group col-md-6">
                            <label for='surname'>Surname (*) </label>
                            <input class="form-control" type="text" required id='surname' name="surname"
                                placeholder='Type Surname'></input>
                        </div>
                        <div class="form-group col-md-6">
                            <label for='height'>Height in cm (*) </label>
                            <input class="form-control" type="number" required id='height' name="height" 
                                placeholder='175'></input>
                        </div>
                        <div class="form-group col-md-6">
                            <label for='gender'>Gender (*) </label>
                            <select class="form-control" id="gender" name="gender">
                                <option value="gen-wom">Famale</option>
                                <option value="gen-man">Male</option>
                                <option value="gen-oth">Other</option>
                            </select>
                        </div>
                        <div class="form-group col-md-6">
                            <label for='age'>Age (*) </label>
                            <input class="form-control" type="number" required id='age' name="age" 
                                placeholder='18'></input>
                        </div>
                        <div class="form-group col-md-6">
                            <label for='age'>Weight in kgs (*) </label>
                            <input class="form-control" type="number" required id='weight' name="weight" 
                                placeholder='60'></input>
                        </div>
                        <div class="form-group col-md-6">
                            <label for='age'>Blood Type (*) </label>
                            <select class="form-control" id="bloodType" name="bloodType">
                                <option value="A+">A+</option>
                                <option value="A-">A-</option>
                                <option value="B+">B+</option>
                                <option value="B-">B-</option>
                                <option value="AB+">AB+</option>
                                <option value="AB-">AB-</option>
                                <option value="0+">0+</option>
                                <option value="0+">0-</option>
                            </select>
                        </div>
                        <div class="form-group col-md-6"></div>
                        <div class="form-group col-md-6">
                            <input class="btn btn-primary" type="submit" name="ok" value="Insertar"/>
                            <input class="btn btn-danger" type="reset" name="reset" value="Borrar"/>
                        </div>
                    </div>
                </form> 
            </div>
            <%
                // Si ha clickado el botón
                boolean resultOK = false;
                //Campos de los imputs
                String name = "";
                String surname = "";
                String heightField = "";
                int height = 0;
                String weightField = "";
                int weight = 0;
                String gender = "";
                String ageField = "";
                int age = 0;
                String bloodType = "";
                Validation validator = new Validation(); 
                if(request.getParameter("ok")!=null) {
                    // Obtenemos todos los parametros que ha introducido el usuario
                    name = request.getParameter("name");
                    surname = request.getParameter("surname");
                    heightField = request.getParameter("height");
                    weightField = request.getParameter("weight");
                    gender = request.getParameter("gender");
                    ageField = request.getParameter("age");
                    bloodType = request.getParameter("bloodType");
                    // Validamos los campos.
                    resultOK = name != null && surname != null && ageField != null && heightField != null && weightField != null;
                   
                    height = validator.validInteger(heightField);
                    weight = validator.validInteger(weightField);
                    age = validator.validInteger(ageField);
                    
                    resultOK = resultOK && height > 40 && height < 300 && age > 1 && weight > 1;
                    
                    //Si la validacion es correcta
                    if(resultOK) {
                        // Printmos un mensaje de bienvenida
                        out.println("<p class='bg-success'> Good day " + gender 
                                + " " + name + 
                                " " + surname + "</p>" );

                        // String name, String surnames, String gender, String bloodType, int weight, int height
                        Patient patient = new Patient(name,surname,gender,bloodType,weight,height);
                        IPatientsDAO daoPatients = new PatientsMemoryDAO();
                        boolean insertIsOK = daoPatients.addPatient(patient);
                    // Si no imprimimos un mensaje de error
                    } else {
                        out.println("<p class='error'>"
                                + "Format not correct, please check the inputs.</p>");
                    }
                } 
           %>
        </main>
        <footer>
            <%@include file="../../templates/footer.jsp" %>
        </footer>
    </body>
</html>
