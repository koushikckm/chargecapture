package com.ha.chargecapture.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ChargeCaptureExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOG = ESAPI.getLogger(ChargeCaptureExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		LOG.debug(Logger.EVENT_SUCCESS, "Entering handleAllExceptions()");
		LOG.error(Logger.EVENT_FAILURE, "Exception caught", ex);
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		LOG.debug(Logger.EVENT_SUCCESS, "Exiting handleAllExceptions()");
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ChargeCaptureServiceException.class)
	public final ResponseEntity<ErrorDetails> handleServiceExceptions(ChargeCaptureServiceException ex,
			WebRequest request) {
		LOG.debug(Logger.EVENT_SUCCESS, "Entering handleServiceExceptions()");
		LOG.error(Logger.EVENT_FAILURE, "ChargeCaptureServiceException caught", ex);
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		LOG.debug(Logger.EVENT_SUCCESS, "Exiting handleServiceExceptions()");
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ChargeCaptureDaoException.class)
	public ResponseEntity<ErrorDetails> handleDaoException(ChargeCaptureDaoException ex, WebRequest request) {
		LOG.debug(Logger.EVENT_SUCCESS, "Entering handleDaoException()");
		LOG.error(Logger.EVENT_FAILURE, "ChargeCaptureDaoException caught", ex);
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		LOG.debug(Logger.EVENT_SUCCESS, "Exiting handleDaoException()");
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOG.debug(Logger.EVENT_SUCCESS, "Entering handleMethodArgumentNotValid()");
		LOG.error(Logger.EVENT_FAILURE, "MethodArgumentNotValidException caught", ex);
		List<String> message = new ArrayList<>();
		List<FieldError> errorList = ex.getBindingResult().getFieldErrors();
		if (!errorList.isEmpty()) {
			for (FieldError e : errorList) {
				message.add("@ " + e.getField().toUpperCase() + " : " + e.getDefaultMessage());
			}
		} else {
			message.add(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		}

		LOG.debug(Logger.EVENT_SUCCESS, "Exiting handleMethodArgumentNotValid()");
		return new ResponseEntity(message, headers, status);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleValidationException(ConstraintViolationException e) {
		LOG.debug(Logger.EVENT_SUCCESS, "Entering handleValidationException()");
		LOG.error(Logger.EVENT_FAILURE, "ConstraintViolationException caught", e);
		for (ConstraintViolation<?> s : e.getConstraintViolations()) {

			return s.getMessage();
		}
		LOG.debug(Logger.EVENT_SUCCESS, "Exiting handleValidationException()");
		return "Validation Error";
	}
}
