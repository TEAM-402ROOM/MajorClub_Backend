package com.major.club.domain.application.repository;

import com.major.club.domain.application.domain.Application;
import com.major.club.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByUser(User user);
}
