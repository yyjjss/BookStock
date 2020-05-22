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
                                    <div>창고조회
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
                                    <c:if test="${user_type_id eq 1}">
                                    	<button type="button" class="btn mr-2 mb-2 btn-primary" style = "float : right" id="memberBtn">등록</button>
                               		</c:if>  
                                        <table class="mb-0 table table-hover" id = "GoodsList">
                                            <thead>
                                            <tr>
                                                <th class='fixedHeader'>No.</th>
                                                <th class='fixedHeader'>창고명</th>
                                                <th class='fixedHeader'>우편번호</th>
                                                <th class='fixedHeader'>창고주소</th>
                                                <th class='fixedHeader'>창고인원</th>
                                                <th class='fixedHeader'></th>
                                                <th class='fixedHeader'></th>
                                            </tr>
                                            </thead>
                                            <tbody class="scrollbar-container ps--active-y">
                                            <c:if test="${empty warehouseList}">
                                            	<tr>
                                                <th scope="row"></th>
                                                <td colspan="5">등록된 창고가 없습니다.</td>
                                            </tr>
                                            </c:if>
                                            <c:if test="${!empty warehouseList}">
                                            <c:set var="num" value="0"/>
                                            <c:forEach var = "wList"  items = "${warehouseList}">
	                                            <c:set var="num" value="${num+1}"/>
	                                            <tr>
	                                                <th scope="row" style="width: 80px;">${num}</th>
	                                                <td>${wList.warehouse_name}</td>
	                                                <td>${wList.zip}</td>
	                                                <td>${wList.address} ${wList.address_detail}</td>
	                                                <td>${wList.employee_count}</td>
	                                                <c:if test="${user_type_id eq 1}">
	                                                <td style="width: 80px;"><button class="mb-2 mr-2 btn btn-primary" onclick = 'updateWarehouse("${wList.warehouse_id}","${wList.zip }","${wList.address}","${wList.address_detail}","${wList.warehouse_name}","${wList.employee_count}","${wList.maximum_quantity}")' >수정</button></td>
	                                                <td style="width: 80px;"><button class="mb-2 mr-2 btn btn-primary" onclick = "deleteWarehouse('${wList.warehouse_id}');">삭제</button></td>
	                                            	</c:if>
	                                            </tr>
                                            </c:forEach>
                                            </c:if>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <!-- Table영역 끝 -->
                            </div>
                        </div>
                    </div>
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/scripts/main.js"></script>
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	</body>
	<script type="text/javascript">
	function addressSelect(){
		new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	            // 예제를 참고하여 다양한 활용법을 확인해 보세요.

	            $("#zip").val(data.zonecode);
	            $("#address").val(data.address);
	            $("#zip_up").val(data.zonecode);
	            $("#address_up").val(data.address);
	        }
	    }).open();
	}
	
	function closeX() {
		var modal = document.getElementById('myModal');	
		modal.style.display = "none";
		window.location.reload();
	}

	$(document).on('click', '#memberBtn', function(){
		var modal = document.getElementById('myModal');	
		modal.style.display = "block";
	});

	function warehouseSave(){

		var warehouse_name = $("#warehouse_name").val();
		var zip = $("#zip").val();
		var address = $("#address").val();
		var address_detail = $("#address_detail").val();
		var employee_count = $("#employee_count").val();
		var maximum_quantity = $("#maximum_quantity").val();

		var warehouseData = {
				warehouse_name : warehouse_name,
				zip : zip,
				address : address,
				address_detail : address_detail,
				employee_count : employee_count,
				maximum_quantity : maximum_quantity
			}

		$.ajax({
			type : "post",
			url : "warehouseInsert.do",
				data : warehouseData,
			dataType : "json"
		}).done(function(args){
			if(args.cnt > 0){
				alert("창고를 등록했습니다.");
				location.href = "warehouse.do";
			}else {
				alert("실패");
			}
			
		}).fail(function(e){
			alert(e.responseText);
		})	

		
	}

	function updateWarehouse(warehouse_id, zip, address, address_detail, warehouse_name, employee_count, maximum_quantity){
		alert("up");
		var modal = document.getElementById('myModalUp');	
		modal.style.display = "block";

		$("#warehouse_name_up").val(warehouse_name);
		$("#zip_up").val(zip);
		$("#address_up").val(address);
		$("#address_detail_up").val(address_detail);
		$("#warehouse_name_up").val(warehouse_name);
		$("#employee_count_up").val(employee_count);
		$("#maximum_quantity_up").val(maximum_quantity);
		$("#warehouse_id_up").val(warehouse_id);
		
	}

	function warehouseUpdate(){
		var warehouse_id = $("#warehouse_id_up").val();
		var warehouse_name = $("#warehouse_name_up").val();
		var zip = $("#zip_up").val();
		var address = $("#address_up").val();
		var address_detail = $("#address_detail_up").val();
		var employee_count = $("#employee_count_up").val();
		var maximum_quantity = $("#maximum_quantity_up").val();

		var warehouseData = {
				warehouse_id : warehouse_id,
				warehouse_name : warehouse_name,
				zip : zip,
				address : address,
				address_detail : address_detail,
				employee_count : employee_count,
				maximum_quantity : maximum_quantity
			}

		$.ajax({
			type : "post",
			url : "warehouseUpdate.do",
				data : warehouseData,
			dataType : "json"
		}).done(function(args){
			if(args.cnt > 0){
				alert("창고를 수정했습니다.");
				location.href = "warehouse.do";
			}else {
				alert("실패");
			}
			
		}).fail(function(e){
			alert(e.responseText);
		})	
	}

	function deleteWarehouse(warehouse_id){

		if(confirm("창고를 삭제하시겠습니까?")){
			location.href = "warehouseDelete.do?warehouse_id="+warehouse_id; 
		}else{
		}
	}
