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
import com.webservices.contentmediaapi.models.ContentMedia;
import com.webservices.contentmediaapi.models.ContentMediaResponse;
import com.webservices.contentmediaapi.models.ListContentMediaResponse;
import com.webservices.contentmediaapi.models.Movie;
import com.webservices.contentmediaapi.models.Serie;
import com.webservices.contentmediaapi.models.User;
import com.webservices.contentmediaapi.utils.JsonNodeUtil;

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
		String genre = JsonNodeUtil.getJsonNodeAsText(json, "genre");
		if(genre == null) {
			return ResponseEntity.status(500).body(new ContentMediaResponse<ContentMedia>(false,
					"Le genre n'a pas été trouvé dans le corps de la requête."));
		}
		genre = genre.toLowerCase();
		
		switch(genre) {
		case "movie":
			Movie newMovie = new Movie(json);
			if(newMovie.getCategory() != null) {
				newMovie.setCategory(newMovie.getCategory().toLowerCase());
			}
			return ResponseEntity.ok(new ContentMediaResponse<ContentMedia>(true,cmDao.save(newMovie)));
		case "serie":
			Serie newSerie = new Serie(json);
			if(newSerie.getCategory() != null) {
				newSerie.setCategory(newSerie.getCategory().toLowerCase());
			}
			return ResponseEntity.ok(new ContentMediaResponse<ContentMedia>(true,cmDao.save(newSerie)));
		default:
			return ResponseEntity.status(500).body(new ContentMediaResponse<ContentMedia>(false,
					"Le genre fourni ne correspond à aucun genre connu."));
		}
	}
	
	@DeleteMapping("/content-media/{id}")
	public ResponseEntity<ContentMediaResponse<ContentMedia>> deleteContentMedia(@PathVariable String id) {
		Optional<ContentMedia> result = cmDao.findById(id);
		if(!result.isEmpty()) {
			ContentMedia cm = result.get();
			cm.setActive(false);
			return ResponseEntity.ok(new ContentMediaResponse<ContentMedia>(true,"Suppression réussite."));
		}else {
			return ResponseEntity.status(500).body(new ContentMediaResponse<ContentMedia>(false,
					"L'id ne correspond à aucun contenu de media."));
		}
	}
	
	@GetMapping("/content-media/{id}")
	public ResponseEntity<ContentMediaResponse<ContentMedia>> getContentMediaById(@PathVariable String id) {
		System.out.println(id);
		Optional<ContentMedia> result = cmDao.findById(id);
		System.out.println(result);
		if(!result.isEmpty()) {
			return ResponseEntity.ok(new ContentMediaResponse<ContentMedia>(true, result.get()));
		}else {
			return ResponseEntity.status(500).body(new ContentMediaResponse<ContentMedia>(false,
					"L'id ne correspond à aucun contenu de media."));
		}
	}
	
	@PutMapping("/content-media/{id}")
	public ResponseEntity<ContentMediaResponse<ContentMedia>> updateContentMedia(@RequestBody ObjectNode json, @PathVariable String id) {
		Optional<ContentMedia> result = cmDao.findById(id);
		if(!result.isEmpty()) {
			ContentMedia contentMediaToUpdate = result.get();
			contentMediaToUpdate.updateData(json);
			return ResponseEntity.ok(new ContentMediaResponse<ContentMedia>(true,"Modification réussite"));
		}else {
			return ResponseEntity.status(500).body(new ContentMediaResponse<ContentMedia>(false,
					"L'id ne correspond à aucun contenu de media."));
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
	
	@PostMapping("/content-media/genre")
	public ResponseEntity<ListContentMediaResponse<ContentMedia>> getAllContentMediaByGenre(@RequestBody ObjectNode json){
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
		String genreSelected = JsonNodeUtil.getJsonNodeAsText(json, "genre");
		if(genreSelected == null) {
			return ResponseEntity.status(500).body(new ListContentMediaResponse<ContentMedia>(false,
					"Le genre n'a pas été trouvé dans le corps de la requête"));
		}
		Optional<List<ContentMedia>> resultOptional = cmDao.findContentMediaByIsActiveAndCountryAcceptedAndGenre(true,user.getCountry(),genreSelected.toLowerCase());
		
		List<ContentMedia> result = resultOptional.get();
		
		return ResponseEntity.ok(new ListContentMediaResponse<ContentMedia>(true,result));
	}
	
	@PutMapping("/content-media/{id}/description")
	public ResponseEntity<ContentMediaResponse<ContentMedia>> updateContentMediaOnlyOnGlobalDescription(@RequestBody ObjectNode json, @PathVariable String id) {
		String newDescription = JsonNodeUtil.getJsonNodeAsText(json, "globalDescription");
		if(newDescription == null) {
			return ResponseEntity.status(500).body(new ContentMediaResponse<ContentMedia>(false,
					"Aucune description global n'a été trouvé dans le corps de la requête."));
		}
		Optional<ContentMedia> result = cmDao.findById(id);
		if(result != null) {
			ContentMedia contentMediaToUpdate = result.get();
			contentMediaToUpdate.setGlobalDescription(newDescription);
			return ResponseEntity.ok(new ContentMediaResponse<ContentMedia>(true,"Modification réussite."));
		}else {
			return ResponseEntity.status(500).body(new ContentMediaResponse<ContentMedia>(false,
					"L'id ne correspond à aucun contenu de media."));
		}
	}
}
