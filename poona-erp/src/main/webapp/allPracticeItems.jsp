<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<table class="dynatable">
  <thead>
    <tr>
      <th style="width:80px">行号</th>
      <th>上课日期</th>
      <th>课程名</th>
      <th>备注</th>
    </tr>
  </thead>
  <tbody>
    <s:iterator value="practiceRecords" status="listStatus">
    <tr>
    <td style="width:80px">
            <s:property value="#listStatus.index+1"/>
    </td>
    <td><s:date name="practiceDate" format="yyyy-MM-dd" /></td>
    <td><s:property value="className"/></td>
    <td><s:property value="comment"/></td>
   </tr>
    
    </s:iterator>
    
   <tbody>
</table>
</body>
</html>