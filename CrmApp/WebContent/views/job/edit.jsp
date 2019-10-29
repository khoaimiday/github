<%@page import="com.myclass.entity.Role"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Cập nhật Job</h4>
		</div>
	</div>
	<!-- /.row -->
	<!-- .row -->
	<div class="row">
		<div class="col-md-2 col-12"></div>
		<div class="col-md-8 col-xs-12">
			<div class="white-box">

				<!-- HIEN THI LOI -->
				<p style="color: red">${message }</p>

				<form class="form-horizontal form-material"
					action='<c:url value="/manager/job/edit"/>' method="post">
					<div class="form-group">
						<label class="col-md-12">Tên Job</label> <input type="hidden"
							value="${job.id }" name="id" />
						<div class="col-md-12">
							<input type="text" placeholder="Tên quyền"
								class="form-control form-control-line" value="${job.name }"
								name="name" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Start Date</label>
						<div class="col-md-12">
							<input type="date" class="form-control form-control-line"
								value="${job.startDate}" name="startDate" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">End Date</label>
						<div class="col-md-12">
							<input type="date" class="form-control form-control-line"
								value="${job.endDate}" name="endDate" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success">Edit Job</button>
							<a href="<c:url value="/manager/job" />" class="btn btn-primary">Quay
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