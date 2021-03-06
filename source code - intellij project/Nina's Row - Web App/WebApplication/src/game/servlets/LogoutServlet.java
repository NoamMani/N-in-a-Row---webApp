package game.servlets;

import game.constants.Constants;
import game.utils.ServletUtils;
import game.utils.SessionUtils;
import rooms.RoomsController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static game.servlets.LoginServlet.LOGIN_ERROR_URL;

public class LogoutServlet extends HttpServlet {


    private static final String LOGOUT_URL = "/pages/logout/logout_msg.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String usernameFromSession = SessionUtils.getUsername(request);
        RoomsController roomsController = ServletUtils.getRoomsController(getServletContext());

        if (usernameFromSession == null) {
            request.setAttribute(Constants.USER_NAME_ERROR, "You must sign in first!");
            getServletContext().getRequestDispatcher(LOGIN_ERROR_URL).forward(request, response);
        } else {
            System.out.println("Clearing session for " + usernameFromSession);
            request.setAttribute(Constants.USER_NAME_LOGOUT,usernameFromSession);
            roomsController.removeUser(usernameFromSession);
            SessionUtils.clearSession(request);
            getServletContext().getRequestDispatcher(LOGOUT_URL).forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}