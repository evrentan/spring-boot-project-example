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

  private void saveApiLog(ApiLog apiLog) {
    this.apiLogRepository.save(this.apiLogMapper.toEntity(apiLog));
  }

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

