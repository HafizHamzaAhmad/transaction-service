package org.rak.transaction;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rak.transaction.unit.transaction.Transaction;
import org.rak.transaction.unit.transaction.TransactionRepository;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;


@DataJpaTest
public class TransactionRepositoryTest {


    @Mock
    private TransactionRepository transactionRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testFindAllByStudentId() {
        // Given
        String studentId = "123";
        Transaction transaction1 = new Transaction(/* set your values here */);
        Transaction transaction2 = new Transaction(/* set your values here */);
        transaction1.setStudentId(studentId);
        transaction2.setStudentId(studentId);
        List<Transaction> expected = List.of(transaction1, transaction2);
        when(transactionRepository.findAllByStudentId(studentId)).thenReturn(expected);

        // When
        List<Transaction> result = transactionRepository.findAllByStudentId(studentId);

        // Then
        assertEquals(2, result.size());
        assertEquals(expected, result);

        // Add more assertions based on your specific use case
    }

    @Test
    public void testFindFirstByTransRefNum() {
        // Given
        String transRefNum = "REF123";
        Transaction transaction = new Transaction(/* set your values here */);
        transaction.setTransRefNum(transRefNum);

        when(transactionRepository.findFirstByTransRefNum(transRefNum)).thenReturn(transaction);
        // When
        Transaction result = transactionRepository.findFirstByTransRefNum(transRefNum);

        // Then
        assertNotNull(result);
        assertEquals(transRefNum, result.getTransRefNum());
        // Add more assertions based on your specific use case
    }
}
