package evrentan.examples.springbootprojectexample.exception;

import evrentan.examples.springbootprojectexample.enums.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Glabal Exception Handler
 *
 * @author zhao wen
 * @since 1.0.0
 **/

@RestControllerAdvice
public class GlobalRestExceptionHandler {

    /**
     * NoHandlerFoundException
     * @return
     */
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public ResponseEntity<RestError> processException(NoHandlerFoundException e , HttpServletRequest req , HttpServletResponse rep){
        System.out.println(e);

        RestError error = new RestError();
        error.setStatus(ResponseCode.NOT_FOUND.getCode());
        error.setMsg(ResponseCode.NOT_FOUND.getMsg());
        return responseEntity(error);
    }

    /**
     * Exception
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<RestError> processException(Exception e , HttpServletRequest req, HttpServletResponse rep){
        System.out.println(e);

        RestError error = new RestError();
        error.setStatus(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
        error.setMsg(ResponseCode.INTERNAL_SERVER_ERROR.getMsg());
        return responseEntity(error);
    }

    private ResponseEntity<RestError> responseEntity(RestError error){
        return new ResponseEntity(error , HttpStatus.valueOf(error.getStatus()));
    }
}
