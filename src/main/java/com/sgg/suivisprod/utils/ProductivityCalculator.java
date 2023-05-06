package com.sgg.suivisprod.utils;

import java.util.List;

import com.sgg.suivisprod.domain.Task;

public interface ProductivityCalculator {
	double calculate(List<Task> tasks);
}
