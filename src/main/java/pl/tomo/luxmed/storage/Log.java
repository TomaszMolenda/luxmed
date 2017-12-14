package pl.tomo.luxmed.storage;

import java.time.LocalDateTime;

public class Log {

    private final String value;
    private final LocalDateTime localDateTime;

    private Log(String value) {

        this.value = value;
        this.localDateTime = LocalDateTime.now();
    }

    public static Log log(String log) {

        return new Log(log);
    }
}
