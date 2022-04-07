package com.webservices.contentmediaapi.daos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.webservices.contentmediaapi.models.Episode;

public interface IEpisodeDao extends MongoRepository<Episode,String>{

}
