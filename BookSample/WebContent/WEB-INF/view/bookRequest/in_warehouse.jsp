<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true" %>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
 	<meta charset="utf-8">
    <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> -->
    <title>입고</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
    <meta name="msapplication-tap-highlight" content="no">
   
<link href="${pageContext.request.contextPath}/assets/css/main.css" rel="stylesheet">
<%-- <link rel="stylesheet" href="<c:url value="/assets/css/main.css"/>"> --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style type="text/css">
.fixedHeader {
	position: sticky;
	top: 0;
	background-color: #ffffff;
}

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

<script type="text/javascript">


function closeX() {
	var modal = document.getElementById('myModal');	
	modal.style.display = "none";
}

/* function memberBtn(){
	var modal = document.getElementById('myModal');	
	modal.style.display = "block";
} */

$(document).on('click', '#memberBtn', function(){
	var modal = document.getElementById('myModal');	
	modal.style.display = "block";
}); 


function inWarehouseSearch(){
	//alert("ok");
	var title = $("#bookSearch").val();
	//var title = document.getElementById('bookSearch');	
	//alert(title);
	$.ajax({
   		type:"post"
   		,url:"in_warehouse_search.do?title="+title 
   		,dataType:"json"})
   		.done(function(args){
   			var msg = "";
			if(args.length != 0){
				msg +="<div class='scroll-area-sm' style='height: 300px;'>"
				msg += "<table class='mb-3 table table-hover' id='example-table-2'>";
				msg += "<thead>";
				msg += "<tr>";
				msg += "<th class='fixedHeader'>ISBN</th>";
				msg += "<th class='fixedHeader'>제품명</th>";
				msg += "<th class='fixedHeader'>저자/역자</th>";
				msg += "<th class='fixedHeader'>출판일</th>";
				msg += "<th class='fixedHeader'>입고일</th>";
				msg += "<th class='fixedHeader'>수량</th>";
				msg += "<th class='fixedHeader'>창고</th>";
				msg += "<th class='fixedHeader'>&nbsp;</th>";
				msg += "</tr>";
				msg += "</thead>";
				msg += "<tbody class='scrollbar-container ps--active-y'>";
	
				for(var index = 0; index <= args.length - 1; index++){
					
					msg += "<tr style='text-align: center;'>";
					msg += "<th scope='row' id='' name=''>"+args[index].isbn+"</th>";
					msg += "<td>"+args[index].title+"</td>";
					msg += "<td>"+args[index].author+"/"+args[index].publisher+"</td>";
					msg += "<td>"+args[index].publish_date+"년</td>";
					msg += "<td><input type='date' id='"+args[index].isbn+"_d' name='due_date' style='width: 125px;'></td>";
					msg += "<td><input type='text' id='"+args[index].isbn+"_a' name='scheduled_amount' style='width: 60px;'></td>";
					msg += "<td><select name='warehouse_name' id='"+args[index].isbn+"_w' style='width: 60px;'>";
					msg += "<c:forEach var='name' items='${warehouseName}'>";
					msg += "<option value='${name.warehouse_id}'>${name.warehouse_name}</option>";
					msg += "</c:forEach>";
					msg += "</select>";
					msg += "</td>";

					//msg += "<td><input type='text' id='warehouse_name' neme='warehouse_name' style='width: 60px;'></td>";
					//msg += "<td><input type='button' class='checkBtn' onclick='plusItems();' value='추가'/></td>";
					msg += "<td><input type='button' class='checkBtn' id='checkBtn' value='추가' onclick = \"selectInwarehouse('"+args[index].isbn+"');\" /></td>";
					msg += "</tr>";
				}
				msg += "</tbody>";
				msg += "</table>";
				msg += "</div>";
			}else{
				msg = "<div style='text-align: center;'>검색결과가 존재하지 않습니다.</div>";
			}
			//console.log(msg);
			$("#searchItems").html(msg);
   		})
   		.fail(function(e) {
   			alert("error");
   			alert(e.responseText);
   		});
}

