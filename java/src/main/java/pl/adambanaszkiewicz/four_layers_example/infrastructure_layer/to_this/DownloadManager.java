package pl.adambanaszkiewicz.four_layers_example.infrastructure_layer.to_this;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.adambanaszkiewicz.four_layers_example.infrastructure_layer.to_this.FilesFinderInterface;
import pl.adambanaszkiewicz.four_layers_example.infrastructure_layer.to_this.FilesystemInterface;

import java.util.Map;

@Component
public class DownloadManager {

    private final FilesFinderInterface filesFinder;
    private final FilesystemInterface filesystem;

    @Autowired
    public DownloadManager(
        FilesFinderInterface filesFinder,
        FilesystemInterface filesystem
    ) {
        this.filesFinder = filesFinder;
        this.filesystem = filesystem;
    }

    public boolean fileExistsOnServer(int fileId) {
        FileDTO file = filesFinder.find(fileId);

        if (file == null) {
            return false;
        }

        // Business logic...

        return filesystem.exists(file.filepath);
    }
}
