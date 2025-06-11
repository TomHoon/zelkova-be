package com.my.zelkova_back.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.zelkova_back.test.entity.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>{

}
