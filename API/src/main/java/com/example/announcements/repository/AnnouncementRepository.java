package com.example.announcements.repository;


import com.example.announcements.models.Announcement;
import com.example.announcements.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Repository
public interface  AnnouncementRepository extends JpaRepository<Announcement, Integer> {
	public List<Announcement> findByCategoryId(Category categoryId);
}
