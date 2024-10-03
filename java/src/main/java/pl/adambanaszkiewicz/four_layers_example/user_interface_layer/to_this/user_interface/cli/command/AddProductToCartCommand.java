package pl.adambanaszkiewicz.four_layers_example.user_interface_layer.to_this.user_interface.cli.command;

import pl.adambanaszkiewicz.four_layers_example.user_interface_layer.to_this.application.usecase.AddProductToCart;
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
public class AddProductToCartCommand implements ApplicationRunner {
    private final AddProductToCart addProductToCart;
    private static final String COMMAND_NAME = "add-product";

    @Autowired
    public AddProductToCartCommand(
        AddProductToCart addProductToCart
    ) {
        this.addProductToCart = addProductToCart;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (args.getNonOptionArgs().contains(COMMAND_NAME)) {
            this.addProductToCart.execute(
                args.getOptionValues("productId").get(0),
                Integer.parseInt(args.getOptionValues("qty").get(0)),
                args.getOptionValues("userId").get(0)
            );
        }
    }
}
