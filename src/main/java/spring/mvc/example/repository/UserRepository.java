package spring.mvc.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.mvc.example.domain.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
