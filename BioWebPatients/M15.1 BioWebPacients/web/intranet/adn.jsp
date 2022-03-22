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
            <%@include file="./userValidation.jsp" %>
            <% 
                // Obtenemos la cadena de ADN
                String ADN_field = request.getParameter("ADN_field");
                ADN_field=ADN_field!=null?ADN_field:"";
            %>
        </header>
        <main>
            <div class="container mw-100"> 
                <h3>ADN Validation</h3>
                <div class="form-row">
                    <form method="post" action="adn.jsp">
                        <div class="form-group col-md-6">
                            
                            <label for='ADN_field'>ADN chain</label>
                            <textarea id='ADN_field' name="ADN_field" rows="15" cols="50" 
                                placeholder='Example: AGCTAGC'><%=ADN_field %></textarea>
                        </div>
                        <div class="form-group col-md-6">
                            <input type="submit" name="ok" value="Calcular"/>
                        </div>
                    </form> 
                </div>
        
        <%
            // Si ha clicado el boton del formulario
           if(request.getParameter("ok")!=null) {
              ADN_field = request.getParameter("ADN_field");    
              // Validamos el ADN
              ADN_Manager adnManager = new ADN_Manager();
              boolean result = adnManager.validaADN(ADN_field);
              out.println("ADN Valid: " + result);
              // Si es valido entra en el if
              if(result) {
                int a = adnManager.numAdenines(ADN_field);
                int c = adnManager.numCitosines(ADN_field);
                int g = adnManager.numGuanines(ADN_field);
                int t = adnManager.numTimines(ADN_field);

                StringBuilder sb = new StringBuilder();

                sb.append("<dl>");
                sb.append("<dt>Num A</dt><dd>");
                sb.append(a);
                sb.append("<dt>Num C</dt><dd>");
                sb.append(c);
                sb.append("<dt>Num T</dt><dd>");
                sb.append(t);
                sb.append("<dt>Num G</dt><dd>");
                sb.append(g);
                sb.append("</dl>");
                out.println(sb.toString());
                
              } else {
                  // Si no es vàlid, retorna missatge.
                  out.println("<p class='error'> "
                          + "ADN not valid, check the input</p>");
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
