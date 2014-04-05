package com.hejian.men.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.hejian.men.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, String> {
	User findByLoginName(String loginName);
}
