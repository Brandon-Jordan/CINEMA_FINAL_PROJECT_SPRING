package yehor.epam.cinema_final_project_spring.services;

import yehor.epam.cinema_final_project_spring.dto.UserDTO;
import yehor.epam.cinema_final_project_spring.dto.UserSignUpDTO;
import yehor.epam.cinema_final_project_spring.entities.User;
import yehor.epam.cinema_final_project_spring.exceptions.UserAlreadyExistException;

import java.util.Optional;

public interface UserService {
    void save(final UserSignUpDTO userSignUpDTO);
    void save(final UserDTO userDTO);

    User getById(final long id);

    Long getUserIdByEmail(String email);

    User getByLoginAndPass(String login, String password) throws UserAlreadyExistException;
}