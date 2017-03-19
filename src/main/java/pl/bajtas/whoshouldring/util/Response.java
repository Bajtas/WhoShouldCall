package pl.bajtas.whoshouldring.util;

/**
 * Created by Bajtas on 19.03.2017.
 */
public class Response {
    private String message;
    private Type type;

    public Response Response() {
        return this;
    }

    public Response Response(Type type, String message) {
        this.message = message;
        this.type = type;

        return this;
    }

    public Response build(Type type, String message) {
        this.message = message;
        this.type = type;

        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        SUCCESS("SUCCESS"), ERROR("ERROR");

        private final String name;

        Type(String s) {
            name = s;
        }

        public boolean equals(String name) {
            return this.name.equals(name);
        }

        public String toString() {
            return this.name;
        }
    }
}
