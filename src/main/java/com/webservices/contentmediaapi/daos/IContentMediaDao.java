package com.webservices.contentmediaapi.daos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.webservices.contentmediaapi.models.ContentMedia;

public interface IContentMediaDao extends MongoRepository<ContentMedia,String>{

}
