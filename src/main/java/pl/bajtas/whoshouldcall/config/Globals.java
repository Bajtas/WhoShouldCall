package pl.bajtas.whoshouldcall.config;

/**
 * Created by Bajtas on 25.05.2017.
 */
public class Globals {
    public enum USER_ROLES {
        ADMIN("Admin"), MOD("Moderator"), USER("User");

        private final String role;

        USER_ROLES(String role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return role;
        }
    }
}
