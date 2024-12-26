package LLD.logger;

public class ErrorLogger extends Logger {

    public void log(LogLevel level, String log) {

        if (level.equals(LogLevel.ERROR)) {
            System.out.println("Logging Error : " + log);

        } else if (nextLogger != null) {
            nextLogger.log(level, log);
        }
    }

}
