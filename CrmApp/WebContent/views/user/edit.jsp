<%@page import="java.util.ArrayList"%>
<%@page import="javax.annotation.security.RolesAllowed"%>
<%@page import="com.myclass.entity.Role"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Cập nhật thành viên</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-2 col-12"></div>
		<div class="col-md-8 col-xs-12">
			<div class="white-box">
				<form class="form-horizontal form-material"
					action="<c:url value="/manager/user/edit"/>" method="post">
					<div class="form-group">
						<label class="col-md-12">Full Name</label>
							<input type="hidden" value="${user.id }" name="id"/>
						<div class="col-md-12">
							<input type="text" placeholder="Johnathan Doe" name="fullname"
								value="${user.fullname }" class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label for="example-email" class="col-md-12">Email</label>
						<div class="col-md-12">
							<input type="email" placeholder="johnathan@admin.com" 
								value="${user.email }" class="form-control form-control-line"
								name="email" id="example-email">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Password</label>
						<div class="col-md-12">
							<input type="password" name="password"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Avatar</label>
						<div class="col-md-12">
							<input type="text" placeholder="link to Avarta" name="avatar"
								class="form-control form-control-line">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-12">Select Role</label>
						<div class="col-sm-12">
							<select class="form-control form-control-line" name="role" >
								<c:forEach items="${roles }" var="role">
									<option value="${role.id }" >${ role.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success">Edit User</button>
							<a href="<c:url value="/manager/user" />" class="btn btn-primary">Quay
								lại</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="col-md-2 col-12"></div>
	</div>
	<!-- /.row -->
</div>