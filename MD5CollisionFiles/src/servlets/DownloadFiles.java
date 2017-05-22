package servlets;

import core.Constants;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;


@WebServlet(description = "DownloadFiles", urlPatterns = { "/download" })
public class DownloadFiles extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DownloadFiles() {
        super();
        Constants.loadProperties();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String filename = request.getParameter("file");
            String ref = request.getParameter("ref");
            String extension = request.getParameter("ext");

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + extension + "\"");

            File file = new File(Constants.outputPath + filename + ref + extension);

            if(file.exists()) {
                ServletOutputStream out = response.getOutputStream();

                out.write(Files.readAllBytes(file.toPath()));

                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            PrintWriter out = response.getWriter();
            out.println("<h1>504 File Not Found</h1>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //Not needed
    }
}
