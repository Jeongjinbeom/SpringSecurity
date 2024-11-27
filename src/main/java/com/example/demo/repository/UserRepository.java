package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

		// 중복확인
		boolean existsByUsername(String username);
		
		
		UserEntity findByUsername(String username);
		
}
