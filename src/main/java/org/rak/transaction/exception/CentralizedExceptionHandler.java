package org.rak.transaction.exception;

import org.springframework.beans.PropertyAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CentralizedExceptionHandler {
	@ExceptionHandler({ ApplicationException.class })
	public ProblemDetail handleAccessDeniedException(ApplicationException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
			problemDetail.setDetail(ex.getMessage());
			return problemDetail;
	}

	@ExceptionHandler({ IllegalStateException.class })
	public ProblemDetail handleAccessDeniedException(IllegalStateException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		problemDetail.setDetail(ex.getMessage());
		return problemDetail;
	}

	@ExceptionHandler({ PropertyAccessException.class })
	public ProblemDetail handleAccessDeniedException(PropertyAccessException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		problemDetail.setDetail(ex.getMessage());
		return problemDetail;
	}

	@ExceptionHandler({ Exception.class })
	public ProblemDetail handleAccessDeniedException(Exception ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		problemDetail.setDetail(ex.getMessage());
		return problemDetail;

	}

}