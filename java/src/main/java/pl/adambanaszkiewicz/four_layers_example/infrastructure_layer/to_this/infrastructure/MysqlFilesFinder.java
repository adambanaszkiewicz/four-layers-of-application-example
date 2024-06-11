package pl.adambanaszkiewicz.four_layers_example.infrastructure_layer.to_this.infrastructure;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import pl.adambanaszkiewicz.four_layers_example.infrastructure_layer.to_this.FilesFinderInterface;
import pl.adambanaszkiewicz.four_layers_example.infrastructure_layer.to_this.FileDTO;

@Component
public class MysqlFilesFinder implements FilesFinderInterface {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MysqlFilesFinder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public FileDTO find(int fileId) {
        return jdbcTemplate.queryForObject(
            "SELECT filepath, filename, expiration_date FROM files WHERE id = ? LIMIT 1",
            FileDTO.class,
            fileId
        );
    }
}
