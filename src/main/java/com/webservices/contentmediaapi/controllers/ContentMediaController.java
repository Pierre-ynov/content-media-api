package com.webservices.contentmediaapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
		return cmDao.findAll();
	}
	
	@PostMapping("/content-media/create")
	public ContentMedia createContentMedia(@RequestBody ObjectNode json){
		Integer genre = JsonNodeUtil.getJsonNodeAsInteger(json, "genre");
		ContentMedia c = new ContentMedia(json);
		ContentMedia cm = cmDao.save(c);
		return cm;
	}
}
