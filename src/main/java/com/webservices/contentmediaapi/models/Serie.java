package com.webservices.contentmediaapi.models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.ArrayList;
import java.util.List;

@Data
@Document
@PrimaryKeyJoinColumn(name = "id")
public class Serie extends ContentMedia {
    @OneToMany
    private List<Episode> episodes;

    public Serie(ObjectNode json) {
        super(json, "serie");
        episodes = new ArrayList<Episode>();
    }

    public Serie() {
        super();
    }

    public void addEpisode(Episode episode) {
        episodes.add(episode);
    }
}
