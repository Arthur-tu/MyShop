package shop.example.shop.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shop.example.shop.dao.UserRepository;
import shop.example.shop.domain.Role;
import shop.example.shop.domain.User;
import shop.example.shop.dto.UserDto;
import shop.example.shop.mapper.UserMapper;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper mapper = UserMapper.MAPPER;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByName(username);
        if (user == null) throw new UsernameNotFoundException("User not found with name: " + username);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));
        return new org.springframework.security.core.userdetails.User (
                user.getName(),
                user.getPassword(),
                roles);
    }

    @Override
    @Transactional
    public boolean save(UserDto userDto) {
        if (!Objects.equals(userDto.getPassword(), userDto.getMatchingPassword())) {
            throw new RuntimeException("Password is not equal");
        }
        User user = mapper.toUser(userDto);
        user.setRole(Role.CLIENT);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        save(user);
        return true;
    }

    @Override
    public void save(User user) { userRepository.save(user); }

    @Override
    public List<UserDto> getAll() { return mapper.fromUserList(userRepository.findAll()); }

    @Override
    public User findByName(String name) { return userRepository.findFirstByName(name); }

    @Override
    public UserDto findDtoByName(String name) {
        return mapper.fromUser(userRepository.findFirstByName(name));
    }

    @Override
    public User findById(Long id) { return userRepository.findOneById(id); }

    @Override
    @Transactional
    public void updateProfile(UserDto dto) {
        User savedUser = userRepository.findFirstByName(dto.getName());
        if (savedUser == null) { throw new RuntimeException("User not found by username " + dto.getName()); }
        boolean changed = false;
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            savedUser.setPassword(passwordEncoder.encode(dto.getPassword()));
            changed = true;
        }
        if (!Objects.equals(dto.getEmail(), savedUser.getEmail())) {
            savedUser.setEmail(dto.getEmail());
            changed = true;
        }
        if (!Objects.equals(dto.getPhone(), savedUser.getPhone())) {
            savedUser.setPhone(dto.getPhone());
            changed = true;
        }
        if (changed) { userRepository.save(savedUser); }
    }

}