package pl.tomo.luxmed.storage;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Log {

    private final String value;
    private final LocalDateTime time;

    private Log(String value) {

        this.value = value;
        this.time = LocalDateTime.now();
    }

    public static Log log(String log) {

        return new Log(log);
    }
}
