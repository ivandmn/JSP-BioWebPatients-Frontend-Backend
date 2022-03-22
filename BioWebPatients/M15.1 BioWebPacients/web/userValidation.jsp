<% 
   if(session.getAttribute("user")!=null) {
       String cookieName = session.getAttribute("user").toString();
       /* 
       String cookieName = session.getAttribute("user").toString();
       Cookie[] cookies = request.getCookies();
       boolean existCookie = false;
       for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                existCookie=true;
            }
       } 
       if(!existCookie) {
           response.sendRedirect("../login.jsp?error=1");
       } 
       */
   } 
%>