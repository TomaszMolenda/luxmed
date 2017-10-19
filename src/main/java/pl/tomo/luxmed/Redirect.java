package pl.tomo.luxmed;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.api.client.util.Maps.newHashMap;
import static org.apache.commons.lang3.StringUtils.EMPTY;


public class Redirect {

    public static Builder builder() {

        return new Builder();
    }

    public static class Builder {

        private String path;
        private HashMap<String, String> params = newHashMap();

        public Builder path(String path) {

            this.path = path;

            return this;
        }

        public Builder param(String key, String value) {

            params.put(key, value);

            return this;
        }

        public String build() {

            return "redirect:/" + removeSlash(path) + convertParam(params);
        }

        private String removeSlash(String path) {

            if (path.startsWith("/")) {

                return StringUtils.substringAfter(path, "/");
            }

            return path;
        }

        private String convertParam(HashMap<String, String> params) {

            if (params.isEmpty()) {

                return EMPTY;
            }

            return "?" + params.entrySet().stream()
                    .map(this::convertToString)
                    .collect(Collectors.joining("&"));
        }

        private String convertToString(Map.Entry<String, String> entry) {

            return entry.getKey() + "=" + entry.getValue();
        }
    }
}