function selectInwarehouse(isbn){

	var due_date = $("#"+isbn+"_d").val();
	var scheduled_amount = $("#"+isbn+"_a").val();
	var warehouse_id = $("#"+isbn+"_w option:selected").val();
	//url:"in_warehouse_insert.do?isbn="+isbn+"&due_date="+due_date+"&scheduled_amount="+scheduled_amount+"&warehouse_id="+warehouse_id 
   		
	var bookData = {
			isbn : isbn,
			due_date : due_date,
			scheduled_amount : scheduled_amount,
			warehouse_id : warehouse_id
		}
	
	$.ajax({
   		type:"post",
   		url:"in_warehouse_insert.do",
   		data: bookData,
   		dataType:"json"

   	   		}).done(function(args){
   	   	   		if(args.cnt > 0){
					alert("입고 신청내역이 추가되었습니다.");
				}else{
					alert("실패");
				}
   			}).fail(function(e) {
   				alert("error");
   				alert(e.responseText);
   			})
}


function inWarehouseDateSearch(){
	var date01 = $("#date01").val();
	var date02 = $("#date02").val();

	location.href = "in_warehouseDateSearch.do?date01="+date01+"&date02="+date02+"";
}

function deleteRequest(requestNumber, isbn){
	//alert(requestNumber);
	var date01 = $("#date01").val();
	var date02 = $("#date02").val();
	
	location.href = "in_warehouseDelectRequest.do?request_number="+requestNumber+"&date01="+date01+"&date02="+date02+"&isbn="+isbn;
}

/* function updateRequest(requestNumber, isbn){
	alert(requestNumber);
	var date01 = $("#date01").val();
	var date02 = $("#date02").val();
	
	location.href = "in_warehouseUpdateRequest.do?request_number="+requestNumber+"&date01="+date01+"&date02="+date02+"&isbn="+isbn;
} */

$(document).on('click', '#up', function(){
	//alert("go");
	
	var tdArr = new Array();	// 배열 선언
	var checkBtn = $(this);
	// checkBtn.parent() : checkBtn의 부모는 <td>이다.
	// checkBtn.parent().parent() : <td>의 부모이므로 <tr>이다.
	var tr = checkBtn.parent().parent();
	var td = tr.children();
	
	var request_number = td.eq(0).text();
	var isbn = td.eq(1).text();
	var due_date = td.eq(5).find('input[type="date"]').val();
	var scheduled_amount = td.eq(6).find('input[type="text"]').val();
	var warehouse_id = td.eq(7).find('option:selected').val();

	var date01 = $("#date01").val();
	var date02 = $("#date02").val();
	
	console.log("w_name : "+warehouse_id);
	console.log("date : "+due_date);
	console.log("amount : "+scheduled_amount);

	var bookData = {
			request_number : request_number,
			isbn : isbn,
			due_date : due_date,
			scheduled_amount : scheduled_amount,
			warehouse_id : warehouse_id
		}
	
	$.ajax({
   		type:"post",
   		url:"in_warehouse_update.do",
   		data: bookData,
   		dataType:"json"

   	   		}).done(function(args){
   	   	   		
   	   	   		if(args.cnt > 0){
					alert("입고 신청이 완료되었습니다.");
					location.href = "in_warehouseDateSearch.do?date01="+date01+"&date02="+date02+"";
					
				}else{
					alert("실패");
				}
   			}).fail(function(e) {
   				alert("error");
   				alert(e.responseText);
   			})
});

