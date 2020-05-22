<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri = "http://www.springframework.org/tags/form" %>
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
		<link href="${pageContext.request.contextPath}/assets/css/jquery-ui.css" rel="stylesheet" type="text/css">
	<style>
		.fixedHeader {
			position: sticky;
			top: 0;
			background-color: #ffffff;
		}
		/* 제품재고 */
		#monthpicker {
			width: 60px;
		}
		#btn_monthpicker {
			background: url('${pageContext.request.contextPath}/assets/images/datepicker.png');
			border: 0;
			height: 24px;
			overflow: hieen;
			text-indent: 999;
			width: 24px;
		}
		
		/* 창고재고 */
		#warehouse_monthpicker {
			width: 60px;
		}
		#warehouse_btn_monthpicker {
			background: url('${pageContext.request.contextPath}/assets/images/datepicker.png');
			border: 0;
			height: 24px;
			overflow: hieen;
			text-indent: 999;
			width: 24px;
		}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.mtz.monthpicker.js"></script>
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
                                    <div>재고조회
                                    </div>
                                </div>
                                <!-- <div class="page-title-actions">
                                    <button type="button" data-toggle="tooltip" title="Example Tooltip" data-placement="bottom" class="btn-shadow mr-3 btn btn-dark">
                                        <i class="fa fa-star"></i>
                                    </button>
                                </div>     --></div>
                        </div>            
                                                
                         <ul class="body-tabs body-tabs-layout tabs-animated body-tabs-animated nav">
                            <li class="nav-item">
                                <a role="tab" class="nav-link active" id="tab-0" data-toggle="tab" href="#goodsStock">
                                    <span>제품재고조회</span>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a role="tab" class="nav-link" id="tab-1" data-toggle="tab" href="#warehouseStock">
                                    <span>창고재고조회</span>
                                </a>
                            </li>
                 
                        </ul>
                        
                        
                        
                        
                        <!-- 탭내용영역 -->
                        <div class="tab-content">
                        
                        <!-- 제품재고 -->
                        
                            <div class="tab-pane tabs-animation fade show active" id="goodsStock" role="tabpanel">
                                                <div class="row">
							<!-- Table영역 --> 
                            <div class="col-lg-6">
                                <div class="main-card mb-3 card" style = "width : 1150px;">
                                    <div class="card-body" style = "width : 100%"><!-- <h5 class="card-title"> -->
                                     <select class="form-control-sm form-control" id = "goods" style = "width : 500px; display : inline;">
                                          <option value = "0">전체</option>
                                          <c:forEach var = "goods" items = "${goodsList}">
                                          <option value = "${goods.isbn}">${goods.title}</option>
                                          </c:forEach>
                                          
                                     </select>
                                     <input id="monthpicker" type="text" class = "mb-2 form-control-sm form-control" style = "width : 100px;display:inline;"/>
										<input type="button" id="btn_monthpicker" value=" " />
                                     <a href = "javascript:getStocksList();"><img alt="search" src="${pageContext.request.contextPath}/assets/images/avatars/search.png" style="width: 28px; margin-left: 10px; height : 25px; padding-bottom:5px;"></a>
                                     <%-- <a href = "javascript:downExcelStockList();"><img alt="엑셀다운" src="${pageContext.request.contextPath}/assets/images/excel-xls-icon.png" style="width: 28px; margin-left: 10px; height : 25px; padding-bottom:5px;"></a>   --%> 
                                        <br>
                                        <div class="scroll-area-sm" style = "height : 800px; width : 100%;">
                                        <table class="mb-3 table table-hover" id = "StocksList">
                                            <thead>
                                            <tr>
                                                <th class='fixedHeader'>날짜</th>
                                                <th class='fixedHeader'>제품명</th>
                                                <th class='fixedHeader'>입고수량</th>
                                                <th class='fixedHeader'>출고수량</th>
                                                <th class='fixedHeader'>재고</th>
                                            </tr>
                                            </thead>
                                            <tbody class="scrollbar-container ps--active-y">
                                            <c:if test="${empty stockHistory}">
                                            	<tr>
                                                <th scope="row"></th>
                                                <td colspan="5">등록된 제품이 없습니다.</td>
                                            </tr>
                                            </c:if>
                                            <c:if test="${!empty stockHistory}">
                                            <c:forEach var = "stock"  items = "${stockHistory}" varStatus="i">
	                                            <tr>
	                                                <th scope="row"><fmt:formatDate value="${stock.date}" pattern="yyyy-MM-dd"/></th>
	                                                <td>${stock.title}</td>
	                                                <td>${stock.incoming_amount}</td>
	                                                <td>${stock.release_amount}</td>
	                                                <td>${stock.stock}</td>
	                                                <%-- <td><button class="mb-2 mr-2 btn btn-primary" onclick = "deleteBook('${book.isbn}');">삭제</button></td> --%>
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
                        
                        <!-- 제품재고끝 -->
                        
                        
                        
                        <!-- 창고재고 -->
                        
                            <div class="tab-pane tabs-animation fade" id="warehouseStock" role="tabpanel">
                                                <div class="row">
							<!-- Table영역 --> 
                            <div class="col-lg-6">
                                <div class="main-card mb-3 card" style = "width : 1150px;">
                                    <div class="card-body" style = "width : 100%"><!-- <h5 class="card-title"> -->
                                     
                                     <select class="form-control-sm form-control" id = "warehouse" style = "width : 200px; display : inline;">
                                          <option value = "0">창고전체</option>
                                          <c:forEach var = "warehouse" items = "${warehouseList}">
                                          <option value = "${warehouse.warehouse_id}">${warehouse.warehouse_name}</option>
                                          </c:forEach>
                                          
                                     </select>
                                     
                                     <select class="form-control-sm form-control" id = "warehouse_goods" style = "width : 500px; display : inline;">
                                          <option value = "0">제품전체</option>
                                          <c:forEach var = "goods" items = "${goodsList}">
                                          <option value = "${goods.isbn}">${goods.title}</option>
                                          </c:forEach>
                                          
                                     </select>
                                     
                                     <input id="warehouse_monthpicker" type="text" class = "mb-2 form-control-sm form-control" style = "width : 100px;display:inline;"/>
										<input type="button" id="warehouse_btn_monthpicker" value=" " />
                                     <a href = "javascript:getWarehouseStocksList();"><img alt="search" src="${pageContext.request.contextPath}/assets/images/avatars/search.png" style="width: 28px; margin-left: 10px; height : 25px; padding-bottom:5px;"></a>
                                     <%-- <a href = "javascript:downExcelWarehouseStockList();"><img alt="엑셀다운" src="${pageContext.request.contextPath}/assets/images/excel-xls-icon.png" style="width: 28px; margin-left: 10px; height : 25px; padding-bottom:5px;"></a> --%>      
                                        <br>
                                        <div class="scroll-area-sm" style = "height : 800px; width : 100%;">
                                        <table class="mb-0 table table-hover" id = "WarehouseStocksList">
                                            <thead>
                                            <tr>
                                                <th class='fixedHeader'>날짜</th>
                                                <th class='fixedHeader'>창고명</th>
                                                <th class='fixedHeader'>입고수량</th>
                                                <th class='fixedHeader'>출고수량</th>
                                                <th class='fixedHeader'>재고</th>
                                            </tr>
                                            </thead>
                                            <tbody class="scrollbar-container ps--active-y">
                                            <c:if test="${empty warehouseStockHistory}">
                                            	<tr>
                                                <th scope="row"></th>
                                                <td colspan="5">조회가능한 재고가 없습니다.</td>
                                            </tr>
                                            </c:if>
                                            <c:if test="${!empty warehouseStockHistory}">
                                            <c:forEach var = "stock"  items = "${warehouseStockHistory}" varStatus="i">
	                                            <tr>
	                                                <th scope="row"><fmt:formatDate value="${stock.date}" pattern="yyyy-MM-dd"/></th>
	                                                <td>${stock.warehouse_name}</td>
	                                                <td>${stock.incoming_amount}</td>
	                                                <td>${stock.release_amount}</td>
	                                                <td>${stock.stock}</td>
	                                                <%-- <td><button class="mb-2 mr-2 btn btn-primary" onclick = "deleteBook('${book.isbn}');">삭제</button></td> --%>
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
                        

                        </div>
                    </div>
    <!-- <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script> -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/scripts/main.js"></script>

	</body>
	<script type="text/javascript">
	
	/* MonthPicker 옵션 */
	options = {
		pattern: 'yyyy-mm', // Default is 'mm/yyyy' and separator char is not mandatory
		selectedYear: 2020,
		startYear: 2001,
		finalYear: 2030,
		monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
	};


	/* 제품재고 */
	
	/* MonthPicker Set */
	$('#monthpicker').monthpicker(options);
	
	/* 버튼 클릭시 MonthPicker Show */
	$('#btn_monthpicker').bind('click', function () {
		$('#monthpicker').monthpicker('show');
	});


	/* 창고재고 */
	
	/* MonthPicker Set */
	$('#warehouse_monthpicker').monthpicker(options);
	
	/* 버튼 클릭시 MonthPicker Show */
	$('#warehouse_btn_monthpicker').bind('click', function () {
		$('#warehouse_monthpicker').monthpicker('show');
	});
	
	/* MonthPicker 선택 이벤트 */
	$('#monthpicker').monthpicker().bind('monthpicker-click-month', function (e, month) {
		//alert("선택하신 월은 : " + month + "월");
	});

	function getStocksList(){
		var isbn = document.getElementById("goods").value;
		var month = document.getElementById("monthpicker").value;

		$.ajax({
			type : "post",
			url : "calcMonthStock.do",
			data : "isbn="+isbn+"&month="+month,
			dataType : "json"
		}).done(function(args){
			console.log(args);
			var msg = "<thead>";
			msg += "<tr>";
			msg += "<th class='fixedHeader'>날짜</th><th class='fixedHeader'>제품명</th><th class='fixedHeader'>입고수량</th><th class='fixedHeader'>출고수량</th><th class='fixedHeader'>재고</th>";
			msg += "</tr></thead><tbody>"
			if(args.length == 0){
				msg += "<tbody><tr><th scope='row'></th><td colspan='5'>검색결과가 없습니다.</td></tr></tbody>"
			}else{
				for(var i = 0; i <= args.length - 1; i++){
					msg += "<tr><th scope='row'>"+args[i].dumpDate+"</th>";
					msg += "<td>"+args[i].title+"</td>";
					msg += "<td>"+args[i].incoming_amount+"</td>";
					msg += "<td>"+args[i].release_amount+"</td>";
					msg += "<td>"+args[i].stock+"</td>";

					
					msg += "</tr>";
				}
			}
			$("#StocksList").html(msg);
			
		}).fail(function(e){
			alert(e.responseText);
		})
	}

	function downExcelStockList(){
		var isbn = document.getElementById("goods").value;
		var month = document.getElementById("monthpicker").value;

		$.ajax({
			type : "post",
			url : "downExcelGoodsStock.do",
			data : "isbn="+isbn+"&month="+month
			//dataType : "json"
		}).done(function(args){
			alert("엑셀이 다운로드 되었습니다.");
			
		}).fail(function(e){
			alert(e.responseText);
		})
	}

	

	function getWarehouseStocksList(){
		//alert("warehouseStock in!");
		var isbn = document.getElementById("warehouse_goods").value;
		var month = document.getElementById("warehouse_monthpicker").value;
		var warehouse_id = document.getElementById("warehouse").value;
		//alert(warehouse_id);
		$.ajax({
			type : "post",
			url : "calcWarehouseStock.do",
			data : "isbn="+isbn+"&month="+month+"&warehouse_id="+warehouse_id,
			dataType : "json"
		}).done(function(args){
			console.log(args);
			var msg = "<thead>";
			msg += "<tr>";
			msg += "<th class='fixedHeader'>날짜</th><th class='fixedHeader'>창고명</th><th class='fixedHeader'>입고수량</th><th class='fixedHeader'>출고수량</th><th class='fixedHeader'>재고</th>";
			msg += "</tr></thead><tbody>"
			if(args.length == 0){
				msg += "<tbody><tr><th scope='row'></th><td colspan='5'>검색결과가 없습니다.</td></tr></tbody>"
			}else{
				for(var i = 0; i <= args.length - 1; i++){
					msg += "<tr><th scope='row'>"+args[i].dumpDate+"</th>";
					msg += "<td>"+args[i].warehouse_name+"</td>";
					msg += "<td>"+args[i].incoming_amount+"</td>";
					msg += "<td>"+args[i].release_amount+"</td>";
					msg += "<td>"+args[i].stock+"</td>";
					msg += "</tr>";
				}
			}
			$("#WarehouseStocksList").html(msg);
			
		}).fail(function(e){
			alert(e.responseText);
		})
	}


	function downExcelWarehouseStocksList(){
		var isbn = document.getElementById("warehouse_goods").value;
		var month = document.getElementById("warehouse_monthpicker").value;
		var warehouse_id = document.getElementById("warehouse").value;

		$.ajax({
			type : "post",
			url : "downExcelWarehouseStock.do",
			data : "isbn="+isbn+"&month="+month+"&warehouse_id="+warehouse_id
			//dataType : "json"
		}).done(function(args){
			alert("엑셀이 다운로드 되었습니다.");
			
		}).fail(function(e){
			alert(e.responseText);
		})
	}
		
	</script>
</html>



