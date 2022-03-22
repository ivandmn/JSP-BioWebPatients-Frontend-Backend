<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Demo JSP Templates - Page 2</title>
        <link rel="stylesheet" href="vendors/bootstrap-4.1.3-dist/css/bootstrap.min.css">
        <script src="vendors/jquery/jquery-3.3.1.min.js"></script>
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <header>
            <%@include file="templates/menu.jsp" %>
            <%@include file="userValidation.jsp" %>
        </header>
        <main>
            <div class="container mw-100">
                <h3>News bioinformatics</h3>
                <div class="row">
                    <div class="col-md-6 col-sm-12">
                        They create a bioinformatics tool to analyze genetic variants
                        of the coronavirus<br/>
                        <a href="https://www.eldiario.es/sociedad/crean-herramienta-bioinformatica-analizar-variantes-geneticas-coronavirus_1_8286727.html">
                            diario.es; set 2021
                        </a>
                    </div>
                    <div class="col-md-6 col-sm-12">         
                    Scientists claim in the journal 'Nature' to integrate genetics, bioinformatics and
                        public health to prevent pandemics<br/>
                        <a href="https://www.infosalus.com/salud-investigacion/noticia-cientificos-reclaman-revista-nature-integrar-genetica-bioinformatica-salud-publica-evitar-pandemias-20210304154828.html">
                            Revista Nature; mar 2021
                        </a>
                    </div>
                </div>
            </div>
        </main>
        <footer>
            <%@include file="templates/footer.jsp" %>
        </footer>
    </body>
</html>
