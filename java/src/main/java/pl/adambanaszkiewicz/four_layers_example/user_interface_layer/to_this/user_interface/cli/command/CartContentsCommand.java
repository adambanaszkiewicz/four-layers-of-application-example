package pl.adambanaszkiewicz.four_layers_example.user_interface_layer.to_this.user_interface.cli.command;

import pl.adambanaszkiewicz.four_layers_example.user_interface_layer.to_this.application.query.GetCartContents;
import pl.adambanaszkiewicz.four_layers_example.user_interface_layer.to_this.domain.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Component
public class CartContentsCommand implements ApplicationRunner {
    private final GetCartContents getCartContents;
    private static final String COMMAND_NAME = "cart-contents";

    @Autowired
    public CartContentsCommand(
        GetCartContents getCartContents
    ) {
        this.getCartContents = getCartContents;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (args.getNonOptionArgs().contains(COMMAND_NAME)) {
            List<Map<String, Object>> contents = this.getCartContents.get(args.getOptionValues("userId").get(0));

            // Do whatever you want with `contents`...
        }
    }
}
