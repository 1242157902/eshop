<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<br/>
    当前第[${page.pagenum}]页&nbsp;&nbsp;
    <c:if test="${page.pagenum>1}">
    <a href="${page.url}?pagenum=${page.pagenum-1}&c_id=${c_id }&c_parent=${c_parent}">上一页</a>
    </c:if>
 
    <c:forEach var="pagenum" begin="${page.startPage}" end="${page.endPage}"> 
         [ <a href="${page.url}?pagenum=${pagenum}&c_id=${c_id }&c_parent=${c_parent}">${pagenum}</a>]
    
    </c:forEach>
    &nbsp;
    <c:if test="${page.pagenum<page.totalpage}">
         <a href="${page.url}?pagenum=${page.pagenum+1}&c_id=${c_id }&c_parent=${c_parent}">下一页</a>
    </c:if>    
    共[${page.totalpage}]页，共[${page.totalrecord}]条记录
    <input type="text" name="pagenum" style="width:35px;border:1px solid #E3E3E3;" id="pagenum">
    <input type="button" value=" Go" onclick="goWhich(document.getElementById('pagenum'))" >
    <script type="text/javascript">
    function goWhich(input){
    var pagenum=input.value;
    
    if(pagenum==null||pagenum==""){
    
    alert("请输入页码!");
    
    return;
    }
    
    if(!pagenum.match("\\d+")){
	    alert("请输入数字！");
	    input.value="";
	    return;
     }
     
     if(pagenum<1 || pagenum > ${page.totalpage} ) {
     
     alert("无效的页码！");
     input.value="";
     return;
     
     }
    
    window.location.href="${page.url}?c_id=${c_id}&c_parent=${c_parent}&pagenum="+pagenum;
    
    }
    
    </script>