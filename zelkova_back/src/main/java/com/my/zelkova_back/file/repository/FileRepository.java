package com.my.zelkova_back.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.my.zelkova_back.file.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
	
}
