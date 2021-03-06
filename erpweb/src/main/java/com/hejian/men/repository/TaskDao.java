package com.hejian.men.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.hejian.men.entity.Task;

public interface TaskDao extends PagingAndSortingRepository<Task, String>, JpaSpecificationExecutor<Task> {

	Page<Task> findByUserId(String id, Pageable pageRequest);

	@Modifying
	@Query("delete from Task task where task.user.id=?1")
	void deleteByUserId(String id);
}
