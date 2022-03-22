<%@page import="java.util.*" %>
<%@page import="users.model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%= request.getContextPath() %>/vendors/bootstrap-4.1.3-dist/css/bootstrap.min.css">
        <script src="<%= request.getContextPath() %>/vendors/jquery/jquery-3.3.1.min.js"></script>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
        <title>BIOINFORMATICS USERS LIST</title>
    </head>
    <body>
        <%  
            // Miramos si el usuario es administrador
            if(session.getAttribute("user")==null){
                response.sendRedirect("login.jsp");
            } else {
                if(!session.getAttribute("role").equals("ADMIN")){
                    response.sendRedirect("login.jsp");
                }
            }
            // Leemos la lista de usuarios
            List<User> usersList = new ArrayList<User>();
            if(session.getAttribute("usersList")!=null) {
                IUserDAO daoUsers = new UsersManagerDAOMemory();
                usersList = daoUsers.listAllUsers();
            } 
         %>
         <header>
            <%@include file="../../templates/menu.jsp" %>
        </header>
        <main class="container">
            <h3>Users List</h3>
            <table class="table table-striped">    
                <thead>
                    <th scope='col'>Username</th>
                    <th scope='col'>Password</th>
                    <th scope='col'>Role</th>
                </thead>
            <%
                for(User user: usersList) {
            %>        
            <tr>
                    <td scope="row">
                        <%=user.getUsername()%>
                    </td>
                    <td scope="row">
                        <%=user.getPassword()%>
                    </td>
                    <td scope="row">
                        <%=user.getRole()%>
                    </td>
               </tr>
            <%
              }
            %>
            </table>
        </main>
    </body>
    <footer>
            <%@include file="../../templates/footer.jsp" %>
    </footer>
</html>
