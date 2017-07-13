package sesoc.global.test.util;

public class PageNavigator {

	private int countPerPage = 10;	//한 페이지당 글 개수
	private int pagePerGroup = 5;	//페이지 그룹
	private int currentPage;				//사용자가 조회하고자 하는 페이지 번호. 외부에서 전달
	private int totalRecordCount;			//전체 글 개수. db에서 계산해서 가져오는 값.
	private int totalPageCount;				//총 페이지 수. 전체 글 개수와 페이지당 글 개수를 통해 계산할 수 있다.
	private int currentGroup;				//현재 조회 중인 페이지에 해당하는 그룹
	private int startPageGroup;				//현재 그룹의 첫 페이지
	private int endPageGroup;				//현재 그룹의 마지막 페이지
	private int startRecord;				//전체 레코드 중 현재 페이지의 첫 글의 위치
	private String searchtype;
	private String searchword;
	
	//srow, erow 계산하지 않고, mybatis의 기능 사용할 예정
	
	public PageNavigator(int currentPage, int totalRecordCount){
		this.totalRecordCount = totalRecordCount;
		
		this.totalPageCount = (totalRecordCount + countPerPage - 1) / countPerPage;
		//전체 글 개수가 152개라면 몇 페이지?
		
		//현재 페이지 요청시 계산
		if(currentPage < 1) currentPage = 1;
		if(currentPage > totalPageCount) currentPage = totalPageCount;

		this.currentPage = currentPage;
		
		//현재 그룹. 1~5페이지 : 0그룹, 6~10페이지 : 1그룹 ...
		currentGroup = (currentPage-1) / pagePerGroup;
		
		//현재 그룹의 첫 페이지 계산
		startPageGroup = currentGroup * pagePerGroup + 1;
		startPageGroup = startPageGroup < 1 ? 1 : startPageGroup;
		
		//현재 그룹의 마지막 페이지 계산
		endPageGroup = startPageGroup + pagePerGroup - 1;
		endPageGroup = endPageGroup < totalPageCount ? endPageGroup : totalPageCount;
		
		
		//전체 레코드 중에서 현재 페이지의 첫 글의 위치
		startRecord = (currentPage - 1) * countPerPage;
	}




	public String getSearchtype() {
		return searchtype;
	}




	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}




	public String getSearchword() {
		return searchword;
	}




	public void setSearchword(String searchword) {
		this.searchword = searchword;
	}




	public int getCountPerPage() {
		return countPerPage;
	}



	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}



	public int getPagePerGroup() {
		return pagePerGroup;
	}




	public void setPagePerGroup(int pagePerGroup) {
		this.pagePerGroup = pagePerGroup;
	}




	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getCurrentGroup() {
		return currentGroup;
	}

	public void setCurrentGroup(int currentGroup) {
		this.currentGroup = currentGroup;
	}

	public int getStartPageGroup() {
		return startPageGroup;
	}

	public void setStartPageGroup(int startPageGroup) {
		this.startPageGroup = startPageGroup;
	}

	public int getEndPageGroup() {
		return endPageGroup;
	}

	public void setEndPageGroup(int endPageGroup) {
		this.endPageGroup = endPageGroup;
	}

	public int getStartRecord() {
		return startRecord;
	}

	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}

	@Override
	public String toString() {
		return "PageNavigator [COUNT_PER_PAGE=" + countPerPage + ", PAGE_PER_GROUP=" + pagePerGroup
				+ ", currentPage=" + currentPage + ", totalRecordCount=" + totalRecordCount + ", totalPageCount="
				+ totalPageCount + ", currentGroup=" + currentGroup + ", startPageGroup=" + startPageGroup
				+ ", endPageGroup=" + endPageGroup + ", startRecord=" + startRecord + "]";
	}
	
	
}
