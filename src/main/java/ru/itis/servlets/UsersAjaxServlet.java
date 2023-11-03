package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.models.User;
import ru.itis.repository.UsersRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PipedReader;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/ajaxUsers")
public class UsersAjaxServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private UsersRepository usersRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usersRepository = (UsersRepository) config.getServletContext().getAttribute("usersRep")   ;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/ajax_example.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // приняли JSON на вход, далее с
        User user = objectMapper.readValue(request.getReader(), User.class);
        try {
            usersRepository.save(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List users = usersRepository.findAllByAge();

        String usersAsJson = objectMapper.writeValueAsString(users);
        response.setContentType("application/json");
        response.getWriter().println(usersAsJson);
    }
}
