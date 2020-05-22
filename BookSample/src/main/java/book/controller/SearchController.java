package book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import book.model.Root;

@Controller
public class SearchController {

	// 국중open api주소
	String url = "https://www.nl.go.kr/NL/search/openApi/search.do?";
	// 인증키
	String key = "key=140af29a41cf7f8643b8a0675525268d1aa8e0cef32ffc324903755c30e63f26";
	// 상세검색 boolean
	String detailSearch = "detailSearch=true";
	// 검색키워드 and조건
	String and = "and1=and";
	// 상세검색조건1 제목
	String searchTargetTitle = "f1=title";
	// 상세조건검색2 출판사
	String searchTargetPublisher = "f2=publisher";
	// 상세조건검색2 입력값 - 민음사고정
	String publisherKwd = "v2=민음사";
	// 온.오프라인자료 구분
	String systemType = "systemType=오프라인자료";
	// 자료형태 구분
	String category = "category=도서";
	// 쪽당출력건수 -default : 10
	String pageSize = "pageSize=10";
	// 쪽
	String pageNum = "pageNum=1";

	String searchUrl = url + key + "&" + detailSearch + "&" + and + searchTargetPublisher + "&" + publisherKwd + "&"
			+ systemType + "&" + category + "&" + pageSize + "&" + pageNum;

	RestTemplate resTemplate;

	@Autowired
	public void setResTemplate(RestTemplate resTemplate) {
		this.resTemplate = resTemplate;
	}

	/*
	 * @RequestMapping(value = "/searchBook.do", method = RequestMethod.POST,
	 * produces = "text/plain;charset=UTF-8")
	 * 
	 * @ResponseBody public String searchBook(String keyword) {
	 * System.out.println(keyword); Root root = resTemplate.getForObject(searchUrl +
	 * "&" + searchTargetTitle + "&" + keyword, Root.class); Gson json = new Gson();
	 * return json.toJson(root); }
	 */
	
	

}
