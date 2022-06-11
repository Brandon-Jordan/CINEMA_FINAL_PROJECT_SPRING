package yehor.epam.cinema_final_project_spring.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yehor.epam.cinema_final_project_spring.dto.UserDTO;
import yehor.epam.cinema_final_project_spring.dto.UserSignUpDTO;
import yehor.epam.cinema_final_project_spring.entities.User;

@Component
public class MapperDTO {
/*    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MapperDTO(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }*/

    public User toUser(UserDTO userDTO) {
        return new User();
    }

    public User toUser(UserSignUpDTO userDTO) {
        final User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setUserRole(userDTO.getUserRole());
        user.setNotification(userDTO.getNotification());
        return user;
    }

}