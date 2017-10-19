package pl.tomo.luxmed.connection;

import lombok.*;
import lombok.experimental.NonFinal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class Cookie {

    @NonFinal
    String key;
    @NonFinal
    String value;
}
