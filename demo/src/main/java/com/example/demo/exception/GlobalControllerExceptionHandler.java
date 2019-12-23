package com.example.demo.exception;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.CommonConstant;
import com.example.json.ErrorInfo;

/**
 * Global Error Handler
 *
 * @author Faruk Hossain
 * @version 1.0.0
 *
 */
@RestController
@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DemoException.class)
	public ErrorInfo handleNotFoundException(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(HttpStatus.BAD_REQUEST, ex);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RuntimeException.class)
	public ErrorInfo handleRunTimeException(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR, CommonConstant.ERROR_INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
	public ErrorInfo handleInvalidMethodException(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(HttpStatus.METHOD_NOT_ALLOWED,
				req.getMethod() + " METHOD NOT VALID AT THIS URL: " + req.getRequestURL());
	}

	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(SQLException.class)
	public ErrorInfo handleSQLException(HttpServletRequest request, Exception ex) {
		return new ErrorInfo(HttpStatus.UNPROCESSABLE_ENTITY, ex);
	}

}