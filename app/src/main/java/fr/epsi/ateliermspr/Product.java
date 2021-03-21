package fr.epsi.ateliermspr;

import org.json.JSONObject;

public class Product {
    private String id;
    private String name;
    private String category;
    private String description;
    private String pictureUrl;


    public Product(JSONObject o){
        this.setId(o.optString("id", ""));
        this.setCategory(o.optString("category", ""));
        this.setName(o.optString("title", ""));
        this.setDescription(o.optString("description", ""));
        this.setPictureUrl(o.optString("products_url", ""));
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getShotDescription(){
        if (this.description.length() > 60){
            return this.description.substring(0, 30) + " ..."
                    .replaceAll("\n", " ");
        }
        return this.description
                .replaceAll("\n", " ");
    }

    public String getShotName(){
        if(this.name.length() > 22){
            return this.name.substring(0, 19) + " ...";
        }

        return this.name;
    }
}
