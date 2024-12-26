package LLD.logger;

public class WarningLogger extends Logger {

    public void log(LogLevel level, String log) {

        if (level.equals(LogLevel.WARNING)) {
            System.out.println("Logging Error  :" + log);

        } else if (nextLogger != null) {
            nextLogger.log(level, log);
        }
    }

}
