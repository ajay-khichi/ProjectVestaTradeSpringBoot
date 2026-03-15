<%@page import="com.ignishers.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
User isadmin = (User) session.getAttribute("user");
if(isadmin==null){
	response.sendRedirect("login");
}
%>  
<nav class="navbar navbar-expand-lg">
  <div class="container">

    <!-- Brand -->
    <a class="navbar-brand" href="customerhome">Vesta<span>Trade</span></a>

    <!-- Mobile Toggle -->
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#nav">
        <i class="bi bi-list"></i>
    </button>

    <div class="collapse navbar-collapse" id="nav">

      <!-- Center Menu -->
      <ul class="navbar-nav mx-auto gap-1">
        <li class="nav-item"><a class="nav-link active" href="customerhome">Home</a></li>
        <li class="nav-item"><a class="nav-link" href="portfolio">Portfolio</a></li>
        <li class="nav-item"><a class="nav-link" href="wallet">Wallet</a></li>
        <li class="nav-item"><a class="nav-link" href="orderhistory">Order History</a></li>
        <li class="nav-item"><a class="nav-link" href="editprofile">Edit Profile</a></li>
      </ul>

      <!-- Right Side -->
      <div class="d-flex align-items-center gap-3 ms-auto">

        <a href="logout" class="nav-link btn-login">Log Out</a>
        <!-- Profile Image -->
        <img src="/dp/<%=isadmin.getImgPath()%>" 
             alt="Profile"
             style="width:45px;height:45px;border-radius:50%;object-fit:cover;border:2px solid white;">
      </div>

    </div>
  </div>
</nav>

<div class="page-header">
  <div class="container">
    <div class="row align-items-end g-4">
      <div class="col-lg-5">
        <h1>
			<%
      	String msg = (String)session.getAttribute("welcome");
      	if(msg!=null)
      		out.print(msg);
      %>
		</h1>
        <p>Live prices and data for NSE &amp; BSE listed stocks</p>
      </div>
    </div>
  </div>
</div>
