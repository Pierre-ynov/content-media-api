package com.webservices.contentmediaapi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.webservices.contentmediaapi.daos.IContentMediaDao;
import com.webservices.contentmediaapi.daos.IEpisodeDao;
import com.webservices.contentmediaapi.models.ContentMedia;
import com.webservices.contentmediaapi.models.Episode;
import com.webservices.contentmediaapi.models.EpisodeResponse;
import com.webservices.contentmediaapi.models.ListEpisodeResponse;
import com.webservices.contentmediaapi.models.Serie;
import com.webservices.contentmediaapi.utils.JsonNodeUtil;

@RestController
public class EpisodeController {

	
	@Autowired
	IEpisodeDao eDao;
	
	@Autowired
	IContentMediaDao cmDao;
	
	@GetMapping("/episode")
	public ResponseEntity<ListEpisodeResponse> getAllEpisodes(@RequestBody ObjectNode json){
		List<Episode> result = eDao.findAll();
		return ResponseEntity.ok(new ListEpisodeResponse(true,result));
	}
	
	@PostMapping("/episode/create")
	public ResponseEntity<EpisodeResponse> createEpisode(@RequestBody ObjectNode json){
		String contentMediaId = JsonNodeUtil.getJsonNodeAsText(json, "contentMediaId");
		if(contentMediaId == null) {
			return ResponseEntity.status(500).body(new EpisodeResponse(false,
					"L'id du contenu de média n'a pas été trouvé dans le corps de la requête."));
		}
		Optional<ContentMedia> result = cmDao.findById(contentMediaId);
		if(result.isEmpty()) {
			return ResponseEntity.status(500).body(new EpisodeResponse(false,
					"L'id ne correspond à aucun contenu de media."));
		}
		Episode episode = new Episode(json);
		eDao.save(episode);
		Serie serie = (Serie)result.get();
		serie.addEpisode(episode);
		cmDao.save(serie);
		return ResponseEntity.ok(new EpisodeResponse(true,episode));
	}
	
	@GetMapping("/episode/{id}")
	public ResponseEntity<EpisodeResponse> getEpisodeById(@PathVariable String id) {
		Optional<Episode> result = eDao.findById(id);
		if(!result.isEmpty()) {
			return ResponseEntity.ok(new EpisodeResponse(true, result.get()));
		}else {
			return ResponseEntity.status(500).body(new EpisodeResponse(false,
					"L'id ne correspond à aucun épisode."));
		}
	}
	
	@PutMapping("/episode/{id}")
	public ResponseEntity<EpisodeResponse> updateEpisode(@RequestBody ObjectNode json, @PathVariable String id) {
		Optional<Episode> result = eDao.findById(id);
		if(!result.isEmpty()) {
			Episode episodeToUpdate = result.get();
			episodeToUpdate.updateData(json);
			eDao.save(episodeToUpdate);
			return ResponseEntity.ok(new EpisodeResponse(true,"Modification réussite"));
		}else {
			return ResponseEntity.status(500).body(new EpisodeResponse(false,
					"L'id ne correspond à aucun episode."));
		}
	}
}
