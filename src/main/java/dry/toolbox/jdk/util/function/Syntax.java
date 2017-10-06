package dry.toolbox.jdk.util.function;

import java.util.function.*;

public class Syntax {

    public void runPredicate(Predicate<String> condition, String text) {
        System.out.println("Predicate test for " + text + " is " + condition.test(text));
    }

    public void runConsumer(Consumer<String> actions, String text) {
        actions.accept(text);
    }

    public void runFunction(Function<String, String> mapper, String text) {
        System.out.println(mapper.apply(text));
    }

    public void runFunction(Function<String, String> mapper, Consumer<String> actions, String text) {
        String result = mapper.apply(text);
        actions.accept(result);
    }

    public void runFunction(Function<String, String> mapper1, Function<String, String> mapper2,
            Consumer<String> actions, String text) {
        String result = mapper1.apply(text);
        result = mapper2.apply(result);
        actions.accept(result);
    }

    public void runFunction(Consumer<String> actions, String text,
            Function<String, String>... mappers) {
        String result = text;
        for (Function<String, String> mapper : mappers) {
            result = mapper.apply(result);
        }
        actions.accept(result);
    }

}
