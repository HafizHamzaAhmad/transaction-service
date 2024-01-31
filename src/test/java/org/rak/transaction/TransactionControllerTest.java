package org.rak.transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rak.transaction.unit.transaction.TransactionController;
import org.rak.transaction.unit.transaction.TransactionDto;
import org.rak.transaction.unit.transaction.TransactionService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;
    
    

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    void testGetAllByStudentId() throws Exception {
        String studentId = "123";
        List<TransactionDto> transactionList = Arrays.asList(new TransactionDto(/* add necessary fields */));

        when(transactionService.getAllByStudentId(anyString())).thenReturn(transactionList);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8083/transaction/student/{studentId}", studentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", hasSize(1))); // Adjust as per your response structure
    }

    @Test
    void testProcessPayment() throws Exception {
        TransactionDto transactionDto = new TransactionDto(/* add necessary fields */);
        when(transactionService.create(any(TransactionDto.class))).thenReturn(transactionDto);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8083/transaction")
                        .content(asJsonString(transactionDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", notNullValue())); // Adjust as per your response structure
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}