<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<title>Left</title>
<%@ include file="/common/meta.jsp"%>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link href="${ctx}/styles/css/style.css" type="text/css"
	rel="stylesheet" />
<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
</head>

<body bgcolor="#f5f5f5" >
	<div class="browsers">
			<div class="brsDetail">
				<!-- <div class="node">
					<a id="node_1" class="nodeLink" href="#" target="_self" onclick="switchLeaf('1')">税务登记</a>
					<div class="leaf" id="leaf_1" style="display: none;">
						<a class="leafLink" href="#" target="_self" onclick="">税务登记</a>
					</div>
				</div> -->
				<frame:menu/>
			</div>
	</div>

	<script type="text/javascript">
	function switchLeaf(id) {
		var l = $("#leaf_"+id);
		var n = $("#node_"+id);
		var display = l.css('display');
		if(display && display == 'none') {
			l.css('display', 'block');
			n.css('background-image', 'url(${ctx}/styles/images/open.jpg)');
		}
		if(display && display == 'block') {
			l.css('display', 'none');
			n.css('background-image', 'url(${ctx}/styles/images/close.jpg)');
		}
	}
	</script>
</body>
</html>