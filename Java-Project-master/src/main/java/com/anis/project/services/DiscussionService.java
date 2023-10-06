package com.anis.project.services;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anis.project.models.Discussion;
import com.anis.project.repositories.DiscussionRepository;

@Service
public class DiscussionService {
	private final DiscussionRepository discussionRepository;

	public DiscussionService(DiscussionRepository discussionRepository) {
		this.discussionRepository = discussionRepository;
	}
	
	// Display all Discussions
	public List<Discussion> allDiscussions() {
		return discussionRepository.findAll();
	}

	// Create a discussion
	public Discussion createDiscussion(Discussion discussion) {
		return discussionRepository.save(discussion);
		
	}

	// Find one
	public Discussion findDiscussion(Long id) {
		Optional<Discussion> optionalDiscussion = discussionRepository.findById(id);
		if (optionalDiscussion.isPresent()) {
			return optionalDiscussion.get();
		} else {
			return null;
		}
	}

	
	// Delete a discussion
	public void deleteDiscussion(Long id) {
		discussionRepository.deleteById(id);
	}

	// Update a discussion
	public Discussion updateDiscussion(Discussion discussion) {
		return discussionRepository.save(discussion);
	}
}
