# Домашнее задание:
```
class Person {
   private final String lastname;
   private final String firstname;
   private final Byte age;
   // constructor, getters
}
```
Мы хотим взять List человеков и отсортировать их по следующим критериям:
Сначала по фамилии (алфавитный порядок)
Если фамилии совпали - сортируем по имени (алфавитный порядок)
Если и имя совпало - сортируем по возрасту, старшие сверху, младшие снизу
```
List<Person> people = Arrays.asList(
       new Person("Антонов", "Борис", 15),
       new Person("Антонов", "Виктор", 15),
       new Person("Антонов", "Борис", 12),
       new Person("Борисов", "Антон", 9)
);
```
Сделать с помощью стримов
sort, Comparator, thenComparing
