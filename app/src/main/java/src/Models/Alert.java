package src.Models;

import src.Enums.ImagePathEnum;

public class Alert {
    private Integer id;
    private Image image;

    public Alert() {
        this.id = 1;
        this.image = new Image(ImagePathEnum.ALERT);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
