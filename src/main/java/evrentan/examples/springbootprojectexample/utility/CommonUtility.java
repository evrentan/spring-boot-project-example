package evrentan.examples.springbootprojectexample.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Class for Common Utilities used within the project
 *
 * @author <a href="https://github.com/evrentan">Evren Tan</a>
 * @since 1.0.0
 */
public class CommonUtility {

  /**
   * Method in order to generate payload detail from JointPoint.
   *
   * @param joinPoint Please, see the {@link org.aspectj.lang.JoinPoint} class for details.
   * @return String which is the generated payload from JointPoint.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  public static String generatePayloadDetail(JoinPoint joinPoint) {
    CodeSignature signature = (CodeSignature) joinPoint.getSignature();
    ObjectMapper om = new ObjectMapper();
    om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < joinPoint.getArgs().length; i++) {
      if (signature.getParameterNames() != null) {
        stringBuilder.append(signature.getParameterNames()[i]);
      } else {
        stringBuilder.append("parameter").append(i);
      }
      stringBuilder.append(":");
      try {
        stringBuilder.append(om.writer().writeValueAsString(joinPoint.getArgs()[i]));
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
      stringBuilder.append(",");
    }
    return stringBuilder.toString();
  }

  /**
   * Method in order to get IP address from HTTP Servlet Request.
   *
   * @param request Please, see the {@link javax.servlet.http.HttpServletRequest} class for details.
   * @return String which is the IP Address retrieved from HTTP Servlet Request.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  public static String getIpAddress(HttpServletRequest request) {
    String ipAddress = request.getHeader("X-FORWARDED-FOR");
    if (ipAddress != null)
      return ipAddress;
    else return request.getRemoteAddr();
  }

  /**
   * Method in order to get HTTP Servlet Request.
   *
   * @return HTTP Servlet Request. Please, see the {@link javax.servlet.http.HttpServletRequest} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  public static HttpServletRequest getHttpServletRequest() {
    RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
    if (Objects.nonNull(attributes)) {
      return ((ServletRequestAttributes) attributes).getRequest();
    }
    return null;
  }

  /**
   * Method in order to get the specified header name from HTTP service request.
   *
   * @param headerName Please, see the {@link String} class for details.
   * @return String headerName which is the specified header name.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  public static String getHeaderValue(String headerName) {
    HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    if (Objects.nonNull(httpServletRequest.getHeader(headerName)))
      return httpServletRequest.getHeader(headerName);

    return null;
  }
}
