package org.zerock;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.FreeBoard;
import org.zerock.domain.FreeBoardReply;
import org.zerock.persistence.FreeBoardReplyRepository;
import org.zerock.persistence.FreeBoardRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@Log
@SpringBootTest
@Commit
public class FreeBoardTests {

	@Autowired
	FreeBoardRepository boardRepo;

	@Autowired
	FreeBoardReplyRepository replyRepo;

	@Test
	public void insertDummyTest() {

		for (int i = 10; i < 100; i++) {

			FreeBoard board = new FreeBoard();
			board.setTitle("제목..." + i);
			board.setContent("내용...." + i);
			board.setWriter("user" + i);

			boardRepo.save(board);
		}
	}

	@Test
	public void testInsertReply() {

		FreeBoardReply reply = new FreeBoardReply();

		Long bno = 1L;

		FreeBoard board = new FreeBoard();
		board.setBno(bno);

		reply.setReply("RE......................");
		reply.setReplyer("Replyer....");
		reply.setBoard(board);

		replyRepo.save(reply);
	}

	@Test
	public void testRead() {

		boardRepo.findById(1L).ifPresent(board -> {

			System.out.println(board);

			List<FreeBoardReply> replies = board.getReplies();

			System.out.println(replies);
		});

	}
	
	@Transactional
	@Test
	public void testPaging() {
		
		Pageable page = PageRequest.of(0, 20, Sort.Direction.DESC, "bno");
		
		List<FreeBoard> result = boardRepo.findByBnoGreaterThan(0L, page);
		
		System.out.println(result);
		
	}
	
	@Test
	public void testList3(){
		
		Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		
		boardRepo.getPage(page).forEach(arr -> 
		  log.info(Arrays.toString(arr)));
	}
}
