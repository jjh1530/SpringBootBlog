<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form>
		<input type="hidden" value="${principal.user.id }" id="id">
		<div class="form-group">
			<label for="username">Username</label> <input type="username" class="form-control" readonly="readonly" id="username" value="${principal.user.username}">
		</div>
		<c:if test="${empty principal.user.oauth }">
			<div class="form-group">
				<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
			</div>
		</c:if>
		<c:if test="${empty principal.user.oauth }">
			<div class="form-group">
				<label for="email">Email</label> <input type="email" class="form-control" placeholder="Enter email" value="${principal.user.email}" id="email" >
			</div>
		</c:if>
		<c:if test="${not empty principal.user.oauth }">
			<div class="form-group">
				<label for="email">Email</label> <input type="email" class="form-control" placeholder="Enter email" value="${principal.user.email}" id="email" readonly="readonly">
			</div>
		</c:if>

	</form>
	<button id="btn-update" class="btn btn-primary">회원수정완료</button>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>