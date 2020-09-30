package src.Models;

import src.Enums.ImagePathEnum;

public class Image {
    private String url; // Donde está alojada con su path correspondiente
    private String uri; // Donde se almacenó en la BD

    public Image(ImagePathEnum imagePathEnum) {
        this.url = imagePathEnum.getPath();
    }

    public Image(ImagePathEnum imagePathEnum, String uri) {
        this(imagePathEnum);
        this.uri = uri;
    }

    public String getUrl() {
        return url;
    }

    public String getUri() {
        return uri;
    }
}
