package org.rak.transaction.interfaces;

public interface Mapper<D, E> {

	D toDto(E entity);

	E toEntity(D dto);

}
