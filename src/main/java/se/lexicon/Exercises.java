package se.lexicon;

import se.lexicon.data.DataStorage;

import se.lexicon.model.Gender;
import se.lexicon.model.Person;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findErik = person -> person.getFirstName().equals("Erik");

        storage.findMany(findErik).forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findFemales = person -> person.getGender().equals(Gender.FEMALE);

        storage.findMany(findFemales);

        System.out.println("----------------------");
    }

    /*
        3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> bornAfter = person -> person.getBirthDate().isEqual(LocalDate.parse("2000-01-01")) || person.getBirthDate().isAfter(LocalDate.parse("2000-01-01"));
        storage.findMany(bornAfter);
        System.out.println("----------------------");
    }

    /*
        4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);
        //Write your code here

        Predicate<Person> findId = person -> person.getId() == 123;
        storage.findOne(findId);

        System.out.println("----------------------");

    }

    /*
        5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findPersonById = person -> {
            if (person.getId() == 456) {
                List<Person> foundPerson = new ArrayList<>();
                foundPerson.add(person);
            }
            return true;
        };

        Function<Person, String> personToString = Person::toString;
        storage.findOneAndMapToString(findPersonById, personToString);

        System.out.println("----------------------");
    }

    /*
        6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findMaleE = person -> person.getGender().equals(Gender.MALE) && person.getFirstName().startsWith("E");

        Function<Person, String> personToString = Person::toString;

        storage.findManyAndMapEachToString(findMaleE, personToString);

        System.out.println("----------------------");
    }

    /*
        7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findAgeUnder10 = person -> person.getBirthDate().isAfter(LocalDate.parse("2011-08-01"));
        Function<Person, String> personToString = Person::toString;
        storage.findManyAndMapEachToString(findAgeUnder10, personToString);

        System.out.println("----------------------");
    }

    /*
        8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findUlf = person -> person.getFirstName().equals("Ulf");
        Consumer<Person> printUlf = System.out::println;

        storage.findAndDo(findUlf, printUlf);

        System.out.println("----------------------");
    }

    /*
        9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> firstNameLast = person -> person.getLastName().contains(person.getFirstName());
        Consumer<Person> printFound = System.out::println;

        storage.findAndDo(firstNameLast, printFound);
        System.out.println("----------------------");
    }

    /*
        10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findPalindrome = person -> {
            StringBuilder sb = new StringBuilder();
            if (person.getFirstName().equals(String.valueOf(sb.reverse())) || person.getLastName().equals(String.valueOf(sb.reverse()))){
                System.out.println(person.getFirstName() + person.getLastName());
            }
            return true;
        };

        Consumer<Person> printPalindrome = person -> System.out.println(person.getFirstName() + person.getLastName());

        storage.findAndDo(findPalindrome, printPalindrome);
        System.out.println("----------------------");
    }

    /*
        11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findFirstNameA = person -> person.getFirstName().startsWith("A");
        Comparator<Person> sortByBirth = (person1, person2) -> person1.getBirthDate().compareTo(person2.getBirthDate());
        storage.findAndSort(findFirstNameA, sortByBirth);

        System.out.println("----------------------");
    }

    /*
        12.	Using findAndSort() find everyone born before 1950 sorted reversed by latest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        //Write your code here

        Predicate<Person> bornBefore50 = person -> person.getBirthDate().isBefore(LocalDate.parse("1950-01-01"));
        Comparator<Person> sortByBirth = (person1, person2) -> person2.getBirthDate().compareTo(person1.getBirthDate());

        storage.findAndSort(bornBefore50, sortByBirth).forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);

        //Write your code here
        Comparator<Person> sort = Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName).thenComparing(Person::getBirthDate);
        //Comparator<Person> sortLambda = Comparator.comparing((p1,p2) -> p1.getLastName().compareTo(p2.getLastName()));

        storage.findAndSort(sort).forEach(System.out::println);

        System.out.println("----------------------");
    }
}
