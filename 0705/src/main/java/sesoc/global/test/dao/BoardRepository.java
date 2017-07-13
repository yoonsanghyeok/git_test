package sesoc.global.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sesoc.global.test.util.PageNavigator;
import sesoc.global.test.vo.Board;

@Repository
public class BoardRepository {
	@Autowired
	SqlSession sqlSession;
	
	public List<Board> findAll(String searchtype, String searchword, int startRecord, int countPerPage){
		List<Board> boardList;
		RowBounds rb = new RowBounds(startRecord, countPerPage);

		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		Map<String, String> search = new HashMap<>();
		search.put("searchtype", searchtype);
		search.put("searchword", searchword);
		
		boardList = dao.selectAll(search, rb);		//rb에 값을 세팅해서 매개변수로 넘기기만 하면 MyBatis가 알아서 내부적으로 이를 이용해서 사용한다.
		System.out.println("결과물 : " + boardList);
		return boardList;
	}

	public Board selectOne(int boardnum) {
		return sqlSession.getMapper(BoardDAO.class).selectOne(boardnum);
	}
	
	public int insertBoard(Board board){
		return sqlSession.getMapper(BoardDAO.class).insert(board);
	}
	
	public int updateBoard(Board newBoard){
		return sqlSession.getMapper(BoardDAO.class).update(newBoard);
	}

	public int increaseHits(int boardnum) {
		return sqlSession.getMapper(BoardDAO.class).increaseHits(boardnum);
	}

	public int deleteBoard(int boardnum) {
		return sqlSession.getMapper(BoardDAO.class).delete(boardnum);
	}
	
	public int getBoardCount(String searchtype, String searchword){
		Map<String, String> search = new HashMap<>();
		search.put("searchtype", searchtype);
		search.put("searchword", searchword);
		return sqlSession.getMapper(BoardDAO.class).getBoardCount(search);
	}
	
	public int deleteFile(int boardnum){
		return sqlSession.getMapper(BoardDAO.class).deleteFile(boardnum);
	}
}
