# Домашнее задание:
Пишем
@JsonSerialazable
@JsonElement -  с возможностью задать имя поля в которе сериализуется значение
@IgnoreNull - на класс Target
Чтобы игнорировать для такого класса нуллы
Если ее нет - то сериализовать null как "null"
```
@JsonSerializable
public final class Person {

    @JsonElement
    private String firstName;

    @JsonElement("name")
    private String lastName;

    @JsonElement()
    private String age;

    private String weight;
{
"age": 14,
"firstName": "Aleksandr"
"name" : "Kuchuk",
"weight": "null"
}
```
Это должен быть мавен проект-библиотека + отличное покрытие тестами
