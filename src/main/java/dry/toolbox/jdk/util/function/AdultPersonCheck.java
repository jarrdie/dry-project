package dry.toolbox.jdk.util.function;

public class AdultPersonCheck implements PersonCheck {

    @Override
    public boolean test(Person person) {
        return person.gender == Person.Sex.MALE
                && person.getAge() >= 18
                && person.getAge() <= 50;
    }

}
