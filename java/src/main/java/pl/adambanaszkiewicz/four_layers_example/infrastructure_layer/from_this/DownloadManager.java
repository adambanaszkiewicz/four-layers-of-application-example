package pl.adambanaszkiewicz.four_layers_example.infrastructure_layer.from_this;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DownloadManager {

    private final DataSource dataSource;
    private final String root;

    public DownloadManager(DataSource dataSource, String root) {
        this.dataSource = dataSource;
        this.root = root;
    }

    public boolean fileExistsOnServer(int fileId) {
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT filepath, filename, expiration_date FROM files WHERE id = ?");
        ) {
            statement.setInt(1, fileId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String filename = resultSet.getString("filename");
                    return Files.exists(Paths.get(root, filename));
                }
            }
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
        }

        return false;
    }
}
