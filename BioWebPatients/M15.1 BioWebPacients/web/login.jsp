<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Demo JSP Templates</title>
        <link rel="stylesheet" href="vendors/bootstrap-4.1.3-dist/css/bootstrap.min.css">
        <script src="vendors/jquery/jquery-3.3.1.min.js"></script>
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <header>
            <%@include file="templates/menu.jsp" %>
            <%@include file="userValidation.jsp" %>
        </header>
        <main class="container mw-100">
                <h3>Login</h3>
                <div class="form-row">
                    <form method="post" action="user">
                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <label>Username:</label> 
                            <input type="text" name="username"/>
                        </div>
                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <label>Password:</label>
                            <input type="password" name="password" />
                        </div>    
                        <div class="col-md-6 col-sm-12">
                            <button class="login-submit" 
                                 accesskey="" type="submit" name="action" value="Validate">Log In</button>     
                        </div>
                    </form>
                </div>
            <%
               if(request.getParameter("error")!=null){
                   String error=request.getParameter("error");
                   if(error.equals("1")) out.println("User/Password not correct");
               } 

            %>
        </main>
        <footer>
            <%@include file="templates/footer.jsp" %>
        </footer>
    </body>
</html>
