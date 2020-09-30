package src.Models;

import src.Enums.ImagePathEnum;

public class Alert {
    private Integer id;
    private Image image;

    public Alert() {
        this.id = 1;
        this.image = new Image(ImagePathEnum.ALERT);
    }
}
