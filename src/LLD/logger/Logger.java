package LLD.logger;

public abstract class Logger {
    protected Logger nextLogger;

    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public abstract void log(LogLevel level, String log);
}
