package org.banana.bank.repository;

import org.banana.bank.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Interface for CRUD operations on a repository for Transactions.
 */
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
