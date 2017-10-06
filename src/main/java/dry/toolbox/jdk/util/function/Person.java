package dry.toolbox.jdk.util.function;

import static java.lang.System.*;
import java.time.*;
import java.util.*;
import java.util.function.*;

public class Person {

    public enum Sex {
        MALE, FEMALE
    }

    String name;
    Sex gender;
    LocalDate birthday;
    String email;

    public int getAge() {
        if (birthday == null) {
            return -1;
        }
        LocalDate now = LocalDate.now();
        return Period.between(birthday, now).getYears();
    }

    public void printHi() {
        System.out.println("Hi!");
    }

    public void printPerson() {
        System.out.println("-Name: " + name + lineSeparator()
                + "-Gender: " + gender + lineSeparator()
                + "-Birthday: " + birthday + lineSeparator()
                + "-Email: " + email + lineSeparator()
        );
    }

    public static Person create() {
        return new Person();
    }

    public static void printPersonsOlderThan(List<Person> roster, int age) {
        for (Person p : roster) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

    public static void printPersonsWithLocalClass(List<Person> roster, PersonCheck check) {
        for (Person person : roster) {
            if (check.test(person)) {
                person.printPerson();
            }
        }
    }

    /*

    Using the same method name with the predicate creates a conflict with the custom
    interface since both have a method that returns a boolean.

    public static void printPersons(List<Person> roster, Predicate<Person> check) {
        ...
    }


     */
    public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {
        for (Person person : roster) {
            if (tester.test(person)) {
                person.printPerson();
            }
        }
    }

    public static void printPersonsWithConsumer(List<Person> roster, Predicate<Person> tester,
            Consumer<Person> voidBlock) {
        for (Person person : roster) {
            if (tester.test(person)) {
                voidBlock.accept(person);
            }
        }
    }

    public static void printPersonsWithFunction(List<Person> roster, Predicate<Person> tester,
            Function<Person, String> mapper, Consumer<String> block) {
        for (Person person : roster) {
            if (tester.test(person)) {
                String result = mapper.apply(person);
                block.accept(result);
            }
        }
    }

    public static <X, Y> void processElements(Iterable<X> source, Predicate<X> tester,
            Function<X, Y> mapper, Consumer<Y> block) {
        for (X p : source) {
            if (tester.test(p)) {
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

}
