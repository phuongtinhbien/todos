package com.example.ui.todos.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {
    private final static long serialVersionUID = 4281516886117883522L;
    private final static String ICON_URL = "http://openweathermap.org/img/w/";
    private final static String ICON_TYPE = ".png";
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icon")
    @Expose
    private String icon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return ICON_URL + icon + ICON_TYPE;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
