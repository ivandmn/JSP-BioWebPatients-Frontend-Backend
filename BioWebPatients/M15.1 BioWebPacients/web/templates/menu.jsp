<nav class="navbar sticky-top navbar-dark topnav">
    <h3 class = "title-topnav">Bioinformatics Website</h3>
    <% if(session.getAttribute("user")==null) { %>
    <nav class="nav nav-pills flex-column flex-sm-row">
        <a class='flex-sm-fill text-sm-center nav-link opt-menu' 
           href='./news.jsp'>News</a>
        <a class='flex-sm-fill text-sm-center nav-link active opt-menu' 
           href='./login.jsp'>Login</a>
    <% } %> 
    <% if(session.getAttribute("user")!=null) { %>
        <% if( (session.getAttribute("user")!=null) 
            && (session.getAttribute("role").equals("ADMIN")) ) { %>
            <a class='flex-sm-fill text-sm-center nav-link opt-menu' 
               accesskey=""href='<%= request.getContextPath() %>/intranet/adn.jsp'>ADN Validator</a>
            <a class='flex-sm-fill text-sm-center nav-link opt-menu' 
               accesskey=""href='<%= request.getContextPath() %>/intranet/adn-gen.jsp'>ADN Generator</a>
            <a class='flex-sm-fill text-sm-center nav-link opt-menu' 
               href='<%= request.getContextPath() %>/intranet/admin/addPatient.jsp'>Add Patient</a>
            <a class='flex-sm-fill text-sm-center nav-link opt-menu' 
               href='<%= request.getContextPath() %>/intranet/admin/addUser.jsp'>Add User</a>
            <a class='flex-sm-fill text-sm-center nav-link opt-menu' 
               href='<%= request.getContextPath() %>/user?action=AdminPage'>List Users</a>
        <%      
            } else {
        %>
        <a class='flex-sm-fill text-sm-center nav-link opt-menu' 
           href='<%= request.getContextPath() %>/intranet/adn.jsp'>ADN Validator</a>
        <a class='flex-sm-fill text-sm-center nav-link opt-menu' 
           accesskey=""href='<%= request.getContextPath() %>/intranet/adn-gen.jsp'>ADN Generator</a>
        <% 
            }
        %>
        <!-- 
        <a class='flex-sm-fill text-sm-center nav-link opt-menu' 
           href='../intranet/listAllPatients.jsp'>List Patients</a> 
        -->
        <a class='flex-sm-fill text-sm-center nav-link opt-menu' 
           href='<%= request.getContextPath() %>/intranet/listAllPatients.jsp'>List Patients</a> 
        <a class='flex-sm-fill text-sm-center nav-link opt-menu' 
           href='<%= request.getContextPath() %>/intranet/filterPatients.jsp'>Filter Patients</a>
        <!--
        <a class='flex-sm-fill text-sm-center nav-link opt-menu' 
           href='../intranet/adn-gen.jsp'>ADN Gen (Pt14Opt)</a>
        -->
        <a class='flex-sm-fill text-sm-center nav-link active opt-menu' 
           href='../user?action=Invalidate'>Logout</a>
        <%      
            } 
        %>
        
    </nav>
</nav>
