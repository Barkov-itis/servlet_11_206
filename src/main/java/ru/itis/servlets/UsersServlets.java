package ru.itis.servlets;

import ru.itis.repository.SignUpService;
import ru.itis.repository.UsersRepository;
import ru.itis.repository.UsersRepositoryJdbcImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@WebServlet("/users")
public class UsersServlets extends HttpServlet {
    private UsersRepository usersRepository;



//    private List<User> users;
    @Override
    public void init(ServletConfig config) throws ServletException {
        usersRepository = (UsersRepository) config.getServletContext().getAttribute("usersRep");
//        users = new ArrayList<User>();
//
//        User user1 = User.builder()
//                .id(1L)
//                .firstName("DAnil")
//                .secondName("Barkov")
//                .age(22)
//                .build();
//
//        User user2 = User.builder()
//                .id(1L)
//                .firstName("Ivan")
//                .secondName("Ivanov")
//                .age(22)
//                .build();
//
//        users.add(user1);
//        users.add(user2);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter writer = response.getWriter();
//
//        StringBuilder resultHtml = new StringBuilder();
//                resultHtml.append("<!DOCTYPE html>\n" +
//                "<html lang=\"en\">\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <title>Users</title>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "<h1>Users</h1>\n" +
//                "<div>\n" +
//                "    <table>\n" +
//                "        <tr>\n" +
//                "            <th>ID</th>\n" +
//                "            <th>FIRST NAME</th>\n" +
//                "            <th>LAST NAME</th>\n" +
//                "            <th>AGE</th>\n" +
//                "        </tr>\n");
//
//                for (User user : users) {
//            resultHtml.append("<tr>\n");
//            resultHtml.append("<td>").append(user.getId()).append("</td>\n");
//            resultHtml.append("<td>").append(user.getFirstName()).append("</td>\n");
//            resultHtml.append("<td>").append(user.getSecondName()).append("</td>\n");
//            resultHtml.append("<td>").append(user.getAge()).append("</td>\n");
//            resultHtml.append("<tr>");
//        }
//
//
//        resultHtml.append("    </table>\n" +
//                "</div>\n" +
//                "</body>\n" +
//                "</html>");
//
//                writer.write(resultHtml.toString());
        List result = null;
        result = usersRepository.findAllByAge();
        request.setAttribute("usersForJsp", result);
        request.getRequestDispatcher("/jsp/users.jsp").forward(request, response);
    }
}
