package dev.dryproject.jdk.util.function;

import java.util.function.*;

public class Button {

    private String id;

    public Button(String id) {
        this.id = id;
    }

    public void onClick(Consumer<String> block) {
        block.accept(id);
    }

}
