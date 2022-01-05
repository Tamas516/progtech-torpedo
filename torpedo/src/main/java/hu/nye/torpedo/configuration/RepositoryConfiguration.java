package hu.nye.torpedo.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import hu.nye.torpedo.persistence.GameSavesRepository;
import hu.nye.torpedo.persistence.impl.JdbcGameSavesRepository;
import hu.nye.torpedo.service.map.parser.PlayerBoardParser;
import hu.nye.torpedo.service.util.BoardToStringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * Spring Java configuration class for persistence layer specific Spring Beans.
 */
@Configuration
public class RepositoryConfiguration {

    @Bean
    Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/torpedo", "sa", "123");
    }

    @Bean(destroyMethod = "close")
    GameSavesRepository jdbcGameSavesRepository(Connection connection, BoardToStringUtil boardToStringUtil,
                                                PlayerBoardParser playerBoardParser) {
        return new JdbcGameSavesRepository(connection, boardToStringUtil, playerBoardParser);
    }

}
