package serializer;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonSerializableTest {
    Serializer serializer = new Serializer();

    @Test
    public void correctSerializationObjectWithIgnoreNullAnnotation() throws NotAnnotatedObjectSerializationException,
            NullObjectSerializationException, IllegalAccessException {
        Person1 person = new Person1("Misha", "19", null, "random_text");
        String personInJson = serializer.serializeToJson(person);
        String expectedString = "{ \n" +
                "\"name\": \"Misha\" \n" +
                "\"AGE\": \"19\" \n" +
                "} \n";
        assertEquals(personInJson, expectedString);
    }

    @Test
    public void correctSerializationObjectWithoutIgnoreNllAnnotation() throws NotAnnotatedObjectSerializationException,
            NullObjectSerializationException, IllegalAccessException {
        Person2 person = new Person2("Misha", "19", null, "random_text");
        String personInJson = serializer.serializeToJson(person);
        String expectedString = "{ \n" +
                "\"name\": \"Misha\" \n" +
                "\"AGE\": \"19\" \n" +
                "\"nullString\": \"null\" \n" +
                "} \n";
        assertEquals(personInJson, expectedString);
    }

    @Test
    public void throwExceptionWhenTryClassWithoutAnnotation() throws NotAnnotatedObjectSerializationException,
            NullObjectSerializationException, IllegalAccessException {
        Person3 person = new Person3("Misha", "19", "null", "random_text");
        assertThrows(NotAnnotatedObjectSerializationException.class,
                () -> {
                    serializer.serializeToJson(person);
                });
    }

    @Test
    public void throwExceptionWhenObjectIsNull() throws NotAnnotatedObjectSerializationException,
            NullObjectSerializationException, IllegalAccessException {
        assertThrows(NullObjectSerializationException.class,
                () -> {
                    serializer.serializeToJson(null);
                });
    }
}
