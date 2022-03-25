<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="#" class="brand-link">
      <img src="<c:url value="/resources/adminLTE/dist/img/AdminLTELogo.png"/>" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
      <span class="brand-text font-weight-light">AdminLTE 3</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image sidebar-img-wrapper">
          <img id="sidebar_user_image_field" src="${userSession.getUser_image() }" class="img-circle elevation-2 " alt="User Image">
        </div>
        <div class="info ml-3">
          <a href="#" class="d-block" id="sidebar_user_id_field">${userSession.getUser_id() }</a>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
			   
          <li class="nav-item">
            <a href="<c:url value='/ledger/details.do'/>" class="nav-link <c:if test="${menu eq 'ledger'}">active</c:if>">
              <i class="nav-icon fas fa-table"></i>
              <p>
				가계부 내역
              </p>
            </a>
          </li>
		  
		  <li class="nav-item">
            <a href="<c:url value='/ledger/calendar.do'/>" class="nav-link <c:if test="${menu eq 'calendar'}">active</c:if>">
              <i class="nav-icon fas fa-calendar-alt"></i>
              <p>
				가계부 달력
              </p>
            </a>
          </li>
		  
		  <li class="nav-item <c:if test="${menu eq 'stats'}">menu-open</c:if>">
            <a href="#" class="nav-link <c:if test="${menu eq 'stats'}">active</c:if>">
              <i class="nav-icon fas fa-chart-pie"></i>
              <p>
				가계부 통계
              </p>
              <i class="fas fa-angle-left right"></i>
            </a>
            <ul class="nav nav-treeview">
            	<li class="nav-item">
            		<a href="<c:url value='/stats/category.do'/>" class="nav-link <c:if test="${subMenu eq 'category'}">active</c:if>">
            			<i class="far fa-circle nav-icon"></i>
            			<p>분류별 통계</p>
            		</a>
            	</li>
            	<li class="nav-item">
            		<a href="<c:url value='/stats/yearly.do'/>" class="nav-link <c:if test="${subMenu eq 'yearly'}">active</c:if>">
            			<i class="far fa-circle nav-icon"></i>
            			<p>연간 통계</p>
            		</a>
            	</li>
            </ul>
          </li>
		  
		  <li class="nav-item">
            <a href="<c:url value='/qna/list.do'/>" class="nav-link <c:if test="${menu eq 'qna'}">active</c:if>">
              <i class="nav-icon fas fa-comments"></i>
              <p>
				Q&A 게시판
              </p>
            </a>
          </li>
          
          <li class="nav-item">
            <a href="<c:url value='/my/myinfo.do'/>" class="nav-link <c:if test="${menu eq 'my'}">active</c:if>">
              <i class="nav-icon fa fa-user"></i>
              <p>
				회원 정보 수정
              </p>
            </a>
          </li>
          
          <c:if test="${userSession.user_idx eq 1}">
          	<li class="nav-item <c:if test="${menu eq 'admin'}">menu-open</c:if>">
            	<a href="#" class="nav-link <c:if test="${menu eq 'admin'}">active</c:if>">
            	  <i class="nav-icon fa fa-lock"></i>
            	  <p>
					관리자 페이지
            	  </p>
            	  <i class="fas fa-angle-left right"></i>
            	</a>
            	<ul class="nav nav-treeview">
            	<li class="nav-item">
            		<a href="<c:url value='/admin/userManagement.do'/>" class="nav-link <c:if test="${subMenu eq 'user'}">active</c:if>">
            			<i class="far fa-circle nav-icon"></i>
            			<p>회원 관리</p>
            		</a>
            	</li>
            	<li class="nav-item">
            		<a href="<c:url value='/admin/mail/writeMail.do'/>" class="nav-link <c:if test="${subMenu eq 'email'}">active</c:if>">
            			<i class="far fa-circle nav-icon"></i>
            			<p>메일 쓰기</p>
            		</a>
            	</li>
            </ul>
          	</li>
          </c:if>
		  
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>