package org.rak.transaction.dto;

import org.springframework.http.ProblemDetail;

/**
 * @author Usman
 * @created 1/23/2024 - 1:10 AM
 * @project Microservices-assessment
 */

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EndpointResponse<T> {

	T data;
	ProblemDetail problemDetail;

}
