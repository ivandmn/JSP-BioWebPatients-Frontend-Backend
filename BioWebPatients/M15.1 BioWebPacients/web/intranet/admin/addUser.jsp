<%@page import="adnutils.Validation"%>
<%@page import="java.util.*" %>
<%@page import="users.model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BIOINFORMATICS ADD USER</title>
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
                <h3>Add User</h3>
                <form method="post" action="addUser.jsp">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for='name'>Username (*)  </label>
                            <input class="form-control" type="text" required id='username' name="username" 
                                placeholder='Type Username'></input>
                        </div>
                        <div class="form-group col-md-6">
                            <label for='surname'>Password (*) </label>
                            <input class="form-control" type="text" required id='password' name="password"
                                placeholder='Type Password'></input>
                        </div>
                        <div class="form-group col-md-6">
                            <label for='surname'>Role (*) </label>
                            <input class="form-control" type="text" required id='role' name="role"
                                placeholder='ADMIN/USER'></input>
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
                boolean roleOK = false;
                //Campos de los imputs
                String username = "";
                String password = "";
                String role = "";
                Validation validator = new Validation(); 
                if(request.getParameter("ok")!=null) {
                    // Obtenemos todos los parametros que ha introducido el usuario
                    username = request.getParameter("username");
                    password = request.getParameter("password");
                    role = request.getParameter("role");
                    // Validamos los campos.
                    roleOK = role.equals("ADMIN") || role.equals("USER");
                    resultOK = username != null && password != null && role != null && roleOK;
                    //Si la validacion es correcta
                    if(resultOK) {
                        // String username,password, role
                        User user; 
                        if(role.equals("ADMIN")){
                        user = new User(username,password,Role.ADMIN);
                        IUserDAO daoUsers = new UsersManagerDAOMemory();
                        daoUsers.addUser(username,password,role,user);
                        // Printmos un mensaje de que el usuario se ha añadido correctamente
                        out.println("<p class='bg-success'> User " + username 
                                + " has been added " + "</p>" );
                        } else{
                        }
                        if(role.equals("USER")){
                        user = new User(username,password,Role.USER);
                        IUserDAO daoUsers = new UsersManagerDAOMemory();
                        daoUsers.addUser(username,password,role,user);
                        // Printmos un mensaje de que el usuario se ha añadido correctamente
                        out.println("<p class='bg-success'> User " + username 
                                + " has been added " + "</p>" );
                        }else{
                            
                        }
                        
                    // Si no imprimimos un mensaje de error
                    } else {
                        out.println("<p class='error'>"
                                + "User not added, format not correct, please check the inputs.</p>");
                    }
                } 
           %>
        </main>
        <footer>
            <%@include file="../../templates/footer.jsp" %>
        </footer>
    </body>
</html>