package com.webservices.contentmediaapi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.webservices.contentmediaapi.daos.IContentMediaDao;
import com.webservices.contentmediaapi.models.ContentMedia;
import com.webservices.contentmediaapi.models.ContentMediaResponse;
import com.webservices.contentmediaapi.models.ListContentMediaResponse;
import com.webservices.contentmediaapi.models.User;
import com.webservices.contentmediaapi.utils.JsonNodeUtil;
import com.webservices.contentmediaapi.utils.RequestUserApiUtil;

@RestController
public class ContentMediaController {

	@Autowired
	IContentMediaDao cmDao;
	
	@PostMapping("/content-media")
	public ResponseEntity<ListContentMediaResponse<ContentMedia>> getAllContentMedia(@RequestBody ObjectNode json){
		/*Integer userId = JsonNodeUtil.getJsonNodeAsInteger(json, "userId");
		if(userId == null) {
			return ResponseEntity.status(500).body(new ListContentMediaResponse<ContentMedia>(false,
					"L'id utilisateur n'a pas réussi à être récupéré"));
		}
		ResponseEntity<ObjectNode> resultGetUserById = RequestUserApiUtil.getUserById(userId);
		if(resultGetUserById.getStatusCodeValue() != 200) {
			return ResponseEntity.status(500).body(new ListContentMediaResponse<ContentMedia>(false,
					"Il y a eu un problème lors de la récupération de l'utilisateur"));
		}
		User user = new User(resultGetUserById.getBody());*/
		User user = new User(json);
		Optional<List<ContentMedia>> result = cmDao.findContentMediaByIsActiveAndCountryAccepted(true,user.getCountry());
		return ResponseEntity.ok(new ListContentMediaResponse<ContentMedia>(true,result.get()));
	}
	
	@PostMapping("/content-media/create")
	public ResponseEntity<ContentMediaResponse<ContentMedia>> createContentMedia(@RequestBody ObjectNode json){
		String message = "";
		Integer genre = JsonNodeUtil.getJsonNodeAsInteger(json, "genre");
		if(genre == null) {
			message = "Le genre n'a pas été trouvé dans le corps de la requête";
			System.out.println(message);
			return ResponseEntity.status(500).body(new ContentMediaResponse<ContentMedia>(false,message));
		}
		ContentMedia newContentMedia = new ContentMedia(json);
		newContentMedia.setCategory(newContentMedia.getCategory().toLowerCase());
		return ResponseEntity.ok(new ContentMediaResponse<ContentMedia>(true,cmDao.save(newContentMedia)));
	}
	
	@DeleteMapping("/content-media/{id}")
	public ResponseEntity<ContentMediaResponse<ContentMedia>> deleteContentMedia(@PathVariable int id) {
		Optional<ContentMedia> result = cmDao.findById(id);
		if(result != null) {
			ContentMedia cm = result.get();
			cm.setActive(false);
			return ResponseEntity.ok(new ContentMediaResponse<ContentMedia>(true,"Suppression réussite"));
		}else {
			return ResponseEntity.status(500).body(new ContentMediaResponse<ContentMedia>(false,
					"L'id ne correspond à aucun contenu de media"));
		}
	}
	
	@PutMapping("/content-media/{id}")
	public ResponseEntity<ContentMediaResponse<ContentMedia>> updateContentMedia(@RequestBody ObjectNode json, @PathVariable int id) {
		Optional<ContentMedia> result = cmDao.findById(id);
		if(result != null) {
			ContentMedia cm = result.get();
			cm.updateData(json);
			return ResponseEntity.ok(new ContentMediaResponse<ContentMedia>(true,"Modification réussite"));
		}else {
			return ResponseEntity.status(500).body(new ContentMediaResponse<ContentMedia>(false,
					"L'id ne correspond à aucun contenu de media"));
		}
	}
	
	@PostMapping("/content-media/category")
	public ResponseEntity<ListContentMediaResponse<ContentMedia>> getAllContentMediaByCategory(@RequestBody ObjectNode json){
		/*Integer userId = JsonNodeUtil.getJsonNodeAsInteger(json, "userId");
		if(userId == null) {
			return ResponseEntity.status(500).body(new ListContentMediaResponse<ContentMedia>(false,
					"L'id utilisateur n'a pas réussi à être récupéré"));
		}
		ResponseEntity<ObjectNode> resultGetUserById = RequestUserApiUtil.getUserById(userId);
		if(resultGetUserById.getStatusCodeValue() != 200) {
			return ResponseEntity.status(500).body(new ListContentMediaResponse<ContentMedia>(false,
					"Il y a eu un problème lors de la récupération de l'utilisateur"));
		}
		User user = new User(resultGetUserById.getBody());*/
		User user = new User(json);
		if(!user.getStatus().equals("ACTIVE")) {
			return ResponseEntity.status(500).body(new ListContentMediaResponse<ContentMedia>(false,
					"L'utilisateur n'est pas actif"));
		}
		String categorySelected = JsonNodeUtil.getJsonNodeAsText(json, "category");
		if(categorySelected == null) {
			return ResponseEntity.status(500).body(new ListContentMediaResponse<ContentMedia>(false,
					"La catégorie n'a pas été trouvé dans le corps de la requête"));
		}
		Optional<List<ContentMedia>> resultOptional = cmDao.findContentMediaByIsActiveAndCountryAcceptedAndCategory(true,user.getCountry(),categorySelected.toLowerCase());
		
		List<ContentMedia> result = resultOptional.get();
		
		return ResponseEntity.ok(new ListContentMediaResponse<ContentMedia>(true,result));
	}
}
