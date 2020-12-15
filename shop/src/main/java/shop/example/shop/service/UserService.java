package shop.example.shop.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import shop.example.shop.domain.User;
import shop.example.shop.dto.UserDto;
import java.util.List;

public interface UserService extends UserDetailsService {
    boolean save(UserDto userDto);
    void save(User user);
    List<UserDto> getAll();
    User findByName(String name);
    UserDto findDtoByName(String name);
    User findById(Long id);
    void updateProfile(UserDto dto);

}
