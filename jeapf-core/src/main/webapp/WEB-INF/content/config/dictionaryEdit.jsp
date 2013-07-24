<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<title>配置管理</title>
		<%@ include file="/common/meta.jsp"%>
		<link rel="stylesheet" href="${ctx}/styles/css/style.css" type="text/css" media="all" />
		<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
	</head>

	<body>
		<form id="inputForm" action="${ctx }/config/dictionary/update" method="post">
			<input type="hidden" name="id" id="id" value="${id }"/>
			<table width="100%" border="0" align="center" cellpadding="0"
					class="table_all_border" cellspacing="0" style="margin-bottom: 0px;border-bottom: 0px">
				<tr>
					<td class="td_table_top" align="center">
						配置管理
					</td>
				</tr>
			</table>
			<table class="table_all" align="center" border="0" cellpadding="0" cellspacing="0" style="margin-top: 0px">
				<tr>
					<td class="td_table_1">
						<span>配置名称：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="text" class="input_240" id="name" name="name"
							value="${dictionary.name }" />
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>配置描述：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="text" class="input_240" id="description" name="description"
							value="${dictionary.description }" />
					</td>
				</tr>
			</table>
			
			<table class="table_all" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="td_table_1">
						<span>添加选项：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<input type="button" class="button_70px" value="添加选项" onclick="addItem()">
					</td>
				</tr>
				<tr>
					<td class="td_table_1">
						<span>选项列表：</span>
					</td>
					<td class="td_table_2" colspan="3">
						<table class="table_all" align="center" border="0" cellpadding="0" cellspacing="0" id="itemTable">
							<c:forEach var="item" items="${dictionary.dictionaryItems}" varStatus="s">
								<tr>
									<td class="td_table_1">
										<input type="text" value="${item.orderby }" name='orderbys' size="2" readonly>
									</td>
									<td class="td_table_2">
										<input type="text" value='${item.name }' class='input_520' name='itemNames' >
									</td>
									<td class="td_table_2">
										<a href='javascript:void(0)' onclick='delRow(${item.orderby - 1})' class='btnDel' title='删除'>删除</a>
									</td>
								</tr>
								<c:set var="index" value="${item.orderby }"/>
							</c:forEach>
						</table>
					</td>
				</tr>
			</table>
			<table align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr align="left">
					<td colspan="1">
						<input type="submit" class="button_70px" name="submit" value="提交">
						&nbsp;&nbsp;
						<input type="button" class="button_70px" name="reback" value="返回"
							onclick="history.back()">
					</td>
				</tr>
			</table>
			
			<script type="text/javascript">
			var order = ${index + 1};
			function addItem() {
				var table = document.getElementById("itemTable");
				var row = table.insertRow(-1);
				var cell = row.insertCell(-1);
				cell.innerHTML = "<input type=\"text\" value=\"" + order + "\" name='orderbys' size=\"2\" readonly>";
				cell.className = "td_table_1";
				
				cell = row.insertCell(-1);
				cell.innerHTML = "<input type=\"text\" value='' class='input_520' name='itemNames' >";
				cell.className = "td_table_2";
				
				cell = row.insertCell(-1);
				cell.innerHTML = "<a href='javascript:void(0)' onclick='delRow(" + (order - 1) + ")' class='btnDel' title='删除'>删除</a>";
				cell.className = "td_table_2";
				order = order + 1;
			}
			
			function delRow(rowId) {
				var table = document.getElementById("itemTable");
				table.deleteRow(rowId);
				order = order - 1;
				var rns = table.rows.length;
				if(rns >= 1) {
					var cns = table.rows(0).cells.length;
					var idx;
					for(idx = 0; idx < rns; idx++) {
						table.rows(idx).cells(0).innerHTML="<input type=\"text\" value=\"" + (idx + 1) + "\" name='orderbys' size=\"2\" readonly>";
						table.rows(idx).cells(cns - 1).innerHTML="<a href='javascript:void(0)' onclick='delRow(" + idx + ")' class='btnDel' title='删除'>删除</a>";
					}
				}
			}
			
			function validateInput(){
					var table = document.getElementById("itemTable");
					var rowLen = table.rows.length;
					if(rowLen == 0) {
						alert("请添加选项");
						return false;
					}
					var warning = "";
					$("input[name='itemNames']").each(function(){
						var item = $(this).val();
						if(item == '') {
							warning = "选项列表 不能为空";
						}
					});
					if(warning != '') {
						alert(warning);
						return false;
					}
			}
			</script>
		</form>
	</body>
</html>
