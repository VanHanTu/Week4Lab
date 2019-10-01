/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.AccountService;
import Model.User;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 759841
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

        HttpSession session = request.getSession();
        
        String logout = request.getParameter("logout");
       //String login = request.getParameter("Login");
       AccountService as = new AccountService();
       
       String checkname = (String) session.getAttribute("Name");
        
       
        if(logout == null)
        {
            if(checkname != null)
            {
                response.sendRedirect("home");
            }
            else
            {
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        
            }
                
        }
        
        else if(logout.equals(""))
        {
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        
        }
        
       // if(checkname.equals())
        
        /*
        if(logout != null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }*/
        //processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
       
       HttpSession session = request.getSession();
       String user = request.getParameter("userName");
       String pass = request.getParameter("passWord");
       
       
       if(user == null || pass == null)
       {
           request.setAttribute("check", "Please try again");
       }
       
       //String login = request.getParameter("Login");
       
       AccountService as = new AccountService();
       User u = as.login(user, pass);
       
        
        if(u == null)
        {
            request.setAttribute("check", "Please try again");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        else
        {
            
            session.setAttribute("Name", u.getUser());
            //getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            response.sendRedirect("home");
        }
       //User(user,pass);
       
    }

}
