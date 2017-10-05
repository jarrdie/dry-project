package dev.dryproject.jdk.util.function;

import org.junit.*;

public class SyntaxTest {

    private Syntax syntax;

    @Before
    public void setUp() throws Exception {
        syntax = new Syntax();
    }

    /*
    General syntax:
        parameters -> body

    Only one:
        parameter -> expression

    Multiple:
        (parameter,parameter,...) -> {sentence;sentence;return}

     */
    @Test
    public void testRunPredicate() {

        syntax.runPredicate(text -> text.isEmpty(), "");

        syntax.runPredicate(text -> text.equals("1"), "1");

        syntax.runPredicate((text) -> text.equals("2"), "2");

        syntax.runPredicate((text) -> {
            System.out.print("(Multiline lambda expression) ");
            boolean result = !text.isEmpty();
            result &= text.equals("3");
            return result;
        }, "3");

    }

    @Test
    public void testRunConsumer() {

        syntax.runConsumer(text -> System.out.println(text), "hi");

        syntax.runConsumer(text -> {
            System.out.println(text);
            //A return statement is not required
        }, "hi");

    }

    @Test
    public void testRunFunction() {

        // Error: a return is required
        //   syntax.runFunction(text -> System.out.println(text), "hi");
        syntax.runFunction(text -> text + " nice world!!!", "hi");

        syntax.runFunction(text -> text + " nice world!!!",
                result -> System.out.println(result), "hi");

        syntax.runFunction(text -> text + " nice world!!!", text -> text.toUpperCase(),
                result -> System.out.println(result), "hi");

        syntax.runFunction(result -> System.out.println(result), "hi",
                text -> text + " nice world!!!",
                text -> text.toUpperCase(),
                text -> text.replace(" ", "_")
        );

        syntax.runFunction(text -> {
            text += " nice world!!!";
            text = text.toUpperCase();
            text = text.replace(" ", "_");
            return text;
        }, result -> System.out.println(result), "hi");

    }

}
