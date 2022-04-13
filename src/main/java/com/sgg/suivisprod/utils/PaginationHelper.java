package com.sgg.suivisprod.utils;

import java.util.ArrayList;
import java.util.List;

public class PaginationHelper {

	public static final int ROW_LIMIT        = 8;
	public static final int PAGINATION_LIMIT = 5;

	private List<Integer> pagination;
	private int           totalPageNumber;
	private int           currentPage;
	private int           totalTaskCount;

	/**
	 * @return the totalTaskCount
	 */
	public int getTotalTaskCount() {
		return totalTaskCount;
	}

	/**
	 * @param totalTaskCount the totalTaskCount to set
	 */
	public void setTotalTaskCount(int totalTaskCount) {
		this.totalTaskCount = totalTaskCount;
	}

	/**
	 * @return the totalPageNumber
	 */
	public int getTotalPageNumber() {
		return totalPageNumber;
	}

	/**
	 * @return the pagination
	 */
	public List<Integer> getPagination() {
		return pagination;
	}

	public PaginationHelper(int totalTaskCount, int currentPage) {
		this.totalTaskCount = totalTaskCount;
		this.currentPage = currentPage;
		this.pagination = new ArrayList<>();
	}

	public List<Integer> build() {
		calculatePageNumber();
		buildPaginationList();
		return this.pagination;
	}

	private void calculatePageNumber() {
		int pageNumber = this.totalTaskCount / ROW_LIMIT;
		this.totalPageNumber = (pageNumber) == 0 ? 1 : pageNumber;
	}

	/**
	 * Build 1-based pagination list for history view pagination
	 * 
	 * @param currentPage
	 * @param totalPageNumber
	 * @return list of Integer
	 */
	private void buildPaginationList() {
		int pageRangeStartIndex = getPageRangeStartIndex();

		if (haveMoreTahnOnePage() && isTheLastXPage(pageRangeStartIndex)) {
			pageRangeStartIndex = totalPageNumber - PAGINATION_LIMIT + 1;
		}

		for (int i = 1; i <= PAGINATION_LIMIT; i++) {
			this.pagination.add(pageRangeStartIndex);
			if (pageRangeStartIndex == totalPageNumber) {
				break;
			}
			pageRangeStartIndex++;
		}
	}

	private boolean haveMoreTahnOnePage() {
		return totalPageNumber > PAGINATION_LIMIT;
	}

	private boolean isTheLastXPage(int pageRangeStartIndex) {
		return pageRangeStartIndex > totalPageNumber - PAGINATION_LIMIT;
	}

	/**
	 * 
	 * @param currentPage
	 * @return
	 */
	private int getPageRangeStartIndex() {
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
