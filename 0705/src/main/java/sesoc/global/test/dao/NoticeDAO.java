package sesoc.global.test.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import sesoc.global.test.vo.Board;
import sesoc.global.test.vo.Notice;

public interface NoticeDAO {
	public List<Notice> selectAll(Map<String, String> search, RowBounds rb);
	public Notice selectOne(int noticenum);
	public int insert(Notice notice);
	public int update(Notice newNotice);
	public int delete(int noticenum);
	public int getNoticeCount(Map<String, String> search);
	public int increaseHits(int noticenum);
	public int deleteFile(int noticenum);
}
