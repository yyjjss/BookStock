<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page isELIgnored="false"%>
<%@ page session="true"%>
<!--${pageContext.request.contextPath}  -->
<!-- 로고 -->

        <div class="app-header header-shadow">
            <div class="app-header__logo">
                <div class="logo-src"></div>
                <div class="header__pane ml-auto">
                    <div>
                        <button type="button" class="hamburger close-sidebar-btn hamburger--elastic" data-class="closed-sidebar">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="app-header__mobile-menu">
                <div>
                    <button type="button" class="hamburger hamburger--elastic mobile-toggle-nav">
                        <span class="hamburger-box">
                            <span class="hamburger-inner"></span>
                        </span>
                    </button>
                </div>
            </div>
            <div class="app-header__menu">
                <span>
                    <button type="button" class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
                        <span class="btn-icon-wrapper">
                            <i class="fa fa-ellipsis-v fa-w-6"></i>
                        </span>
                    </button>
                </span>
            </div>    <div class="app-header__content">
               
                <div class="app-header-right">
                    <div class="header-btn-lg pr-0">
                        <div class="widget-content p-0">
                            <div class="widget-content-wrapper">
                                <div class="widget-content-left">
                                    <div class="btn-group">
                                    	<div class="widget-content-left  ml-3 header-user-info">
                                    		<div class="widget-heading" style="margin-top: 5px;">
                                       	 		${user_name}&nbsp;님
                                   		 	</div>
                               	 		</div>
                                        <a data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="p-0 btn">
                                            &nbsp;<img width="35px" src="${pageContext.request.contextPath}/assets/images/avatars/user_01.png" alt="">
                                        </a>
                                        <div tabindex="-1" role="menu" aria-hidden="true" class="dropdown-menu dropdown-menu-right">
                                            <a href="logout.do">
                                            <button type="button" tabindex="0" class="dropdown-item">LogOut</button>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                    </div>        
                 </div>
            </div>
        </div>        
        <div class="app-main">
                <div class="app-sidebar sidebar-shadow">
                    <div class="app-header__logo">
                        <div class="logo-src"></div>
                        <div class="header__pane ml-auto">
                            <div>
                                <button type="button" class="hamburger close-sidebar-btn hamburger--elastic" data-class="closed-sidebar">
                                    <span class="hamburger-box">
                                        <span class="hamburger-inner"></span>
                                    </span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="app-header__mobile-menu">
                        <div>
                            <button type="button" class="hamburger hamburger--elastic mobile-toggle-nav">
                                <span class="hamburger-box">
                                    <span class="hamburger-inner"></span>
                                </span>
                            </button>
                        </div>
                    </div>
                    <div class="app-header__menu">
                        <span>
                            <button type="button" class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
                                <span class="btn-icon-wrapper">
                                    <i class="fa fa-ellipsis-v fa-w-6"></i>
                                </span>
                            </button>
                        </span>
                    </div>    <div class="scrollbar-sidebar">
                        <div class="app-sidebar__inner">
                            <ul class="vertical-nav-menu">
                                <li class="app-sidebar__heading">제품관리</li>
                                <li>
                                    		  <a href="booksInfo.do" >
                                       		<i class="metismenu-icon"><img src="${pageContext.request.contextPath}/assets/images/avatars/book.png" width="40px" alt=""></i> 
                                    		&nbsp;&nbsp;제품조회
                                    </a>
                                </li>
                                <li class="app-sidebar__heading">재고관리</li>
                                <li>
                                    <a href="goodsStockInfo.do">
                                        	<i class="metismenu-icon"><img src="${pageContext.request.contextPath}/assets/images/avatars/box.png" width="40px" alt=""></i> 
                                    		&nbsp;&nbsp;재고조회
                                    </a>
                                    
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/in_warehouse.do">
                                      		<i class="metismenu-icon"><img src="${pageContext.request.contextPath}/assets/images/avatars/in_ware.png" width="40px" alt=""></i> 
                                    		&nbsp;&nbsp;입고
                                    </a>
                                    
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/out_warehouse.do">
                                        	<i class="metismenu-icon"><img src="${pageContext.request.contextPath}/assets/images/avatars/out_ware.png" width="40px" alt=""></i> 
                                    		&nbsp;&nbsp;출고
                                    </a>
                                </li>
                                <li class="app-sidebar__heading">창고관리</li>
                                <li>
                                    <a href="warehouse.do">
                                        	<i class="metismenu-icon"><img src="${pageContext.request.contextPath}/assets/images/avatars/warehouse.png" width="40px" alt=""></i> 
                                    		&nbsp;&nbsp;창고조회
                                    </a>
                                </li>
                                
                            </ul>
                        </div>
                    </div>
                </div>   
		
