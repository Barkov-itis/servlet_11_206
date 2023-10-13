package ru.itis.servlets;

import ru.itis.dto.SigUpForm;
import ru.itis.models.User;
import ru.itis.repository.SignUpService;
import ru.itis.repository.UsersRepository;
import ru.itis.repository.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/signUp")
public class TestServlet extends HttpServlet {

    private SignUpService signUpService;

    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "gjhfqr102";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";

    private UsersRepository usersRepository;
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/profile.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SigUpForm form = new SigUpForm();
        form.setFirstName(req.getParameter("firstName"));

        signUpService.signUp(form);
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("1234567890-");
//        String firstName = request.getParameter("name");
//        String lastName = request.getParameter("surname");
//        String login = request.getParameter("login");
//        String password = request.getParameter("password");
//        User user = User.builder()
//                .name(firstName)
//                .surname(lastName)
//                .login(login)
//                .password(password)
//                .build();
//        try {
//            usersRepository.save(user);
//            response.sendRedirect("/users");
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        }
//    }


}