package com.team404.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team404.command.FreeBoardVO;
import com.team404.freeboard.service.FreeBoardService;
import com.team404.util.Criteria;
import com.team404.util.PageVO;

@Controller
@RequestMapping("/freeBoard")
public class BoardController {
	
	// 화면처리 -> 테이블생성 -> 등록처리 -> 
	
	@Autowired
	@Qualifier("freeBoardService")
	private FreeBoardService freeBoardService;
	
	//게시판 등록 이동
	@RequestMapping("/freeRegist")
	public void freeRegist() {}
	
	//게시판 목록 이동	
	@RequestMapping("/freeList")
	public String freeList(Model model, Criteria cri) {
		//기본
		//ArrayList<FreeBoardVO> list = freeBoardService.getList();
		//model.addAttribute("boardList", list);
		
		//페이징
		ArrayList<FreeBoardVO> list = freeBoardService.getList(cri);
		int total = freeBoardService.getTotal();
		PageVO pageVO = new PageVO(cri, total); //화면에 그려질 페이지네이션 처리
		System.out.println(list.toString());
		model.addAttribute("boardList", list);
		model.addAttribute("pageVO", pageVO);
		
		
		return "freeBoard/freeList";
	}
	
//	//게시판 수정 이동
//	@RequestMapping("/freeModify")
//	public String freeModify(@RequestParam("bno") int bno,
//			Model model) {
//		
//		FreeBoardVO vo = freeBoardService.getContent(bno);
//		model.addAttribute("boardVO", vo);
//		
//		return "redirect:/freeBoard/freeModify";
//	}	
//	
//	//게시판 상세보기 이동
//	@RequestMapping("/freeModify")
//	public String freeDetail(@RequestParam("bno") int bno,
//										Model model) {
//		
//		FreeBoardVO vo = freeBoardService.getContent(bno);
//		
//		model.addAttribute("boardVO", vo);
//		
//		return "freeBoard/freeDetail";
//	}
	
	//변경화면 상세화며을 하나로 묶어서 처리가 가능.
	//게시판 상세보기, 수정 (void로 선언하면 그화면 그대로 return)
	@RequestMapping({"/freeModify", "/freeDetail"})
	public void getContent(@RequestParam("bno") int bno,
										Model model) {
		FreeBoardVO vo = freeBoardService.getContent(bno);
		model.addAttribute("boardVO", vo);
	}
	
	
	
	//등록처리
	@RequestMapping(value = "/registForm", method = RequestMethod.POST)
	public String registForm(FreeBoardVO vo, RedirectAttributes RA) {
		
		freeBoardService.regist(vo);
		//등록 성공 여부(msg)  - 일회성 사용 가능
		RA.addFlashAttribute("msg", "정상 등록 처리 되었습니다.");

		return "redirect:/freeBoard/freeList"; //등록 후에 리스트화면으로 리다이렉트 
	}
	
	
	//수정처리
	@RequestMapping(value = "/freeUpdate", method = RequestMethod.POST)
	public String freeUpdate(FreeBoardVO vo, RedirectAttributes RA) {
		
		int result = freeBoardService.update(vo);
		
		if(result == 1 ) {
			RA.addFlashAttribute("msg", "게시글이 정상수정되었습니다.");
		}else {
			RA.addFlashAttribute("msg", "게시글 수정에 실패했습니다");
		}
		
		//다시 상세화면으로 리다이렉트 
		return "redirect:/freeBoard/freeList";
	}
	
	@RequestMapping(value = "/freeDelete", method = RequestMethod.POST )
	public String freeDelete(FreeBoardVO vo, RedirectAttributes RA) {
		System.out.println("접속");
		
		int result = freeBoardService.delete(vo);
		
		if(result == 1 ) {
			RA.addFlashAttribute("msg", "게시글 삭제가 정상처리되었습니다.");
		}else {
			RA.addFlashAttribute("msg", "게시글 삭제가 실패했습니다");
		}
		
		//다시 상세화면으로 리다이렉트 
		return "redirect:/freeBoard/freeList";
	}

	
	
	
	

}
