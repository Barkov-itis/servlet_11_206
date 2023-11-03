package ru.itis.litstener;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.repository.SignUpService;
import ru.itis.repository.SignUpServiceImpl;
import ru.itis.repository.UsersRepository;
import ru.itis.repository.UsersRepositoryJdbcImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CustomServletContextListener implements ServletContextListener {

    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "gjhfqr102";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";

    private static final String DB_DRIVER = "org.postgresql.Driver";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {


        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);


        ServletContext servletContext = servletContextEvent.getServletContext();

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        servletContext.setAttribute("usersRep", usersRepository);
        SignUpService signUpService = new SignUpServiceImpl(usersRepository);
        servletContext.setAttribute("signUpService", signUpService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
