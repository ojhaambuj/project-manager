package com.projectmanager.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.projectmanager.collection.Project;

/**
 * 
 * @author Ambuj Ojha
 *
 */
public interface ProjectDao extends MongoRepository<Project, String> {

}
