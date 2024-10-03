package pl.adambanaszkiewicz.four_layers_example.user_interface_layer.from_this.application.query;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GetCartContents {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GetCartContents(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> get(String userId) {
        return jdbcTemplate.queryForList(
            "SELECT p.name, li.productId, li.qty FROM [...]",
            userId
        );
    }
}
