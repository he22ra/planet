package com.blog.main.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.main.common.dto.MessageDto;
import com.blog.main.common.dto.SearchDto;
import com.blog.main.common.paging.PagingResponse;
import com.blog.main.ott.OttRequest;
import com.blog.main.ott.OttResponse;
import com.blog.main.service.OttService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor	// 클래스 내에 final로 선언된 모든 멤버에 대한 생성자를 만들어주는 역할
public class OttController {

	private final OttService ottService;
	
	// 게시글 리스트 페이지
		@GetMapping("/ott/list.do")	//ModelAttribute : 파라미터로 수집한 객체를 자동으로 뷰(HTML)에 전달.
		public String openOttList(@ModelAttribute("params") final SearchDto params, Model model) {
	        PagingResponse<OttResponse> response = ottService.findAllOtt(params);
	        model.addAttribute("response", response);
	        return "ott/list";
	    }
		
		// 게시글 작성 페이지
		@GetMapping("/ott/write.do")
		public String openOttWrite(@RequestParam(value = "id", required = false) final Long id, Model model) {
			// 글 수정시
			if (id != null) {
				OttResponse ott = ottService.findOttById(id);
				model.addAttribute("ott", ott);
			}
			return "ott/write";
		}
		
		// 신규 게시글 생성
	    @PostMapping("/ott/save.do")
	    public String saveOttPost(final OttRequest params, Model model) {
	        ottService.saveOtt(params);
	        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/ott/list.do", RequestMethod.GET, null);
	        return showMessageAndRedirect(message, model);
	    }
	    
	    // 기존 게시글 수정
	    @PostMapping("/ott/update.do")
	    public String updateOttPost(final OttRequest params, Model model) {
	        ottService.updateOtt(params);
	        MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/ott/list.do", RequestMethod.GET, null);
	        return showMessageAndRedirect(message, model);
	    }
	    
	    // 게시글 상세 페이지
	    @GetMapping("/ott/view.do")
	    public String openPostView(@RequestParam final Long id, Model model) {
	        OttResponse ott = ottService.findOttById(id);
	        model.addAttribute("ott", ott);
	        return "ott/view";
	    }
	    
	    // 게시글 삭제
	    @PostMapping("/ott/delete.do")
	    public String deleteOttPost(@RequestParam final Long id, final SearchDto queryParams, Model model) {
	        ottService.deleteOtt(id);
	        MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/ott/list.do", RequestMethod.GET, queryParamsToMap(queryParams));
	        return showMessageAndRedirect(message, model);
	    }
	    //쿼리 스트링 파라미터를 Map에 담아 리턴
	    private Map<String, Object> queryParamsToMap(SearchDto queryParams) {
	    	Map<String, Object> data = new HashMap<>();
	    	data.put("page", queryParams.getPage());
	    	data.put("recordSize", queryParams.getRecordSize());
	    	data.put("pageSize", queryParams.getPageSize());
	    	data.put("keyword", queryParams.getKeyword());
	    	data.put("searchType", queryParams.getSearchType());
			return data;
		}

		// Alert 메세지 처리 후 페이지 리다이렉트
		private String showMessageAndRedirect(final MessageDto params, Model model) {
	    	model.addAttribute("params", params);
	    	return "common/messageRedirect";
	    }
}
