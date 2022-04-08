package com.webservices.contentmediaapi.models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.webservices.contentmediaapi.utils.JsonNodeUtil;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.io.Serializable;
import java.util.UUID;

@Data
@Document
@Inheritance(strategy = InheritanceType.JOINED)
public class ContentMedia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@Field(name = "_id")
    private String id;
    private String title;
    private String url;
    private String category;
    private String globalDescription;
    private boolean isActive;
    private String countryAccepted;
    private String urlPoster;
    private String genre;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public ContentMedia() {

    }

    public ContentMedia(ObjectNode json, String genre) {
        id = UUID.randomUUID().toString();
        isActive = true;
        this.genre = genre;
        updateData(json);
    }

    public void updateData(ObjectNode json) {
        title = JsonNodeUtil.getJsonNodeAsText(json, "title");
        url = JsonNodeUtil.getJsonNodeAsText(json, "url");
        category = JsonNodeUtil.getJsonNodeAsText(json, "category");
        globalDescription = JsonNodeUtil.getJsonNodeAsText(json, "globalDescription");
        countryAccepted = JsonNodeUtil.getJsonNodeAsText(json, "countryAccepted");
    }
}
