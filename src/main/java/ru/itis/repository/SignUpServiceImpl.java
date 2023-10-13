package ru.itis.repository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.SigUpForm;
import ru.itis.models.User;

import java.sql.SQLException;

public class SignUpServiceImpl implements SignUpService{

    private UsersRepository usersRepository;

    private PasswordEncoder passwordEncoder;

    public SignUpServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void signUp(SigUpForm form) throws SQLException {
        User user = User.builder()
                .name(form.getFirstName())
                .surname(form.getLastName())
                .login(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .build();

        usersRepository.save(user);

        // функция проверки пароля
//        Boolean pass = passwordEncoder.matches(rawPass, encodePass);
    }
}
