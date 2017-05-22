package servlets;

import core.Constants;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(description = "RunningPath", urlPatterns = { "/path" })
public class RunningPath extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RunningPath() {
        super();
        Constants.loadProperties();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println(System.getProperty("user.dir"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //Not needed
    }
}
