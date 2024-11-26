package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.JoinDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinService {	
	
	private final UserRepository userRepository;
	
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public void joinProcess(JoinDTO joinDTO) {
		
		// 중복 체크
		UserEntity userEntity = new UserEntity();
		
		userEntity.setUsername(joinDTO.getUsername());
		// 비밀번호 암호화 진행 => $2a$10$I15LHOhdT9lcJiEeDYjFXONot.B71SND6O7luYNW3YzNyRq33MNTW << DB에 들어간 비밀번호
		userEntity.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));	
		userEntity.setRole("ROLE_USER");
	
		userRepository.save(userEntity);
		
	}
}
