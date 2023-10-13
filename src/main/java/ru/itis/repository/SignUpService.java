package ru.itis.repository;

import ru.itis.dto.SigUpForm;

import java.sql.SQLException;

public interface SignUpService {
    void signUp(SigUpForm form) throws SQLException;
}
