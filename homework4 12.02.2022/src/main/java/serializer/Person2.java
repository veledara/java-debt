package serializer;

@JsonSerializable
public class Person2 {
    @JsonElement
    private String name;
    @JsonElement(value = "AGE")
    private String age;
    @JsonElement
    private String nullString;

    private String notAnnotatedString;

    public Person2(String name, String age, String nullString, String notAnnotatedString) {
        this.name = name;
        this.age = age;
        this.nullString = nullString;
        this.notAnnotatedString = notAnnotatedString;
    }
}

