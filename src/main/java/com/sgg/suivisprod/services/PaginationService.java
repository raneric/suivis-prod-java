package com.sgg.suivisprod.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgg.suivisprod.repository.TaskRepository;

@Service
public class PaginationService {

	public static final int ROW_LIMIT        = 8;
	public static final int PAGINATION_LIMIT = 5;

	@Autowired
	private TaskRepository taskRepository;

	/**
	 * Build 1-based pagination list for history view pagination
	 * 
	 * @param currentPage
	 * @param totalPageNumber
	 * @return list of Integer
	 */
	public List<Integer> buildPaginationList(String username, int currentPage) {

		int totalTaskCount = taskRepository.countAllTaskByUserName(username);

		int           pageRangeStartIndex = getPageRangeStartIndex(currentPage);
		int           totalPageNumber     = getTotalPageNumber(username);
		List<Integer> pagination          = new ArrayList<>();
		if (haveMoreTahnOnePage(totalPageNumber) && isTheLastXPage(pageRangeStartIndex, totalPageNumber)) {
			pageRangeStartIndex = totalPageNumber - PAGINATION_LIMIT + 1;
		}

		for (int i = 1; i <= PAGINATION_LIMIT; i++) {
			pagination.add(pageRangeStartIndex);
			if (pageRangeStartIndex == totalPageNumber) {
				break;
			}
			pageRangeStartIndex++;
		}
		return pagination;
	}

	public int getTotalPageNumber(String username) {
		int totalTaskCount = taskRepository.countAllTaskByUserName(username);
		int pageNumber = (totalTaskCount / ROW_LIMIT) + 1;
		return pageNumber;
	}

	private boolean haveMoreTahnOnePage(int totalPageNumber) {
		return totalPageNumber > PAGINATION_LIMIT;
	}

	private boolean isTheLastXPage(int pageRangeStartIndex, int totalPageNumber) {
		return pageRangeStartIndex > totalPageNumber - PAGINATION_LIMIT;
	}

	/**
	 * 
	 * @param currentPage
	 * @return
	 */
	private int getPageRangeStartIndex(int currentPage) {
		String stringTotalPageNumber = Integer.toString(currentPage);
		int    value                 = Integer
				.parseInt(stringTotalPageNumber.substring(stringTotalPageNumber.length() - 1));
		int    startRangeIndex       = 0;

		if (value <= 5) {
			startRangeIndex = (stringTotalPageNumber.length() < 2) ? 1 : currentPage - value + 1;
		}
		else if (value > 5) {
			startRangeIndex = currentPage - value + PAGINATION_LIMIT + 1;
		}

		return startRangeIndex;
	}
}
