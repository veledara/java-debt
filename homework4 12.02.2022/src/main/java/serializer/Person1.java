package serializer;

@JsonSerializable
@IgnoreNull
public class Person1 {
    @JsonElement
    private String name;
    @JsonElement(value = "AGE")
    private String age;
    @JsonElement
    private String nullString;

    private String notAnnotatedString;

    public Person1(String name, String age, String nullString, String notAnnotatedString) {
        this.name = name;
        this.age = age;
        this.nullString = nullString;
        this.notAnnotatedString = notAnnotatedString;
    }
}
