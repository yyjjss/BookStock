<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Regular Tables - Tables are the backbone of almost all web applications.</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
    <meta name="description" content="Wide selection of modal dialogs styles and animations available.">
    <meta name="msapplication-tap-highlight" content="no">

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
<style type="text/css">
.modal_box {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1050; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }
</style>
</head>
<body>
   <div class="app-main__outer">
                    <div class="app-main__inner">
                        <div class="app-page-title">
                            <div class="page-title-wrapper">
                                <div class="page-title-heading">
                                   <!--  <div class="page-title-icon">
                                        <i class="pe-7s-drawer icon-gradient bg-happy-itmeo">
                                        </i>
                                    </div> -->
                                    <div>제품조회
                                    </div>
                                </div>
                                <!-- <div class="page-title-actions">
                                    <button type="button" data-toggle="tooltip" title="Example Tooltip" data-placement="bottom" class="btn-shadow mr-3 btn btn-dark">
                                        <i class="fa fa-star"></i>
                                    </button>
                                </div>     --></div>
                        </div>            
                        
                        <div class="row">
							<!-- Table영역 --> 
                            <div class="col-lg-6">
                                <div class="main-card mb-3 card" style = "width : 1150px;">
                                    <div class="card-body" style = "width : 100%"><!-- <h5 class="card-title"> -->
                                     <input id = "searchKeyword" class = "mb-2 form-control-sm form-control" style = "width : 500px; display : inline;">
                                     <a href = "javascript:searchTitle();"><img alt="search" src="${pageContext.request.contextPath}/assets/images/avatars/search.png" style="width: 28px; margin-left: 10px; height : 25px; padding-bottom:5px;"></a>
                                    
                                    <button type="button" class="btn mr-2 mb-2 btn-primary" style = "float : right" onclick = "openModal();">등록</button>
