package com.lms.loanplans.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.loanplans.entities.MyUser;
@Repository
public interface MyUserRepository extends JpaRepository<MyUser, String>
{
}
 
