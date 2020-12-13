package util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;

public class LoggerUtil {

	public static void logError(Logger log, Throwable t, String reason) {
		log.error("\n\n################################ ERRO ################################");
		if(reason != null) {
			log.error("Reason: " + reason);
		}
		log.error("Exception Message: " + t.getMessage());
		if(t.getCause() != null) {
			log.error("Cause: " + t.getCause().getMessage());
		}
		log.error("StackTrace: " + ExceptionUtils.getStackTrace(t));
		log.error("\n################################ FIM ERRO ################################");
	}

	public static void logError(Logger log, Throwable t) {
		logError(log, t, null);
	}
}
