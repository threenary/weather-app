
package com.threenary.weather.domain;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "units",
    "title",
    "link",
    "description",
    "language",
    "lastBuildDate",
    "ttl",
    "location",
    "wind",
    "atmosphere",
    "astronomy",
    "image",
    "item"
})
public class Channel {

    @JsonProperty("units")
    private Units units;
    @JsonProperty("title")
    private String title;
    @JsonProperty("link")
    private String link;
    @JsonProperty("description")
    private String description;
    @JsonProperty("language")
    private String language;
    @JsonProperty("lastBuildDate")
    private String lastBuildDate;
    @JsonProperty("ttl")
    private String ttl;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("wind")
    private Wind wind;
    @JsonProperty("atmosphere")
    private Atmosphere atmosphere;
    @JsonProperty("astronomy")
    private Astronomy astronomy;
    @JsonProperty("image")
    private Image image;
    @JsonProperty("item")
    private Item item;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("units")
    public Units getUnits() {
        return units;
    }

    @JsonProperty("units")
    public void setUnits(Units units) {
        this.units = units;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("lastBuildDate")
    public String getLastBuildDate() {
        return lastBuildDate;
    }

    @JsonProperty("lastBuildDate")
    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    @JsonProperty("ttl")
    public String getTtl() {
        return ttl;
    }

    @JsonProperty("ttl")
    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonProperty("wind")
    public Wind getWind() {
        return wind;
    }

    @JsonProperty("wind")
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    @JsonProperty("atmosphere")
    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    @JsonProperty("atmosphere")
    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    @JsonProperty("astronomy")
    public Astronomy getAstronomy() {
        return astronomy;
    }

    @JsonProperty("astronomy")
    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    @JsonProperty("image")
    public Image getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(Image image) {
        this.image = image;
    }

    @JsonProperty("item")
    public Item getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(Item item) {
        this.item = item;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
