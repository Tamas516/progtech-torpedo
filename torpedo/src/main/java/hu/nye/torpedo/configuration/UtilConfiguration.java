package hu.nye.torpedo.configuration;

import hu.nye.torpedo.service.util.BoardToStringUtil;
import hu.nye.torpedo.service.util.BoardUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Java configuration class for utility Spring Beans.
 */
@Configuration
public class UtilConfiguration {

    @Bean
    BoardUtil boardUtil() {
        return new BoardUtil();
    }

    @Bean
    BoardToStringUtil boardToStringUtil() {
        return new BoardToStringUtil();
    }

}
