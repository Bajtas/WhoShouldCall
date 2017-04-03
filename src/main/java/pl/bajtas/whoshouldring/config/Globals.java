package pl.bajtas.whoshouldring.config;

/**
 * Created by Bajtas on 19.03.2017.
 */
public class Globals {
    public enum Roles {
        ADMINISTRATOR("Admin"), USER("User"), MODERATOR("Moderator");

        private final String name;

        Roles(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) {
            // (otherName == null) check is not needed because name.equals(null) returns false
            return name.equals(otherName);
        }

        public String toString() {
            return this.name;
        }
    }

    public static final class DEFAULT_ACCOUNT {
        public static final String ADMINISTRATOR_LOGIN = "root";
        public static final String ADMINISTRATOR_PASSWORD = "123";
        public static final String ADMINISTRATOR_EMAIL = "bajtas@gmail.com";
    }
}
