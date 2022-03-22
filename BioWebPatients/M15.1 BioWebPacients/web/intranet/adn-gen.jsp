<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="adnutils.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Demo JSP Templates - Page 2</title>
        <link rel="stylesheet" href="../vendors/bootstrap-4.1.3-dist/css/bootstrap.min.css">
        <script src="../vendors/jquery/jquery-3.3.1.min.js"></script>
        <link rel="stylesheet" href="../css/styles.css">
    </head>
    <body>
        <header>
            <%@include file="../templates/menu.jsp" %>
        </header>
        <main>
            <div class="container mw-100"> 
                <h3>ADN Generator</h3>
                <p>ADN Random Generator</p>
                    <form method="post" action="adn-gen.jsp">
                        <div class="form-group col-md-20">
                            <div class="form-group col-md-15">
                                <label for='name'>Lenght ADN Sequence</label>
                                <input class="form-control" type="number" required id='lenghtADN' name="lenghtADN"
                                    placeholder='Type a number'></input>
                            </div>
                        <div class="form-group col-md-6">
                            <input type="submit" name="ok" value="Calcular"/>
                        </div>
                    </form> 

        
        <%
            // Si se clica el boton del formulario
           String lenghtInput;
           int lenght;
           boolean resultOk = false;
           if(request.getParameter("ok")!=null) {
              Validation validator = new Validation();
              ADN_Manager adnManager = new ADN_Manager();
              boolean result = false;
              lenghtInput = request.getParameter("lenghtADN");
              resultOk = lenghtInput != null;
              lenght = validator.validInteger(lenghtInput);
              if(resultOk) {
                String adnChain = adnManager.randomADNGenerator(lenght);
                 out.println("<textarea disabled id='adnrandom'> "
                          + adnChain + "</textarea>");
                
              } else {
                  out.println("<p class='error'> "
                          + "Camps no vàlids. </p>");
              }
           }
        %>
            </div>
        </main>
        <footer>
            <%@include file="../templates/footer.jsp" %>
        </footer>
    </body>
</html>