<%--                                     <button class="mb-2 mr-2 btn btn-primary" style = "float:right" onclick = "location.href='${pageContext.request.contextPath}/addGoods.do'">제품등록
                                        </button> --%>
                                        <div class="scroll-area-sm" style = "height : 800px; width : 100%">
                                        <table class="mb-0 table table-hover" id = "GoodsList">
                                            <thead>
                                            <tr>
                                                <th class='fixedHeader'>서명</th>
                                                <th class='fixedHeader'>저자</th>
                                                <th class='fixedHeader'>ISBN</th>
                                                <th class='fixedHeader'>카테고리</th>
                                                <th class='fixedHeader'>발행일</th>
                                                <th class='fixedHeader'></th>
                                            </tr>
                                            </thead>
                                            <tbody class="scrollbar-container ps--active-y">
                                            <c:if test="${empty books}">
                                            	<tr>
                                                <th scope="row"></th>
                                                <td colspan="5">등록된 제품이 없습니다.</td>
                                            </tr>
                                            </c:if>
                                            <c:if test="${!empty books}">
                                            <c:forEach var = "book"  items = "${books}">
	                                            <tr>
	                                                <th scope="row">${book.title}</th>
	                                                <td>${book.author}</td>
	                                                <td>${book.isbn}</td>
	                                                <td>${book.category}</td>
	                                                <td>${book.publish_date}</td>
	                                                <td><button class="mb-2 mr-2 btn btn-primary" onclick = "deleteBook('${book.isbn}');">삭제</button></td>
	                                            </tr>
                                            </c:forEach>
                                            </c:if>

                                            </tbody>
                                        </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Table영역 끝 -->
                            </div>
                        </div>
                    </div>
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/scripts/main.js"></script>

	</body>
	<script type="text/javascript">
	
	function closeX() {
		var modal = document.getElementById('myModal');	
		modal.style.display = "none";
		window.location.reload();
	}

	function openModal(){
		var modal = document.getElementById('myModal');	
		modal.style.display = "block";
	}

		function search(){
			var keyword = document.getElementById("kwd").value;
			//alert(keyword);
			$.ajax({
				type : "get",
				url : "https://www.nl.go.kr/NL/search/openApi/search.do?key=140af29a41cf7f8643b8a0675525268d1aa8e0cef32ffc324903755c30e63f26&detailSearch=true&and1=and&f2=publisher&v2=%EB%AF%BC%EC%9D%8C%EC%82%AC&systemType=%EC%98%A4%ED%94%84%EB%9D%BC%EC%9D%B8%EC%9E%90%EB%A3%8C&category=%EB%8F%84%EC%84%9C&pageSize=10&pageNum=1&f1=title&apiType=json&v1="+keyword
/* 				data : "keyword=", */
				/* dataType : "json" */
			}).done(function(args){
				console.log(args);
				var msg = "<thead>";
				msg += "<tr>";
				msg += "<th>서명</th><th>저자</th><th>ISBN</th><th>카테고리</th><th>발행일</th><th></th>";
				msg += "</tr></thead><tbody>"
				if(args.total == 0){
					msg += "<tbody><tr><th scope='row'></th><td colspan='5'>검색결과가 없습니다.</td></tr></tbody>"
				}else{
					for(var i = 0; i <= args.result.length - 1; i++){
						//alert("원본 : " + args.result[i].titleInfo);
						var title = args.result[i].titleInfo.replace(/(<([^>]+)>)/ig,"");
						//alert("1차 : " + title);
						title = title.replace(/(<([^>]+)>)/ig,"");
						//alert("2차 : " + title);
						var author = args.result[i].authorInfo.replace(/(<([^>]+)>)/ig,"");
						msg += "<tr><th scope='row'>"+args.result[i].titleInfo+"</th>";
						msg += "<td>"+args.result[i].authorInfo+"</td>";
						msg += "<td>"+args.result[i].isbn+"</td>";
						msg += "<td>"+args.result[i].kdcName1s+"</td>";
						msg += "<td>"+args.result[i].pubYearInfo+"</td>";
						msg += "<td><button class='mb-2 mr-2 btn btn-primary' onclick = \"selectBook('"+author+"','"+args.result[i].isbn+"','"+args.result[i].kdcCode1s+"','"+title+"','"+args.result[i].pubYearInfo+"');\">선택</button></td>";
						msg += "</tr>";
					}
				}
				msg += "</tbody>";
				$("#searchResult").html(msg);
				
			}).fail(function(e){
				alert(e.responseText);
			})	
		}

		function selectBook(authorInfo, isbn, kdcCode, titleInfo, publishYearInfo){
			var title = titleInfo.replace(/(<([^>]+)>)/ig,"");
			var bookData = {
				isbn : isbn,
				title : title,
				author : authorInfo,
				publisher : "민음사",
				category_id : kdcCode,
				publish_date : publishYearInfo
			}
			$.ajax({
				type : "post",
				url : "insertBook.do",
 				data : bookData,
				dataType : "json"
			}).done(function(args){
				if(args == -1){
					alert("이미 등록된 제품입니다.");
				}else if(args == 0){
					alert("제품등록에 실패했습니다.");
				}else if(args == 1){
					alert("제품을 등록했습니다.");
				}
				
			}).fail(function(e){
				alert(e.responseText);
			})	
		}


		function searchTitle(){
			var keyword = document.getElementById("searchKeyword").value;
			$.ajax({
				type : "post",
				url : "searchBook.do",
 				data : "title="+keyword,
				dataType : "json"
			}).done(function(args){
				console.log(args);
				var msg = "<thead>";
				msg += "<tr>";
				msg += "<th>서명</th><th>저자</th><th>ISBN</th><th>카테고리</th><th>발행일</th><th></th>";
				msg += "</tr></thead><tbody>"
				if(args.length == 0){
					msg += "<tbody><tr><th scope='row'></th><td colspan='5'>검색결과가 없습니다.</td></tr></tbody>"
				}else{
					for(var i = 0; i <= args.length - 1; i++){
						msg += "<tr><th scope='row'>"+args[i].title+"</th>";
						msg += "<td>"+args[i].author+"</td>";
						msg += "<td>"+args[i].isbn+"</td>";
						msg += "<td>"+args[i].category+"</td>";
						msg += "<td>"+args[i].publish_date+"</td>";
						msg += "<td><button class='mb-2 mr-2 btn btn-primary' onclick = \"deleteBook('"+args[i].isbn+"'');\">삭제</button></td>";
						msg += "</tr>";
					}
				}
				$("#GoodsList").html(msg);
				
			}).fail(function(e){
				alert(e.responseText);
			})
			
		}

		function deleteBook(isbn){
			$.ajax({
				type : "post",
				url : "deleteBook.do",
 				data : "isbn="+isbn,
				dataType : "json"
			}).done(function(args){
				console.log(args);
				if(args > 0){
					alert("재품을 삭제하였습니다.");
					window.location.reload();
				}else{
					alert("재고가 존재하는 제품입니다. 재고를 모두 소진한 후에 삭제해주세요.");
				}
				
			}).fail(function(e){
				alert(e.responseText);
			})
		}

		
	</script>
</html>
<div id="myModal" class="modal_box">
<!-- 	<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
 -->    <div class="modal-dialog modal-lg" style = "margin-top : 80px">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">제품등록</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick = "closeX();">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
				                        <div class="row">
							<!-- Table영역 --> 
                            <div class="col-lg-6">
                                <div class="main-card mb-3 card" style = "width : 770px">
                                    <div class="card-body">
                                    <input id = "kwd" class = "mb-2 form-control-sm form-control" style = "width : 500px; display : inline;">
                                     <a href = "javascript:search();"><img alt="search" src="${pageContext.request.contextPath}/assets/images/avatars/search.png" style="width: 28px; margin-left: 10px; height : 25px; padding-bottom:5px;"></a>
                                       
                                        <table class="mb-0 table table-hover" id = "searchResult">
                                            <thead>
                                            <tr>
                                                <th>서명</th>
                                                <th>저자</th>
                                                <th>ISBN</th>
                                                <th>카테고리</th>
                                                <th>발행일</th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <!-- Table영역 끝 -->
                            </div>

            </div>
		<div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick = "closeX();">Close</button>
               <!--  <button type="button" class="btn btn-primary">Save changes</button> -->
            </div>
        </div>
    </div>
</div>

<!-- Large modal -->


