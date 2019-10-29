<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Thêm mới Job</h4>
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
					action='<c:url value="/manager/job/add"/>' method="post">
					<div class="form-group">
						<label class="col-md-12">Tên Job</label>
						<div class="col-md-12">
							<input type="text" placeholder="Tên Job"
								class="form-control form-control-line" name="name" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">Start date</label>
						<div class="col-md-12">
							<input type="date" placeholder="Mô tả" 
								class="form-control form-control-line" name="startDate" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-12">End date</label>
						<div class="col-md-12">
							<input type="date" placeholder="Mô tả" 
								class="form-control form-control-line" name="endDate" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-success">Add Job</button>
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