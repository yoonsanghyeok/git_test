package sesoc.global.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sesoc.global.test.vo.Notice;

@Repository
public class NoticeRepository {

	@Autowired
	SqlSession sqlSession;
	
	public List<Notice> selectAll(Map<String, String> search, RowBounds rb){
		return sqlSession.getMapper(NoticeDAO.class).selectAll(search, rb);
	}

	public int getNoticeCount(Map<String, String> search) {
		return sqlSession.getMapper(NoticeDAO.class).getNoticeCount(search);
	}

	public int insertNotice(Notice notice) {
		return sqlSession.getMapper(NoticeDAO.class).insert(notice);
	}

	public Notice selectOne(int noticenum) {
		return sqlSession.getMapper(NoticeDAO.class).selectOne(noticenum);
	}

	public int increaseHits(int noticenum) {
		return sqlSession.getMapper(NoticeDAO.class).increaseHits(noticenum);
	}

	public void updateNotice(Notice notice) {
		sqlSession.getMapper(NoticeDAO.class).update(notice);
	}

	public void deleteNotice(int noticenum) {
		sqlSession.getMapper(NoticeDAO.class).delete(noticenum);
	}
}
