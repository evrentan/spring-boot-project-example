package evrentan.examples.springbootprojectexample.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class CustomObjectMapper extends ObjectMapper {
  protected final Logger logger = LogManager.getLogger(this.getClass());

  public CustomObjectMapper() {
    super.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    super.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    super.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    super.setTimeZone(TimeZone.getDefault());
  }

  public <T> T fromJson(String jsonString, Class<T> target) {
    try {
      if (jsonString != null) {
        return this.readValue(jsonString, target);
      }
    } catch (Exception e) {
      this.logger.error("fromJson, detail: ", e);
    }
    return null;
  }

  public String toJson(Object input) {
    try {
      if (input != null) {
        return this.writeValueAsString(input);
      }
    } catch (Exception e) {
      this.logger.error("toJson, detail: ", e);
    }
    return null;
  }

  public <T> T fromJsonList(String content, TypeReference<T> valueTypeRef) {
    try {
      if (content != null) {
        return this.readValue(content,this._typeFactory.constructType(valueTypeRef));
      }
    } catch (Exception e) {
      this.logger.error("toJson, detail: ", e);
    }
    return null;
  }

  public <T> List<T> cloneObject(Iterable<T> model, Class<T> targetType) {
    List<T> result = new ArrayList<>();
    for (T item : model) {
      T clone = this.convertObject(item, targetType);
      result.add(clone);
    }
    return result;
  }

  public <T> T cloneObject(T model, Class<T> targetType) {
    return this.convertObject(model, targetType);
  }

  public <T> List<T> cloneListObject(List<T> model, Class<T> targetType) {
    List<T> result = new ArrayList<>();
    if (!CollectionUtils.isEmpty(model)) {
      for (T modelItem : model) {
        result.add(this.cloneObject(modelItem, targetType));
      }
    }
    return result;
  }

  public <T> T convertObject(Object source, Class<T> targetType) {
    try {
      if (source != null) {
        final byte[] bytes = this.writeValueAsBytes(source);
        return this.readValue(bytes, targetType);
      }
    } catch (Exception e) {
      this.logger.error("convertObject, detail: ", e);
    }
    return null;
  }

}
