package com.example.demo.domain.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBoardRepository extends JpaRepository<JpaBoard, Long>{

	List<JpaBoard> findAllByWriter(String writer);
	
	@Query("select j from JpaBoard j where j.writer like '%?1%'")
	List<JpaBoard> aaaa(String writer);

}
