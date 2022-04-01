package com.webservices.contentmediaapi.models;

import javax.persistence.PrimaryKeyJoinColumn;

import org.springframework.data.mongodb.core.mapping.*;

import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.Data;

@Data
@Document
@PrimaryKeyJoinColumn(name = "id")
public class Serie extends ContentMedia {

	public Serie(ObjectNode json) {
		super(json,"serie");
	}
	
	public Serie() {
		super();
	}
}
