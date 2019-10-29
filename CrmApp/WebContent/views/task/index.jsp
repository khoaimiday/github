<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Danh sách JOB</h4>
		</div>
		<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
			<a href="<c:url value="/manager/task/add" />" class="btn btn-sm btn-success">Thêm mới</a>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /row -->
	<div class="row">
		<div class="col-sm-12">
			<div class="white-box">
				<div class="table-responsive">
					<table class="table" id="example">
						<thead>
							<tr>
								<th>STT</th>
								<th>Tên Task</th>
								<th>Start Date</th>
								<th>End Date</th>
								<th>User Name</th>
								<th>Job Name</th>
								<th>Status Name</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listTask }" var="item" varStatus="loop">
							<tr>
								<td>${loop.count }</td>
								<td>${item.taskName }</td>
								<td>${item.startDate }</td>
								<td>${item.endDate }</td>
								<td>${item.userName }</td>
								<td>${item.jobName }</td>
								<td>${item.statusName }</td>
								<td><a href="<c:url value="/manager/task/edit?id=${item.id}"/>" class="btn btn-sm btn-primary">Sửa</a> <a
									href="<c:url value="/manager/task/delete?id=${item.id}"/>" class="btn btn-sm btn-danger">Xóa</a></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- /.row -->
</div>