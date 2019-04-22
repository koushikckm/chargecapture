package com.ha.chargecapture.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger log = LoggerFactory.getLogger(ChargeCaptureExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		log.debug("Entering handleAllExceptions()");
		log.error("Exception caught", ex);
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		log.debug("Exiting handleAllExceptions()");
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ChargeCaptureServiceException.class)
	public final ResponseEntity<ErrorDetails> handleServiceExceptions(ChargeCaptureServiceException ex,
			WebRequest request) {
		log.debug("Entering handleServiceExceptions()");
		log.error("ChargeCaptureServiceException caught", ex);
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		log.debug("Exiting handleServiceExceptions()");
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ChargeCaptureDaoException.class)
	public ResponseEntity<ErrorDetails> handleDaoException(ChargeCaptureDaoException ex, WebRequest request) {
		log.debug("Entering handleDaoException()");
		log.error("ChargeCaptureDaoException caught", ex);
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		log.debug("Exiting handleDaoException()");
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.debug("Entering handleMethodArgumentNotValid()");
		log.error("MethodArgumentNotValidException caught", ex);
		List<String> message = new ArrayList<>();
		List<FieldError> errorList = ex.getBindingResult().getFieldErrors();
		if (!errorList.isEmpty()) {
			for (FieldError e : errorList) {
				message.add("@ " + e.getField().toUpperCase() + " : " + e.getDefaultMessage());
			}
		} else {
			message.add(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		}

		log.debug("Exiting handleMethodArgumentNotValid()");
		return new ResponseEntity(message, headers, status);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleValidationException(ConstraintViolationException e) {
		log.debug("Entering handleValidationException()");
		log.error("ConstraintViolationException caught", e);
		for (ConstraintViolation<?> s : e.getConstraintViolations()) {

			return s.getMessage();
		}
		log.debug("Exiting handleValidationException()");
		return "Validation Error";
	}
}
