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

/**
 * Customer Object Mapper Class in order to implement customer object mappers.
 * Its SuperClass can be reached from {@link com.fasterxml.jackson.databind.ObjectMapper} class.
 *
 * @author <a href="https://github.com/evrentan">Evren Tan</a>
 * @since 1.0.0
 */
public class CustomObjectMapper extends ObjectMapper {
  protected final Logger logger = LogManager.getLogger(this.getClass());

  public CustomObjectMapper() {
    super.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    super.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    super.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    super.setTimeZone(TimeZone.getDefault());
  }

  /**
   * Method used for mapping a JSON to an object.
   *
   * @param jsonString input JSON.
   * @param target object to be mapped.
   * @param <T> any class to be mapped.
   * @return object to be mapped.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
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

  /**
   * Method used for mapping an object to JSON string.
   *
   * @param input input object.
   * @return String JSON to be mapped.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
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

  /**
   * Method used for mapping a JSON list to an object.
   *
   * @param content input JSON List.
   * @param valueTypeRef object to be mapped.
   * @param <T> any class to be mapped.
   * @return object to be mapped.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
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

  /**
   * Method used for cloning an iterable object.
   *
   * @param model input iterable object.
   * @param targetType object type to be cloned.
   * @param <T> any class to be cloned.
   * @return object list to be cloned.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  public <T> List<T> cloneObject(Iterable<T> model, Class<T> targetType) {
    List<T> result = new ArrayList<>();
    for (T item : model) {
      T clone = this.convertObject(item, targetType);
      result.add(clone);
    }
    return result;
  }

  /**
   * Method used for cloning an object
   *
   * @param model input object
   * @param targetType object type to be cloned.
   * @param <T> any class to be cloned.
   * @return object to be cloned.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  public <T> T cloneObject(T model, Class<T> targetType) {
    return this.convertObject(model, targetType);
  }

  /**
   * Method used for cloning a list object.
   *
   * @param model input iterable object.
   * @param targetType object type to be cloned.
   * @param <T> any class to be cloned.
   * @return object list to be cloned.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  public <T> List<T> cloneListObject(List<T> model, Class<T> targetType) {
    List<T> result = new ArrayList<>();
    if (!CollectionUtils.isEmpty(model)) {
      for (T modelItem : model) {
        result.add(this.cloneObject(modelItem, targetType));
      }
    }
    return result;
  }

  /**
   * Method used for converting an object to another type.
   *
   * @param source input object.
   * @param targetType object type to be converted.
   * @param <T> any class to be converted.
   * @return object to be converted.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
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
