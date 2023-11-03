package ru.itis.repository;

import ru.itis.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private DataSource dataSource;

    private static final String SQL_SELECT_ALL_FROM_DRIVER = "select * from driver";
    private static final String SQL_INSERT_INTO_USERS = "insert into driver(login,password,first_name,last_name) values ";


    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List findAllByAge() {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from driver");
            List<User> result = new ArrayList<>();
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("first_name"))
                        .surname(resultSet.getString("last_name"))
                        .build();
                result.add(user);
//                if (user.getAge().equals(age)) {
//                    result.add(user);
//                }
            }
            if (result.isEmpty()) {
                System.out.println("По введенному возврасту ничего не найдено...");
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(User entity) throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(entity.getName());
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_USERS + "(?, ?, ?, ?)");
        preparedStatement.setString(1, entity.getLogin());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.setString(3, entity.getName());
        preparedStatement.setString(4, entity.getSurname());
        preparedStatement.executeUpdate();

        ResultSet generated = preparedStatement.getGeneratedKeys();
        System.out.println(generated);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findByLogin(User login) {
        return Optional.empty();
    }
}
