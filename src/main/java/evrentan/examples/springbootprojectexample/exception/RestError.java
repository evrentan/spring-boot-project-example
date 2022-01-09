package evrentan.examples.springbootprojectexample.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 
 * @author zhao wen
 * @since 1.0.0
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestError {

    private Integer status;

    private String msg;

    private static RestError failed(String msg){
        RestError restError = new RestError();
        restError.setMsg(msg);
        return restError;
    }

    private static RestError failed(Integer code, String msg){
        RestError restError = new RestError();
        restError.setStatus(code);
        restError.setMsg(msg);
        return restError;
    }
}
