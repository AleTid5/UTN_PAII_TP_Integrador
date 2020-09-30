package src.Enums;

public enum ImagePathEnum {
    ALERT("alerts/"),
    USER("users/");

    private final String path;

    ImagePathEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        final String basePath = "http://mlstatic.com/";

        return basePath + path;
    }
}
