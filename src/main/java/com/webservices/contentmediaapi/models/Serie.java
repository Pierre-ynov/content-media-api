package com.webservices.contentmediaapi.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import org.springframework.data.mongodb.core.mapping.*;

import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.Data;

@Data
@Document
@PrimaryKeyJoinColumn(name = "id")
public class Serie extends ContentMedia {

	@OneToMany
	private List<Episode> episodes;
	
	public Serie(ObjectNode json) {
		super(json,"serie");
		episodes = new ArrayList<Episode>();
	}
	
	public Serie() {
		super();
	}
	
	public void addEpisode(Episode episode) {
		episodes.add(episode);
	}
}
