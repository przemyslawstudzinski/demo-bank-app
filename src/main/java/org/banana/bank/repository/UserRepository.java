package org.banana.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.banana.bank.domain.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
