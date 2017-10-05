package dev.dryproject.jdk.util.function;

import org.junit.*;

public class ButtonTest {

    @Test
    public void testOnClick() {
        Button btn1 = new Button("btn1");
        btn1.onClick(id -> System.out.println("Button clicked: " + id));
    }

}
