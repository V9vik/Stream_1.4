import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> list = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            list.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long stream1 = list.stream()
                .filter(x -> (x.getAge() < 18))
                .count();
        List<String> secondTask = list.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Призывники: " + secondTask);

        List<String> stream3 = list.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(x -> ((x.getSex() == Sex.MAN) ? x.getAge() <= 65 : x.getAge() <= 60) && x.getAge() >= 18)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(x -> x.getFamily() + " " + x.getName())
                .collect(Collectors.toList());
        System.out.println("Отсортированный список потенциально работоспособных людей с высшим образованием: " + stream3);
        System.out.println("Число людей меньше 18: " + stream1);// если ставить перед кол во людей, то тяжело найти цифры)

    }
}