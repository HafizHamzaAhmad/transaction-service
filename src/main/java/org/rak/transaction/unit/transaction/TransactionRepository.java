package org.rak.transaction.unit.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByStudentId(String studentId);

    Optional<Transaction> findFirstByTransRefNum(String tranRefNum);


}
