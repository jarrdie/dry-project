package dev.dryproject.jdk.util.function;

import static dev.dryproject.jdk.util.function.Person.Sex.*;
import static dev.dryproject.jdk.util.function.Person.*;
import static dev.dryproject.jdk.util.function.Person.Sex.*;
import java.time.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
import static org.junit.Assert.*;

/*
  https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 */
public class PersonTest {

    private Person pablo;
    private Person andres;
    private Person mario;
    private List<Person> roster;

    @Before
    public void setUp() throws Exception {
        pablo = Person.create();
        pablo.name = "Pablo";
        pablo.gender = MALE;
        pablo.birthday = LocalDate.of(1977, Month.SEPTEMBER, 12);
        pablo.email = "jarroyod@saludcastillayleon.es";

        andres = Person.create();
        andres.name = "Andrés";
        andres.gender = MALE;
        andres.birthday = LocalDate.of(2013, Month.MARCH, 31);
        andres.email = "";

        mario = Person.create();
        mario.name = "Mario";
        mario.gender = MALE;
        mario.birthday = LocalDate.of(2016, Month.JUNE, 12);
        mario.email = "";

        roster = new ArrayList<>();
        roster.add(pablo);
        roster.add(andres);
        roster.add(mario);
    }

    @Test
    public void testPrintPerson() {
    }

    @Test
    public void testCreate() {
    }

    @Test
    public void testGetAge() {
        int age = pablo.getAge();
        assertTrue(age >= 40);
    }

    /*  Approach 1
    Create Methods That Search for Members That Match One Characteristic
     */
    @Test
    public void testPrintPersonsOlderThan() {
        printPersonsOlderThan(roster, 2);
    }

    //  Approach 2: Create More Generalized Search Methods
    /*  Approach 3
    Specify Search Criteria Code in a Local Class
     */
    @Test
    public void testPrintPersonsWithLocalClass() {
        PersonCheck check = new AdultPersonCheck();
        printPersonsWithLocalClass(roster, check);
    }

    /*  Approach 4
    Specify Search Criteria Code in an Anonymous Class
     */
    @Test
    public void testPrintPersonsWithAnonymousClass() {
        printPersonsWithLocalClass(roster, new PersonCheck() {
            public boolean test(Person p) {
                return p.gender == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 50;
            }
        });
    }

    /*  Approach 5
    Specify Search Criteria Code with a Lambda Expression
     */
    @Test
    public void testPrintPersonsWithLambda() {
        printPersonsWithLocalClass(roster, (Person p) -> p.gender == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 50);
        //printPersons(roster, (Person p) -> 12); //Uncomment this and it won't compile
    }

    @Test
    public void testPrintAllPersonsWithLambda() {
        printPersonsWithLocalClass(roster, (Person p) -> true);
    }

    @Test
    public void testPrintAnyPersonsWithLambda() {
        printPersonsWithLocalClass(roster, (Person p) -> false);
    }

    @Test
    public void testPrintAllPersonsWithLambdaMultiline() {
        printPersonsWithLocalClass(roster, (Person p) -> {
            return false;
        });
    }

    /*  Approach 6
    Use Standard Functional Interfaces with Lambda Expressions -> Predicate
    Custom interfaces (PersonCheck) not required
    Follow the convention
    This is the programming style used in Vert.x
     */
    @Test
    public void testPrintPersonsWithPredicate() {
        printPersonsWithPredicate(roster, p -> p.gender == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 50);
    }

    /*  Approach 7
    Use Lambda Expressions Throughout Your Application
     */
    @Test
    public void testPrintPersonsWithConsumer() {
        printPersonsWithConsumer(roster, p -> p.gender == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 50,
                p -> p.printPerson());
    }

    @Test
    public void testPrintPersonsWithFunction() {
        printPersonsWithFunction(roster, p -> p.gender == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 50,
                p -> p.email,
                email -> System.out.println(email));
    }

    /*  Approach 8
    Use Generics More Extensively
     */
    @Test
    public void testPrintProcessElements() {
        processElements(roster, p -> p.gender == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 50,
                p -> p.email,
                email -> System.out.println(email));
    }

    /*  Approach 9
    Use Aggregate Operations That Accept Lambda Expressions as Parameters
     */
    @Test
    public void testPrintStream() {
        roster.stream()
                .filter(
                        p -> p.gender == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 50)
                .map(p -> p.email)
                .forEach(email -> System.out.println(email));
    }

}
