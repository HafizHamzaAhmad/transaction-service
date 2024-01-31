package org.rak.transaction.interfaces;

import org.rak.transaction.unit.transaction.TransactionDto;

import java.util.List;

public interface BusinessService<D> {
	D getByUuid(String uuid);
	D create(D dto);
	D update(D dto, String id);
	void delete(String uuid);
	List<TransactionDto> getAllByStudentId(String studentId);

}
