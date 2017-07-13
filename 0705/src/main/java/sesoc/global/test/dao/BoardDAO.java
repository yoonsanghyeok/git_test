package sesoc.global.test.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import sesoc.global.test.vo.Board;

public interface BoardDAO {

	public List<Board> selectAll(Map<String, String> search, RowBounds rb);
	public Board selectOne(int boardnum);
	public int insert(Board board);
	public int update(Board newBoard);
	public int delete(int boardnum);
	public int getBoardCount(Map<String, String> search);
	public int increaseHits(int boardnum);
	public int deleteFile(int boardnum);
}
