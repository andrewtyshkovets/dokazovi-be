package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.user.ExpertDTO;
import com.softserveinc.dokazovi.dto.user.ExpertPreviewDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;


public interface UserService {

	ExpertDTO findExpertById(Integer userId);

	UserEntity findByEmail(String email);

	Page<UserEntity> findAll(Pageable pageable);

	Page<ExpertPreviewDTO> getRandomExpertPreview(Pageable pageable, Set<Integer> directionsIds);

}
