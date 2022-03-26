package serializer;

import java.lang.reflect.Field;

public class Serializer {
    public boolean isSerializable(Object object) throws NullObjectSerializationException,
            NotAnnotatedObjectSerializationException {
        if (object == null) {
            throw new NullObjectSerializationException("Can not serialize null object");
        } else if (!object.getClass().isAnnotationPresent(JsonSerializable.class)) {
            throw new NotAnnotatedObjectSerializationException("Object is not annotated with @JsonSerializable.");
        } else {
            return true;
        }
    }

    public String serializeToJson(Object object) throws NotAnnotatedObjectSerializationException,
            NullObjectSerializationException, IllegalAccessException {
        StringBuilder jsonString = new StringBuilder();
        if (isSerializable(object)) {
            jsonString.append("{ \n");
            Class<?> clazz = object.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                boolean accessibleChanged = false;
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                    accessibleChanged = true;
                }
                if (field.isAnnotationPresent(JsonElement.class)) {
                    String fieldName;
                    if (field.getAnnotation(JsonElement.class).value().equals("")) {
                        fieldName = field.getName();
                    } else {
                        fieldName = field.getAnnotation(JsonElement.class).value();
                    }
                    Object value = field.get(object);
                    if (value == null && !object.getClass().isAnnotationPresent(IgnoreNull.class)) {
                        jsonString.append("\"").append(fieldName).append("\": ").append("\"null\" \n");
                    } else if (value != null) {
                        jsonString.append("\"").append(fieldName).append("\": ").append("\"").append(value).append("\" \n");
                    }
                }
                if (accessibleChanged) {
                    field.setAccessible(false);
                }
            }
            jsonString.append("} \n");
        }
        return jsonString.toString();
    }
}
