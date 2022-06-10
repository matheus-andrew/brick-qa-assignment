package matheus.andrew.brick.app.property;

public class ValueHelper {

    public static String setString(String value) {
        if (value.equalsIgnoreCase("empty")) {
            value = "";
        } else if (value.equalsIgnoreCase("space")) {
            value = " ";
        }

        return value;
    }
}
