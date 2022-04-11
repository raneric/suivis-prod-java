package com.sgg.suivisprod;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static com.sgg.suivisprod.utils.TimeUtils.convertDoubleTimeAsFormatedString;

@SpringBootTest
class TimeUtilsTests {

	@Test
	void testTimeFormater() {
		assertThat(convertDoubleTimeAsFormatedString(1.5)).isEqualTo("01:30");
		assertThat(convertDoubleTimeAsFormatedString(33.75)).isEqualTo("33:45");
	}

}
