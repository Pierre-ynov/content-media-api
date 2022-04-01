package com.webservices.contentmediaapi.daos;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.webservices.contentmediaapi.models.ContentMedia;

public interface IContentMediaDao extends MongoRepository<ContentMedia,String>{
	@Query("{'isActive': ?0, 'countryAccepted': ?1}")
	public Optional<List<ContentMedia>> findContentMediaByIsActiveAndCountryAccepted(Boolean isActive, String countryAccepted);
	
	@Query("{'isActive': ?0, 'countryAccepted': ?1, 'category': ?2}")
	public Optional<List<ContentMedia>> findContentMediaByIsActiveAndCountryAcceptedAndCategory(Boolean isActive, String countryAccepted, String category);
	
	@Query("{'isActive': ?0, 'countryAccepted': ?1, 'genre': ?2}")
	public Optional<List<ContentMedia>> findContentMediaByIsActiveAndCountryAcceptedAndGenre(Boolean isActive, String countryAccepted, String genre);
}
