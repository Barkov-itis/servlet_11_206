package ru.itis.servlets;

import org.w3c.dom.html.HTMLTableCaptionElement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/files")
@MultipartConfig
public class FilesUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("html/fileUpload.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//        String currentLine = reader.readLine();
//        while (currentLine != null) {
//            System.out.println(currentLine);
//            currentLine = reader.readLine();
//        }
        Part part = request.getPart("file");
        System.out.println(part.getSubmittedFileName() + " ");
        System.out.println(part.getContentType() + " ");
        System.out.println(part.getSize());

        Files.copy(part.getInputStream(), Paths.get("C://Users/tron5/IdeaProjects/Servlets_11_206/src/main/resources/files/" + part.getSubmittedFileName()));
        response.sendRedirect("/files");
    }
}
