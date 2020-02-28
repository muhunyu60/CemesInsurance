package model;

public class ClaimModel {
    String title;
    private String type;
    private String description;

    public ClaimModel(String title, String type, String description) {
        this.title = title;
        this.type =  type;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
