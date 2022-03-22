<% 
   if(session.getAttribute("user")!=null) {
       String cookieName = session.getAttribute("user").toString();
   } else {
       response.sendRedirect("../login.jsp?error=1");
   }
%>