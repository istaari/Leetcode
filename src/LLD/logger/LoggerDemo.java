package LLD.logger;

public class LoggerDemo {

    public static void main(String[] args) {

        DebugLogger debugLogger = new DebugLogger();
        WarningLogger warningLogger = new WarningLogger();
        ErrorLogger errorLogger = new ErrorLogger();


        debugLogger.setNextLogger(warningLogger);
        warningLogger.setNextLogger(errorLogger);

        debugLogger.log(LogLevel.ERROR, "Stack OverFlow Error");
    }
}
