package sesoc.global.test.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sesoc.global.test.dao.NoticeRepository;
import sesoc.global.test.vo.Notice;

@Service
public class NoticeService{

	@Autowired
	NoticeRepository repo;

	public int getNoticeCount(Map<String, String> search) {
		return repo.getNoticeCount(search);
	}

	public List<Notice> selectAll(Map<String, String> search, int startRecord, int countPerPage) {
		RowBounds rb = new RowBounds(startRecord, countPerPage);
		return repo.selectAll(search, rb);
	}

	public void insertNotice(Notice notice) {
		repo.insertNotice(notice);
	}

	public Notice selectOne(int noticenum) {
		return repo.selectOne(noticenum);
	}

	public void increaseHits(int noticenum) {
		repo.increaseHits(noticenum);
	}

	public void updateNotice(Notice notice) {
		repo.updateNotice(notice);
	}

	public void deleteNotice(int noticenum) {
		repo.deleteNotice(noticenum);
	}
	
}
