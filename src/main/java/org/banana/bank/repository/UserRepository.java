package org.banana.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.banana.bank.domain.User;

import java.util.UUID;

/**
 * Interface for CRUD operations on a repository for Users.
 */
public interface UserRepository extends JpaRepository<User, UUID> {
}
