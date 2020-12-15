package shop.example.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.example.shop.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByName(String name);
    User findOneById(Long id);
}
