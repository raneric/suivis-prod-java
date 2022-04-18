package com.sgg.suivisprod;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sgg.suivisprod.services.PaginationService;

@SpringBootTest
public class PaginationTests {

	@Autowired
	PaginationService pagination;
	
	@Test
	void currentPageLessThanFiveShoudlReturnOneToFive() {
		assertThat(pagination.buildPaginationList("ran_eric", 0).get(0)).isEqualTo(1);
		assertThat(pagination.buildPaginationList("ran_eric", 0).get(4)).isEqualTo(5);
	}
	
	@Test
	void currentPageLessThanTenShoudlReturnSixToTen() {
		assertThat(pagination.buildPaginationList("ran_eric", 6).get(0)).isEqualTo(6);
		assertThat(pagination.buildPaginationList("ran_eric", 6).get(4)).isEqualTo(10);
	}
	
	@Test
	void lastPageShouldReturnLastFivePage() {
		int totalPageNumber = pagination.getTotalPageNumber("ran_eric");
		assertThat(pagination.buildPaginationList("ran_eric", totalPageNumber).get(0)).isEqualTo(totalPageNumber-4);
		assertThat(pagination.buildPaginationList("ran_eric", totalPageNumber).get(4)).isEqualTo(totalPageNumber);
	}
}
