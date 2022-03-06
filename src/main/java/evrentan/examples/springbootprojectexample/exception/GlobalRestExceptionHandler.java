package evrentan.examples.springbootprojectexample.exception;

import evrentan.examples.springbootprojectexample.dto.shared.CustomRestError;
import evrentan.examples.springbootprojectexample.message.MessageUtilityServiceImpl;
import evrentan.examples.springbootprojectexample.utility.CommonUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.el.MethodNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Objects;

/**
 * Global Exception Handler Class
 *
 * @author <a href="https://github.com/ybqdren">Zhao Wen</a>, <a href="https://github.com/evrentan">Evren Tan</a>
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
   *
   * @author <a href="https://github.com/ybqdren">Zhao Wen</a>, <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @ExceptionHandler(value = {NoHandlerFoundException.class, MethodNotFoundException.class})
  public ResponseEntity<CustomRestError> processNoHandlerFoundException(final Exception exception, final HttpServletRequest request) {
    return responseEntity(CustomRestError.builder()
        .status(HttpStatus.NOT_FOUND.value())
        .message((HttpStatus.NOT_FOUND.getReasonPhrase()))
        .build());
  }

  /**
   * To handle general Exception cases for returning Internal Server Error status
   *
   * @param exception Exception
   * @param request Web Request
   * @return ResponseEntity<CustomRestError>
   *
   * @author <a href="https://github.com/ybqdren">Zhao Wen</a>, <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<CustomRestError> processException(final Exception exception, final HttpServletRequest request) {
    final String locale = CommonUtility.getHeaderValue("locale");

    return responseEntity(CustomRestError.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message(MessageUtilityServiceImpl.getMessage("customException.internalServerError.message", Objects.nonNull(locale) ? new Locale(locale) : null))
        .build());
  }

  /**
   * To handle general Bad Request exception cases for returning Bad Request status
   *
   * @param exception Exception
   * @param request Web Request
   * @return ResponseEntity<CustomRestError>
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @ExceptionHandler(value = {MethodArgumentNotValidException.class})
  public ResponseEntity<CustomRestError> processBadRequestException(final Exception exception, final HttpServletRequest request) {
    return responseEntity(CustomRestError.builder()
        .status(HttpStatus.BAD_REQUEST.value())
        .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .build());
  }

  /**
   * To handle general Method Not Allowed exception cases for returning Method Not Allowed status
   *
   * @param exception Exception
   * @param request Web Request
   * @return ResponseEntity<CustomRestError>
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @ExceptionHandler(value = {MethodNotAllowedException.class, HttpClientErrorException.MethodNotAllowed.class})
  public ResponseEntity<CustomRestError> processMethodNotAllowedException(final Exception exception, final HttpServletRequest request) {
    final String locale = CommonUtility.getHeaderValue("locale");

    return responseEntity(CustomRestError.builder()
        .status(HttpStatus.METHOD_NOT_ALLOWED.value())
        .message(MessageUtilityServiceImpl.getMessage("customException.methodNotAllowedException.message", Objects.nonNull(locale) ? new Locale(locale) : null))
        .build());
  }

  /**
   * To build ResponseEntity with CustomerRestError
   *
   * @param error CustomerError to build the ResponseEntity
   * @return ResponseEntity<CustomRestError>
   *
   * @author <a href="https://github.com/ybqdren">Zhao Wen</a>, <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  private ResponseEntity<CustomRestError> responseEntity(CustomRestError error) {
    return new ResponseEntity(error, HttpStatus.valueOf(error.getStatus()));
  }
}
