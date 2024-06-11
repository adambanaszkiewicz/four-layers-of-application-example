package pl.adambanaszkiewicz.four_layers_example.infrastructure_layer.to_this;

import java.util.Map;
import pl.adambanaszkiewicz.four_layers_example.infrastructure_layer.to_this.FileDTO;

public interface FilesFinderInterface {
    public FileDTO find(int fileId);
}