</script>
</head>
<body>

 <div class="app-main__outer">
                    <div class="app-main__inner">
                        <!-- <div> <span style="font-size: 30px;">입고신청</span></div> -->
                        <div class="main-card mb-3" style="text-align: center;">
                        	<input type="date" name="date01" id="date01" value="${date01}"
                            	style="line-height: 1.5; color: #495057; background-color: #fff; background-clip: padding-box; border: 1px solid #ced4da; border-radius: .25rem; height: calc(2.25rem + 2px); margin-top: 5px;">
                          	<span style="font-size: 30px;">&nbsp;~&nbsp;</span> 
                          	<input type="date" name="date02" id="date02" value="${date02}"
                            	style="line-height: 1.5; color: #495057; background-color: #fff; background-clip: padding-box; border: 1px solid #ced4da; border-radius: .25rem; height: calc(2.25rem + 2px); margin-top: 5px;">
                          	<a href="javascript:void(0);" onclick="inWarehouseDateSearch();">
                          	<img alt="search" src="${pageContext.request.contextPath}/assets/images/avatars/search.png" style="width: 30px; margin-top: 7px; margin-left: 10px;"></a>
                        	<span style="float: right; margin-top: 15px;">
                        	<!-- <button class="btn btn-primary" style="width: 100%" data-toggle="modal" data-target=".bd-example-modal-lg">입고신청</button>  --> 
                        	<!-- 
                        	<button class="btn btn-primary" style="width: 100%" id="memberBtn" onclick="memberBtn();">입고신청</button> 
                        	 -->
                        	 <c:if test="${user_type_id eq 1}"><button class="btn btn-primary" style="width: 100%" id="memberBtn">입고신청</button></c:if> 
                        	</span>
                        </div>
                        
                        
                        <div class="tab-content">
                            <div class="tab-pane tabs-animation fade show active" id="tab-content-0" role="tabpanel">
                                <div class="main-card mb-3 card">
                                    <div class="card-body"><h5 class="card-title">입고신청</h5>
                                    <div class="scroll-area-sm">
                                      
                                        <table class="mb-3 table table-hover">
                                            <thead>
                                            <tr>
                                                <th class='fixedHeader'>No.</th>
                                                <th class='fixedHeader'>ISBN</th>
                                                <th class='fixedHeader'>제품명</th>
                                                <th class='fixedHeader'>카테고리</th>
                                                <th class='fixedHeader'>저자/역자</th>
                                                <th class='fixedHeader'>입고일</th>
                                                <th class='fixedHeader'>수량</th>
                                                <th class='fixedHeader'>창고</th>
                                                <th class='fixedHeader'>처리상태</th>
                                                <th class='fixedHeader'></th>
                                            </tr>
                                            </thead>
                                            <tbody class="scrollbar-container ps--active-y">
                                            <c:forEach var="requestHistory" items="${requestHistory}">
                                            	<%-- <c:set var="num" value="0"/><th scope="row"><c:out value="${num}+1"/></th> --%> 
                                            	<tr>
                                            		<th>${requestHistory.request_number}</th>
                                            		<td style="width: 150px;">${requestHistory.isbn}</td>
                                            		<td style="width: 150px;">${requestHistory.title}</td>
                                            		<td>${requestHistory.category}</td>
                                            		<td style="width: 150px;">${requestHistory.author}/${requestHistory.publisher}</td>
                                            		<td><fmt:formatDate value="${requestHistory.due_date}" pattern="yyyy-MM-dd"/></td>
                                            		<td>${requestHistory.scheduled_amount}</td>
                                            		<td>${requestHistory.warehouse_name}</td>
                                            		<td>${requestHistory.status}</td>
                                            		<td><c:if test="${user_type_id eq 1}"><button id="del" onclick='deleteRequest("${requestHistory.request_number}","${requestHistory.isbn}")'>삭제</button></c:if></td>
                                            	</tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                           </div>
                                
                               <!--  <div class="main-card mb-3">
                                <span style="font-size: 30px;">입고확인</span>
                                </div>
                                	 -->
                                <div class="main-card mb-3 card">
                                    <div class="card-body"><h5 class="card-title">입고확인</h5>
                                    <div class="scroll-area-sm">
                                      
                                        <table class="mb-3 table table-hover">
                                            <thead>
                                            <tr>
                                                <th class='fixedHeader'>No.</th>
                                                <th class='fixedHeader'>ISBN</th>
                                                <th class='fixedHeader'>제품명</th>
                                                <th class='fixedHeader'>카테고리</th>
                                                <th class='fixedHeader'>저자/역자</th>
                                                <th class='fixedHeader'>입고일</th>
                                                <th class='fixedHeader'>수량</th>
                                                <th class='fixedHeader'>창고</th>
                                                <th class='fixedHeader'>처리상태</th>
                                                <th class='fixedHeader'></th>
                                            </tr>
                                            </thead>
                                            <tbody class="scrollbar-container ps--active-y">
                                            <c:forEach var="requestHistory" items="${requestHistory}">
                                            	<%-- <c:set var="num" value="0"/><th scope="row"><c:out value="${num}+1"/></th> --%> 
                                            	<tr>
                                            		<th>${requestHistory.request_number}</th>
                                            		<td style="width: 150px;">${requestHistory.isbn}</td>
                                            		<td style="width: 150px;">${requestHistory.title}</td>
                                            		<td>${requestHistory.category}</td>
                                            		<td style="width: 150px;">${requestHistory.author}/${requestHistory.publisher}</td>
                                            		<td>
                                            		<c:set var="now" value="<%= new java.util.Date() %>"/>
                                            		<c:choose>
                                            			<c:when test="${empty requestHistory.completion_date}">
                                            				<input type="date" max="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>" value="<fmt:formatDate value="${requestHistory.due_date}" pattern="yyyy-MM-dd"/>" style="width: 130px;">
                                            			</c:when>
                                            			
                                            			<c:when test="${!empty requestHistory.completion_date}">
                                            				<c:choose>
                                            					<c:when test="${requestHistory.status_id eq 2 || requestHistory.status_id eq 3}">
                                            						<input type="date" max="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>" value="<fmt:formatDate value="${requestHistory.completion_date}" pattern="yyyy-MM-dd"/>" <c:if test="${requestHistory.due_date ne requestHistory.completion_date}"> style="color:#FF0000; width: 130px;" </c:if> style="width: 130px;">
                                            					</c:when>
                                            					<c:when test="${requestHistory.status_id eq 1}">
                                            						<input type="date" max="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>" value="<fmt:formatDate value="${requestHistory.due_date}" pattern="yyyy-MM-dd"/>" style="width: 130px;">
                                            					</c:when>
                                            				</c:choose>
                                            			</c:when>
                                            		</c:choose>
                                            		</td>
                                            		
                                            		<td>
                                            		<c:choose>
                                            			<c:when test="${0 ne requestHistory.amount}">
                                            				<c:choose>
                                            					<c:when test="${requestHistory.status_id eq 2 || requestHistory.status_id eq 3}">
                                            						<input type="text" value="${requestHistory.amount}" <c:if test="${requestHistory.scheduled_amount ne requestHistory.amount}"> style="color:#FF0000; width: 40px;" </c:if> style="width: 40px;">
                                            					</c:when>
                                            			
                                            					<c:when test="${requestHistory.status_id eq 1}">
                                            						<input type="text" value="${requestHistory.scheduled_amount}" style="width: 40px;">
                                            					</c:when>
                                            				</c:choose>
                                            			</c:when>
                                            			<c:when test="${0 eq requestHistory.amount}">
                                            				<input type="text" value="${requestHistory.scheduled_amount}" style="width: 40px;">
                                            			</c:when>
                                            		</c:choose>
                                            		
                                            		</td>
                                            		<td>
                                            		<select id="${requestHistory.request_number}_wId">
                                            		<c:choose>
                                            			<c:when test="${0 ne requestHistory.complete_warehouse_id}">
                                            				<c:choose>
                                            					<c:when test="${requestHistory.status_id eq 2 || requestHistory.status_id eq 3}">
                                            					<c:forEach var='name' items="${warehouseName}">
                                            						<c:choose>
                                            							<c:when test="${requestHistory.warehouse_id ne requestHistory.complete_warehouse_id}">
                                            								<option value="${name.warehouse_id}" <c:if test="${requestHistory.complete_warehouse_id eq name.warehouse_id}"> selected="selected" style="color:#FF0000;" </c:if> style="width: 60px;">${name.warehouse_name}</option>
                                            							</c:when>
                                            							<c:when test="${requestHistory.warehouse_id eq requestHistory.complete_warehouse_id}">
                                            								<option value="${name.warehouse_id}" <c:if test="${requestHistory.complete_warehouse_id eq name.warehouse_id}"> selected="selected" </c:if> style="width: 60px;">${name.warehouse_name}</option>
                                            							</c:when>
                                            						</c:choose>
                                            				
                                            						<%-- <option value="${name.complete_warehouse_id}" <c:if test="${requestHistory.complete_warehouse_id eq name.complete_warehouse_id}"> selected="selected" </c:if> style="width: 60px;">${name.warehouse_name}</option> --%>
                                            					</c:forEach>
                                            					</c:when>
                                            					
                                            					<c:when test="${requestHistory.status_id eq 1}">			
                                            					<c:forEach var='name' items="${warehouseName}">
                                            						<option value="${name.warehouse_id}" <c:if test="${requestHistory.warehouse_id eq name.warehouse_id}"> selected="selected" </c:if> style="width: 60px;">${name.warehouse_name}</option>
                                            					</c:forEach>
                                            					</c:when>
                                            			
                                            				</c:choose>
                                            			</c:when>
                                            			<c:when test="${0 eq requestHistory.complete_warehouse_id}">
                                            				<c:forEach var='name' items="${warehouseName}">
                                            					<option value="${name.warehouse_id}" <c:if test="${requestHistory.warehouse_id eq name.warehouse_id}"> selected="selected" </c:if> style="width: 60px;">${name.warehouse_name}</option>
                                            				</c:forEach>
                                            			</c:when>
                                            		</c:choose>
                                            		
                                            		</select>
                                            		</td>
                                            		<td>${requestHistory.status}</td>
                                            		<%-- <td><button id="del" onclick='updateRequest("${requestHistory.request_number}","${requestHistory.isbn}")'>완료</button></td> --%>
                                            		<td><c:if test="${user_type_id eq 2 && requestHistory.status_id ne 2}">
                                            			<button id="up" style="width: 60px;">완료</button>
                                            		</c:if></td>
                                            	</tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                           </div>
                               
                                
                            </div>
                           
                        </div>
                    </div>
                 </div>
     <script src="http://maps.google.com/maps/api/js?sensor=true"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/scripts/main.js"></script>

