package pl.tomo.luxmed.connection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.NonFinal;

import java.io.InputStream;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DataEntry
{
    @NonFinal
    String key;
    @NonFinal
    String value;
}