package pl.adambanaszkiewicz.four_layers_example.infrastructure_layer.to_this.infrastructure;

import org.springframework.stereotype.Component;
import pl.adambanaszkiewicz.four_layers_example.infrastructure_layer.to_this.FilesystemInterface;

@Component
public class LocalFilesystem implements FilesystemInterface {
    public boolean exists(String filepath) {
        return true; // To implement...
    }
}