</body>
</html>


<!-- Large modal -->

<!-- <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true"></div> -->
<div id="myModal" class="modal_box">
    <div class="modal-dialog modal-lg">
        <div class="modal-content" style="margin-top: 20%; width: 1000px;">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">입고신청</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="closeX();">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p style="text-align: center;">
                <input type="text" name="bookSearch" id="bookSearch" style=" width: 300px; line-height: 1.5; color: #495057; background-color: #fff; background-clip: padding-box; border: 1px solid #ced4da; border-radius: .25rem; height: calc(2.25rem + 2px); margin-top: 5px;">
                          	<a href="javascript:void(0);" onclick="inWarehouseSearch();">
                          	<img alt="search" src="${pageContext.request.contextPath}/assets/images/avatars/search.png" style="width: 30px; margin-top: 7px; margin-left: 10px;">
                          	</a>
                </p>
                <div id="searchItems"></div>
                <p/>
                <p/>
                <!-- <div id="tableTh" style="display: none;">
                	<table class='mb-3 table table-hover'>
                	<thead>
                	<tr>
                	<th class='fixedHeader'>ISBN</th>
                	<th class='fixedHeader'>제품명</th>
                	<th class='fixedHeader'>저자/역자</th>
                	<th class='fixedHeader'>출판일</th>
                	<th class='fixedHeader'>입고일</th>
                	<th class='fixedHeader'>수량</th>
                	<th class='fixedHeader'>창고</th>
                	<th class='fixedHeader'>&nbsp;</th>
                	</tr>
                	</thead>
                	<tbody class='scrollbar-container ps--active-y' id="plusItems">
                	
                	</tbody>
                	</table>
                </div>
            </div>
            <div class="modal-footer" id="modal_footer" style="display: none;">
                <button type="button" class="btn btn-primary">신청</button>
            </div> -->
           
        </div>
        <div class="modal-footer" id="modal_footer">
                <button type="button" class="btn btn-primary" onclick="closeX();">Close</button>
        </div>
    </div>
</div>