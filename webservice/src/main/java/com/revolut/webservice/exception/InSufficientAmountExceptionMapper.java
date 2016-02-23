package com.revolut.webservice.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.revolut.webservice.model.ErrorMessage;


@Provider
public class InSufficientAmountExceptionMapper implements ExceptionMapper<InSufficientAmountException> {

	public Response toResponse(InSufficientAmountException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 400);
		return Response.status(Status.BAD_REQUEST)
				.entity(errorMessage)
				.build();
	}

}
