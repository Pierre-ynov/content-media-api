package com.webservices.contentmediaapi.daos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.webservices.contentmediaapi.models.ContentMedia;

public interface IContentMediaDao extends MongoRepository<ContentMedia,Integer>{
	@Query("{'isActive': ?0, 'countryAccepted': ?1}")
	public Optional<List<ContentMedia>> findContentMediaByIsActiveAndCountryAccepted(Boolean isActive, String countryAccepted);
}
