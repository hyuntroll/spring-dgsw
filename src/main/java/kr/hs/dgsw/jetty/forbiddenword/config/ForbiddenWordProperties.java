package kr.hs.dgsw.jetty.forbiddenword.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "forbidden-word")
public class ForbiddenWordProperties {
    private boolean enabled = true;
    private List<String> words = new ArrayList<>();
}
