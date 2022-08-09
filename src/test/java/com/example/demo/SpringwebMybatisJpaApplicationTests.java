package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.dto.MemoDto;
import com.example.demo.domain.entity.JpaBoard;
import com.example.demo.domain.entity.JpaBoardRepository;
import com.example.demo.mapper.MemoMapper;

@SpringBootTest
class SpringwebMybatisJpaApplicationTests {
	
	@Autowired
	private MemoMapper mapper;
	
	//@Test
	void contextLoads() {
		IntStream.rangeClosed(1, 10).forEach((i)->{
			MemoDto dto=MemoDto.builder().content("안녕하세요-"+i).build();
			mapper.save(dto);
		});
		
	}
	
	//@Test
	void 데이터읽기() {
		mapper.readAll().forEach(System.out::println);
	}
	//@Test
	void 데이터수정() {
		int no=3;
		String content="일괄 수정";
		MemoDto dto=MemoDto.builder().no(no).content(content).createdDate(LocalDateTime.now()).build();
		int result=mapper.update(dto);
		System.out.println("수정>"+result);
	}
	//@Test
	void 데이터삭제() {
		int no=1;
		
		int result=mapper.deleteById(no);
		System.out.println(">>"+result+"데이터 삭제");
	}
	//@Test
	void 컨텐츠데이터검색() {
		String search="일괄";
		List<MemoDto> result=mapper.findBySearch(search);
		System.out.println(result);
		
		result.forEach(System.out::println);
	}
	
	@Autowired
	private JpaBoardRepository repository;
	
	//@Test
	void 생성() {
		IntStream.rangeClosed(1, 100).forEach((i)->{
			repository.save(JpaBoard.builder().title("테스트").content("내용").writer("작성자").build());
		});
		
	}
	//@Test
	void 작성자검색() {
		String writer="작성자";
		
		List<JpaBoard> result=repository.findAllByWriter(writer);
		result.forEach(System.out::println);
	}
}
