<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<form name="updateNonPaidForm" id="updateNonPaidForm" method="post">
<table style="width:100%">
  <tr>
  <td>姓名</td> <td><input type="text" id="name" name="name" value='<s:property value="customer.name"/>' onchange="checkName()"/>*</td>
  </tr>
  
  <tr>
   <td>性别</td> 
  
  <td>
   <select name="sex" id="sex">
  		  <s:if test="customer.sex==0">
  			<option value="0" selected="selected">女</option>  
  			<option value="1">男</option>
  		  </s:if>
  		  <s:elseif test="customer.sex==1">
  			<option value="0" >女</option>  
  			<option value="1" selected="selected">男</option>
  		  </s:elseif>
  	</select>
  </td>
  </tr>
    <tr>
  <td>国籍</td> <td><input type="text" id="nationality"  name="nationality" value='<s:property value="customer.nationality"/>'/>*</td>
  </tr>
  <tr>
  <td>手机</td> <td><input type="text" id="mobilePhone" value='<s:property value="customer.mobilePhone"/>'  name="mobilePhone"/>*</td>
  </tr>
    <tr>
  <td>固定电话</td> <td><input type="text" id="deskPhone"  name="deskPhone" value='<s:property value="customer.deskPhone"/>'/>*</td>
  </tr>
   <tr>
  <td>职业</td> <td><input type="text" id="career"  name="career" value='<s:property value="customer.career"/>'/>*</td>
  </tr>
    <tr>
  <td>公司</td> <td><input type="text" id="company"  name="company" value='<s:property value="customer.company"/>'/>*</td>
  </tr>
  <tr>
  <td>地址</td> <td><input type="text" id="address" value='<s:property value="customer.address"/>'  name="address" />*</td>
  </tr>
  <tr>
   <td>备注</td> <td><textarea id="comments" name="comments"  cols="30" rows="5"><s:property value="customer.comments"/></textarea></td>
  </tr>
  
</table>
</form>
</body>
</html>