package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.dto.MemoDto;

@Mapper
public interface MemoMapper {

	void save(MemoDto dto);

	List<MemoDto> readAll();

	int update(MemoDto dto);

	int deleteById(int no);

	List<MemoDto> findBySearch(String search);

	

}
