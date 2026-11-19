package kr.hs.dgsw.jetty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JettyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JettyApplication.class, args);
    }

}
