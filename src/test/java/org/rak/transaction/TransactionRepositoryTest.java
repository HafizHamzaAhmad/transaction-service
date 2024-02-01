package org.rak.transaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rak.transaction.unit.transaction.Transaction;
import org.rak.transaction.unit.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class TransactionRepositoryTest {


    @Autowired
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        List<Transaction> transactions =
                List.of(Transaction.builder().uuid("123").transRefNum("REF123").amount("123")
                        .studentId("123").build(), Transaction.builder().uuid("123").transRefNum("REF123")
                        .amount("123")
                        .studentId("123").build());
        transactionRepository.saveAll(transactions);
    }


    @Test
    public void testFindAllByStudentId() {
        // Given
        String studentId = "123";
        Transaction transaction1 = new Transaction(/* set your values here */);
        Transaction transaction2 = new Transaction(/* set your values here */);
        transaction1.setStudentId(studentId);
        transaction2.setStudentId(studentId);
        List<Transaction> expected =
                List.of(Transaction.builder().id(1L).uuid("123").transRefNum("REF123").amount("123")
                        .studentId("123").build(), Transaction.builder().id(2L).uuid("123").transRefNum("REF123")
                        .amount("123")
                        .studentId("123").build());

        // When
        List<Transaction> result = transactionRepository.findAllByStudentId(studentId);

        // Then
        assertEquals(2, result.size());
        assertEquals(expected.get(0).getTransRefNum(), result.get(0).getTransRefNum());
        assertEquals(expected.get(0).getStudentId(), result.get(0).getStudentId());


        // Add more assertions based on your specific use case
    }

    @Test
    public void testFindFirstByTransRefNum() {
        // Given
        String transRefNum = "REF123";

        // When
        Optional<Transaction> result = transactionRepository.findFirstByTransRefNum(transRefNum);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isPresent());
        assertEquals(transRefNum, result.get().getTransRefNum());
        // Add more assertions based on your specific use case
    }
}
