package org.rak.transaction;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rak.transaction.exception.ApplicationException;
import org.rak.transaction.interfaces.RequestValidator;
import org.rak.transaction.unit.transaction.Transaction;
import org.rak.transaction.unit.transaction.TransactionDto;
import org.rak.transaction.unit.transaction.TransactionRepository;
import org.rak.transaction.unit.transaction.TransactionService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class TransactionServiceTest {


    @Mock
    private RequestValidator<TransactionDto> validator;
    @Mock
    private org.rak.transaction.interfaces.Mapper<TransactionDto, Transaction> mapper;
    @Mock
    private TransactionRepository repository;
    @InjectMocks
    private TransactionService transactionService;


    @Test
    void testCreateValidDto() {
        // Arrange
        TransactionDto inputDto = new TransactionDto(/* set your values here */);
        Transaction mappedEntity = new Transaction(/* set your values here */);
        Transaction savedEntity = new Transaction(/* set your values here */);
        TransactionDto expectedDto = new TransactionDto(/* set your values here */);

        when(validator.validateRequest(inputDto)).thenReturn(true);
        when(mapper.toEntity(inputDto)).thenReturn(mappedEntity);
        when(repository.save(mappedEntity)).thenReturn(savedEntity);
        when(mapper.toDto(savedEntity)).thenReturn(expectedDto);

        // Act
        TransactionDto resultDto = transactionService.create(inputDto);

        // Assert
        assertNotNull(resultDto);
        assertEquals(expectedDto, resultDto);

        // Verify that methods were called with the expected parameters
        verify(validator).validateRequest(inputDto);
        verify(mapper).toEntity(inputDto);
        verify(repository).save(mappedEntity);
        verify(mapper).toDto(savedEntity);

        // Ensure that no other methods were called on the mocks
        verifyNoMoreInteractions(validator, mapper, repository);
    }

    @Test
    void testCreateInvalidDto() {
        // Arrange
        TransactionDto inputDto = new TransactionDto(/* set your values here */);

        when(validator.validateRequest(inputDto)).thenReturn(false);

        // Act and Assert
        assertThrows(ApplicationException.class, () -> transactionService.create(inputDto));

        // Verify that only the validator method was called
        verify(validator).validateRequest(inputDto);
        verifyNoInteractions(mapper, repository);
    }

    @Test
    void testGetAllByStudentId() {
        // Arrange
        String studentId = "123";
        Transaction entity1 = new Transaction(/* set your values here */);
        Transaction entity2 = new Transaction(/* set your values here */);
        List<Transaction> entities = Arrays.asList(entity1, entity2);

        TransactionDto dto1 = new TransactionDto(/* set your values here */);
        TransactionDto dto2 = new TransactionDto(/* set your values here */);
        List<TransactionDto> expectedDtos = Arrays.asList(dto1, dto2);

        when(repository.findAllByStudentId(studentId)).thenReturn(entities);
        when(mapper.toDto(entity1)).thenReturn(dto1);
        when(mapper.toDto(entity2)).thenReturn(dto2);

        // Act
        List<TransactionDto> resultDtos = transactionService.getAllByStudentId(studentId);

        // Assert
        assertEquals(expectedDtos, resultDtos);

        // Verify that methods were called with the expected parameters
        verify(repository).findAllByStudentId(studentId);
        verify(mapper).toDto(entity1);
        verify(mapper).toDto(entity2);

        // Ensure that no other methods were called on the mocks
        verifyNoMoreInteractions(repository, mapper);
    }
}
