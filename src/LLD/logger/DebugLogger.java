package LLD.logger;

public class DebugLogger extends Logger {

    public void log(LogLevel level, String log) {

        if (level.equals(LogLevel.DEBUG)) {
            System.out.println("Logging Debug : " + log);

        } else if (nextLogger != null) {
            nextLogger.log(level, log);
        }
    }

}
