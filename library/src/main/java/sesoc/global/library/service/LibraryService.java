package sesoc.global.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sesoc.global.library.dao.LibraryRepository;

@Service
public class LibraryService {

	@Autowired
	LibraryRepository repo;
	
	
}
