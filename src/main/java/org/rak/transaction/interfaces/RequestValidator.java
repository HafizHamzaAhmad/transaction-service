package org.rak.transaction.interfaces;

public interface RequestValidator<D> {
	boolean validateRequest(D dto);
}