</script>
</html>
<div id="myModal" class="modal_box">
<!-- 	<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
 -->    <div class="modal-dialog modal-lg">
        <div class="modal-content" style="margin-top: 20%;">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">창고등록</h5>
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
                                    <%-- <input id = "kwd" class = "mb-2 form-control-sm form-control" style = "width : 500px; display : inline;">
                                     <a href = "javascript:search();"><img alt="search" src="${pageContext.request.contextPath}/assets/images/avatars/search.png" style="width: 28px; margin-left: 10px; height : 25px; padding-bottom:5px;"></a>
                                        --%>
                                        <table class="mb-0 table table-hover" id = "searchResult">
                                           <tr>
                                                <th>창고명</th>
                                                <td><input type="text" id="warehouse_name" name="warehouse_name"></td>
                                            </tr>
                                            <tr>
                                                <th>우편번호</th>
                                                <td><input type="text" id="zip" name="zip" readonly> &nbsp;&nbsp; <button onclick="addressSelect();">주소검색</button></td>
                                            </tr>
                                            <tr>
                                                <th>주소</th>
                                                <td><input type="text" id="address" name="address" style="width: 500px" readonly></td>
                                            </tr>
                                            <tr>
                                                <th>상세주소</th>
                                                <td><input type="text" id="address_detail" name="address_detail" style="width: 500px"></td>
                                            </tr>
                                            <tr>
                                                <th>직원수</th>
                                                <td><input type="number" id="employee_count" name="employee_count"></td>
                                            </tr>
                                            <tr>
                                                <th>최대재고수용량</th>
                                                <td><input type="number" id="maximum_quantity" name="maximum_quantity"></td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <!-- Table영역 끝 -->
                            </div>

            </div>
		<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="warehouseSave();">등록</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick = "closeX();">취소</button>
            </div>
        </div>
    </div>
</div>

<!-- Large modal -->
<div id="myModalUp" class="modal_box">
    <div class="modal-dialog modal-lg">
        <div class="modal-content" style="margin-top: 20%;">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">창고수정</h5>
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
                                    	<input type="hidden" id="warehouse_id_up" name="warehouse_id">
                                        <table class="mb-0 table table-hover" id = "searchResult">
                                           <tr>
                                                <th>창고명</th>
                                                <td><input type="text" id="warehouse_name_up" name="warehouse_name"></td>
                                            </tr>
                                            <tr>
                                                <th>우편번호</th>
                                                <td><input type="text" id="zip_up" name="zip" readonly> &nbsp;&nbsp; <button onclick="addressSelect();">주소검색</button></td>
                                            </tr>
                                            <tr>
                                                <th>주소</th>
                                                <td><input type="text" id="address_up" name="address" style="width: 500px" readonly></td>
                                            </tr>
                                            <tr>
                                                <th>상세주소</th>
                                                <td><input type="text" id="address_detail_up" name="address_detail" style="width: 500px"></td>
                                            </tr>
                                            <tr>
                                                <th>직원수</th>
                                                <td><input type="number" id="employee_count_up" name="employee_count"></td>
                                            </tr>
                                            <tr>
                                                <th>최대재고수용량</th>
                                                <td><input type="number" id="maximum_quantity_up" name="maximum_quantity"></td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <!-- Table영역 끝 -->
                            </div>

            </div>
		<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="warehouseUpdate();">수정</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick = "closeX();">취소</button>
            </div>
        </div>
    </div>
</div>

