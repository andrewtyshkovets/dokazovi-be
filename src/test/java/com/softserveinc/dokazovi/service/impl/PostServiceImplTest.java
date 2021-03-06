package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.mapper.PostMapper;
import com.softserveinc.dokazovi.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

	@Mock
	private PostRepository postRepository;
	@Mock
	private PostMapper postMapper;
	@Mock
	private Pageable pageable;
	@InjectMocks
	private PostServiceImpl postService;

	private Page<PostEntity> postEntityPage;

	@BeforeEach
	void init() {
		postEntityPage = new PageImpl<>(List.of(new PostEntity(), new PostEntity()));
	}

	@Test
	void findAllByStatus() {
		when(postRepository.findAllByStatus(any(PostStatus.class), any(Pageable.class))).thenReturn(postEntityPage);
		postService.findAllByStatus(PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findImportantPosts() {
		when(postRepository.findAllByImportantIsTrueAndStatus(any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findImportantPosts(pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByMainDirection() {
		Integer directionId = 1;
		when(postRepository.findAllByMainDirectionIdAndStatus(
				any(Integer.class), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByMainDirection(directionId, null, null, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByMainDirectionAndType() {
		Integer directionId = 1;
		Integer typeId = 1;
		when(postRepository.findAllByMainDirectionIdAndTypeIdAndStatus(any(Integer.class),
				any(Integer.class), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByMainDirection(directionId, typeId, null, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByMainDirectionAndTags() {
		Integer directionId = 1;
		Set<Integer> tags = Set.of(1, 2, 3, 4);
		when(postRepository.findAllByMainDirectionIdAndTagsIdInAndStatus(
				any(Integer.class), anySet(), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByMainDirection(directionId, null, tags, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}

	@Test
	void findAllByMainDirectionAndTypeAndTags() {
		Integer directionId = 1;
		Integer typeId = 1;
		Set<Integer> tags = Set.of(1, 2, 3, 4);
		when(postRepository.findAllByMainDirectionIdAndTypeIdAndTagsIdInAndStatus(any(Integer.class),
				any(Integer.class), anySet(), any(PostStatus.class), any(Pageable.class)))
				.thenReturn(postEntityPage);
		postService.findAllByMainDirection(directionId, typeId,tags, PostStatus.PUBLISHED, pageable);
		verify(postMapper, times(postEntityPage.getNumberOfElements())).toPostDTO(any(PostEntity.class));
	}
}