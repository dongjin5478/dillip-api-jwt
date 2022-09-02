package com.dillip.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dillip.api.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
	
//	@Query(value = "select * from tbl_user where user_name =:userName or email_id =:userName",nativeQuery = true)
//	UserEntity getUserName(@Param("userName") String userName);
	
	@Query(value = "SELECT u FROM UserEntity u WHERE u.userName = :userName OR u.emailId = :userName")
	UserEntity getUserName(@Param("userName") String userName);
}
