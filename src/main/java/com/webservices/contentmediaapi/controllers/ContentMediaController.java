package com.webservices.contentmediaapi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.webservices.contentmediaapi.daos.IContentMediaDao;
import com.webservices.contentmediaapi.models.ContentMedia;
import com.webservices.contentmediaapi.models.User;
import com.webservices.contentmediaapi.utils.JsonNodeUtil;

@RestController
public class ContentMediaController {

	@Autowired
	IContentMediaDao cmDao;
	
	@PostMapping("/content-media")
	public List<ContentMedia> getAllContentMedia(@RequestBody ObjectNode json){
		User u = new User(json);
		Optional<List<ContentMedia>> result = cmDao.findContentMediaByIsActiveAndCountryAccepted(true,u.getCountry());
		return result.get();
	}
	
	@PostMapping("/content-media/create")
	public ContentMedia createContentMedia(@RequestBody ObjectNode json){
		Integer genre = JsonNodeUtil.getJsonNodeAsInteger(json, "genre");
		ContentMedia c = new ContentMedia(json);
		ContentMedia cm = cmDao.save(c);
		return cm;
	}
	
	@DeleteMapping("/content-media/{id}")
	public String deleteContentMedia(@PathVariable int id) {
		String message = "";
		Optional<ContentMedia> result = cmDao.findById(id);
		if(result != null) {
			ContentMedia cm = result.get();
			cm.setActive(false);
			message = "Suppression réussite";
		}else {
			message = "L'id ne correspond à aucun contenu de media";
		}
		return message;
	}
	
	@PutMapping("/content-media/{id}")
	public String updateContentMedia(@RequestBody ObjectNode json, @PathVariable int id) {
		String message = "";
		Optional<ContentMedia> result = cmDao.findById(id);
		if(result != null) {
			ContentMedia cm = result.get();
			cm.updateData(json);
			message = "Modification réussite";
		}else {
			message = "L'id ne correspond à aucun contenu de media";
		}
		return message;
	}
}
