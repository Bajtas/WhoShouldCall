package pl.bajtas.whoshouldring.config;

/**
 * Created by Bajtas on 19.03.2017.
 */
public class Globals {
    public enum Roles {
        ADMINISTRATOR("Admin"), USER("User"), MODERATOR("Moderator");

        private final String name;

        private Roles(String s) {
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
}
