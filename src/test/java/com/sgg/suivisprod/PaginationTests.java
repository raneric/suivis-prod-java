package com.sgg.suivisprod;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sgg.suivisprod.utils.PaginationHelper;

@SpringBootTest
public class PaginationTests {

	PaginationHelper pagination;
	
	@Test
	void currentPageLessThanFiveShoudlReturnOneToFive() {
		pagination = new PaginationHelper(100, 3);
		pagination.build();
		assertThat(pagination.getPagination().get(0)).isEqualTo(1);
		assertThat(pagination.getPagination().get(4)).isEqualTo(5);
	}
	
	@Test
	void currentPageLessThanTenShoudlReturnSixToTen() {
		pagination = new PaginationHelper(100, 8);
		pagination.build();
		assertThat(pagination.getPagination().get(0)).isEqualTo(6);
		assertThat(pagination.getPagination().get(4)).isEqualTo(10);
	}
	
	@Test
	void lastPageShouldReturnLastFivePage() {
		pagination = new PaginationHelper(100, 13);
		pagination.build();
		assertThat(pagination.getPagination().get(0)).isEqualTo(9);
		assertThat(pagination.getPagination().get(4)).isEqualTo(13);
	}
}
