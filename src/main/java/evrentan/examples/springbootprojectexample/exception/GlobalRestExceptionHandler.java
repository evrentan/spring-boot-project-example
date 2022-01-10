package evrentan.examples.springbootprojectexample.exception;

import evrentan.examples.springbootprojectexample.dto.shared.CustomRestError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.el.MethodNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Global Exception Handler Class
 *
 * @author zhao wen, evren tan
 * @since 1.0.0
 **/
@RestControllerAdvice
public class GlobalRestExceptionHandler {

  /**
   * To handle general Not Found exception cases for returning Not Found status
   *
   * @param exception NoHandlerFoundException
   * @param request Web Request
   * @return ResponseEntity<CustomRestError>
   * @author zhao wen, evren tan
   * @since 1.0.0
   */
  @ExceptionHandler(value = {NoHandlerFoundException.class, MethodNotFoundException.class})
  public ResponseEntity<CustomRestError> processNoHandlerFoundException(final Exception exception, final HttpServletRequest request) {
    return responseEntity(CustomRestError.builder()
        .status(HttpStatus.NOT_FOUND.value())
        .msg((HttpStatus.NOT_FOUND.getReasonPhrase()))
        .build());
  }

  /**
   * To handle general Exception cases for returning Internal Server Error status
   *
   * @param exception Exception
   * @param request Web Request
   * @return ResponseEntity<CustomRestError>
   * @author zhao wen, evren tan
   * @since 1.0.0
   */
  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<CustomRestError> processException(final Exception exception, final HttpServletRequest request) {
    return responseEntity(CustomRestError.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .msg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
        .build());
  }

  /**
   * To handle general Bad Request exception cases for returning Bad Request status
   *
   * @param exception Exception
   * @param request Web Request
   * @return ResponseEntity<CustomRestError>
   * @author evren tan
   * @since 1.0.0
   */
  @ExceptionHandler(value = {MethodArgumentNotValidException.class})
  public ResponseEntity<CustomRestError> processBadRequestException(final Exception exception, final HttpServletRequest request) {
    return responseEntity(CustomRestError.builder()
        .status(HttpStatus.BAD_REQUEST.value())
        .msg(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .build());
  }

  /**
   * To handle general Method Not Allowed exception cases for returning Method Not Allowed status
   *
   * @param exception Exception
   * @param request Web Request
   * @return ResponseEntity<CustomRestError>
   * @author evren tan
   * @since 1.0.0
   */
  @ExceptionHandler(value = {MethodNotAllowedException.class, HttpClientErrorException.MethodNotAllowed.class})
  public ResponseEntity<CustomRestError> processMethodNotAllowedException(final Exception exception, final HttpServletRequest request) {
    return responseEntity(CustomRestError.builder()
        .status(HttpStatus.METHOD_NOT_ALLOWED.value())
        .msg(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())
        .build());
  }

  /**
   * To build ResponseEntity with CustomerRestError
   *
   * @param error CustomerError to build the ResponseEntity
   * @return ResponseEntity<CustomRestError>
   * @author zhao wen, evren tan
   * @since 1.0.0
   */
  private ResponseEntity<CustomRestError> responseEntity(CustomRestError error) {
    return new ResponseEntity(error, HttpStatus.valueOf(error.getStatus()));
  }
}
