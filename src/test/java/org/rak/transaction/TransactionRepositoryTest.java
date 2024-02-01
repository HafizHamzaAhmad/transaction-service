package org.rak.transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rak.transaction.unit.transaction.Transaction;
import org.rak.transaction.unit.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
public class TransactionRepositoryTest {


    @Autowired
    private TransactionRepository transactionRepository;

    private List<Transaction> transactionList;

    @BeforeEach
    public void setup() {
        transactionList = List.of(Transaction.builder()
                .id(0L)
                .uuid("abc-123")
                .studentName("test")
                .studentId("123")
                .guardianName("test-parent")
                .schoolName("Skiply")
                .amount("100")
                .schoolLogoUrl("logo.png")
                .cardNumber("1213-13331-331")
                .cardType("Master Card")
                .transRefNum("REF123")
                .grade("10")
                .transDateTime(LocalDateTime.now()).build(),
                Transaction.builder()
                .id(1L)
                .uuid("abc-124")
                .studentName("test")
                .studentId("123")
                .guardianName("test-parent")
                .schoolName("Skiply")
                .amount("101")
                .schoolLogoUrl("logo.png")
                .cardNumber("1213-13331-33100")
                .cardType("Master Card")
                .transRefNum("REF124")
                .grade("10")
                .transDateTime(LocalDateTime.now()).build()
        );
        transactionRepository.saveAll(transactionList);
    }


    @Test
    public void testFindAllByStudentId() {
        // Given
        String studentId = "123";

        // When
        List<Transaction> result = transactionRepository.findAllByStudentId(studentId);

        // Then
        assertEquals(2, result.size());

        // Add more assertions based on your specific use case
    }

    @Test
    public void testFindFirstByTransRefNum() {
        // Given
        String transRefNum = "REF123";

        // When
        Optional<Transaction> result = transactionRepository.findFirstByTransRefNum(transRefNum);

        // Then
        assertNotNull(result);
//        assertEquals(transRefNum, result.getTransRefNum());
        // Add more assertions based on your specific use case
    }
}
