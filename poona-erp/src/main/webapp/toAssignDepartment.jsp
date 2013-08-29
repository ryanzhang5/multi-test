<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<input type="hidden" name="userId" id="userId" value='<s:property value="userId" />'	 />
            <s:iterator value="rolevoList" >
            	<s:if test="selected == true">
					<input type="checkbox" id='<s:property value="id" />' checked="checked" value='<s:property value="id" />'  name='myRoleName'  /><s:property value="roleName" />
				</s:if>
				<s:else>
				<input type="checkbox" id='<s:property value="id" />' value='<s:property value="id" />'  name='myRoleName'  /><s:property value="roleName" />
				</s:else>
			</s:iterator>

</body>
</html>