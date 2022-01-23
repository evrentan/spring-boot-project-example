package evrentan.examples.springbootprojectexample.aspect;

import evrentan.examples.springbootprojectexample.annotation.ApiLogger;
import evrentan.examples.springbootprojectexample.dto.ApiLog;
import evrentan.examples.springbootprojectexample.mapper.ApiLogMapper;
import evrentan.examples.springbootprojectexample.mapper.CustomObjectMapper;
import evrentan.examples.springbootprojectexample.repository.ApiLogRepository;
import evrentan.examples.springbootprojectexample.utility.CommonUtility;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

/**
 * Implementation Class for Api Logging Mechanism.
 * This implementation is executed around advice ApiLogger annotation
 *
 * @author <a href="https://github.com/evrentan">Evren Tan</a>
 * @since 1.0.0
 */
@Aspect
@Component
public class ApiLogAspect {

  private final ApiLogRepository apiLogRepository;
  private final ApiLogMapper apiLogMapper;
  @Qualifier(value = "customObjectMapper")
  private final CustomObjectMapper customObjectMapper;


  public ApiLogAspect(ApiLogRepository apiLogRepository, ApiLogMapper apiLogMapper, CustomObjectMapper customObjectMapper) {
    this.apiLogRepository = apiLogRepository;
    this.apiLogMapper = apiLogMapper;
    this.customObjectMapper = customObjectMapper;
  }

  /**
   *
   * @param joinPoint ProceedingJoinPoint support around advice
   * @param apiLogger ApiLogger DTO in order to generate the log for API calls
   * @return Object that is the result of proceed of annotation joint
   * @throws Throwable throws exception during operations of saving the API call logs
   *
   *  @author <a href="https://github.com/evrentan">Evren Tan</a>
   *  @since 1.0.0
   */
  @Around("@annotation(apiLogger)")
  public Object log(ProceedingJoinPoint joinPoint, ApiLogger apiLogger) throws Throwable {

    HttpServletRequest request = CommonUtility.getHttpServletRequest();

    StopWatch stopWatch = new StopWatch();

    stopWatch.start();

    try {
      Object response = joinPoint.proceed();

      stopWatch.stop();

      String responseToJson = this.customObjectMapper.toJson(response);

      this.saveApiLog(this.generateApiLog(joinPoint, request, stopWatch, responseToJson, null));

      return response;

    } catch (Exception e) {
      stopWatch.stop();

      String errorCode = "";
      if (RequestContextHolder.getRequestAttributes() != null) {
        RequestContextHolder.getRequestAttributes().setAttribute("errorCode", errorCode, RequestAttributes.SCOPE_REQUEST);
      }

      this.generateApiLog(joinPoint, request, stopWatch, errorCode, e.getMessage());

      throw e;
    }
  }

  /**
   * Method for saving ApiLogEntity to ApiLog Repository
   * @param apiLog object that is generated to be saved into related ApiLogEntity Repository
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  private void saveApiLog(ApiLog apiLog) {
    this.apiLogRepository.save(this.apiLogMapper.toEntity(apiLog));
  }

  /**
   * Method for generating ApiLog object from API call request, response, duration of API response time from stopwatch and exception message if exists
   *
   * @param joinPoint ProceedingJoinPoint support around advice
   * @param request API call request
   * @param stopWatch StopWatch object for API response time
   * @param responseToJson API response in JSON string
   * @param exceptionMessage exception message if exists
   * @return ApiLog object that is generated. Please, see the {@link evrentan.examples.springbootprojectexample.dto.ApiLog} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  private ApiLog generateApiLog(ProceedingJoinPoint joinPoint, HttpServletRequest request, StopWatch stopWatch, String responseToJson, String exceptionMessage) {
    return ApiLog.builder()
        .sourceIpAddress(CommonUtility.getIpAddress(request))
        .httpRequestMethod(request.getMethod())
        .endPoint(request.getRequestURL().toString())
        .requestPayload(CommonUtility.generatePayloadDetail(joinPoint))
        .responsePayload(responseToJson)
        .httpStatusCode(200)
        .totalDuration(stopWatch.getTotalTimeMillis())
        .exceptionMessage(exceptionMessage)
        .callDate(Calendar.getInstance().getTime())
        .build();
  }
}

