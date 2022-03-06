package evrentan.examples.springbootprojectexample.message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class MessageUtilityServiceImpl {
  private static final Logger logger = LogManager.getLogger(MessageUtilityServiceImpl.class);
  private static final Locale defaultLocale = new Locale("en");

  public static ResourceBundle getBundle(Locale locale) {
    if(locale == null) {
      locale = Optional.of(Locale.getDefault()).orElse(defaultLocale);
    }
    return ResourceBundle.getBundle("messages", locale);
  }

  public static String getMessage(String messageKey) {
    String message = "messageKey not found !!!";
    try {
      message = getBundle(null).getString(messageKey);
    }catch (Exception e) {
      logger.error(String.format("Error while getting message for messageKey %s", messageKey), e);
    }
    return message;
  }

  public static String getMessage(String messageKey, Locale locale) {
    return getBundle(locale).getString(messageKey);
  }

  public static String getMessage(String messageKey, Object... messageArguments) {
    return getMessage(messageKey, messageArguments, null);
  }

  public static String getMessage(String messageKey, Locale locale, Object... messageArguments) {
    return MessageFormat.format(getBundle(locale).getString(messageKey), messageArguments);
  }
}
