package org.rak.transaction.unit.transaction;

import jakarta.validation.Valid;
import org.rak.transaction.dto.EndpointResponse;
import org.rak.transaction.interfaces.BusinessService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {

	private final BusinessService<TransactionDto> businessService;
	private final TransactionService transactionService;

	public TransactionController(BusinessService<TransactionDto> businessService, TransactionService transactionService) {
		this.businessService = businessService;
		this.transactionService = transactionService;
	}


	@GetMapping("/student/{studentId}")
	public EndpointResponse<List<TransactionDto>> getAllByStudentId(@PathVariable String studentId){
		return new EndpointResponse<>(transactionService.getAllByStudentId(studentId),null);
	}

	@PostMapping
	public EndpointResponse<TransactionDto> processPayment(@Valid @RequestBody TransactionDto transactionDto){
		return new EndpointResponse<>(businessService.create(transactionDto), null);
	}

	@GetMapping("/view-receipt")
	public EndpointResponse<String> viewReceipt(@RequestParam String transNumber){
		return new EndpointResponse<>(transactionService.generateReceiptByRefNo(transNumber), null);
	}

}
