<%@page import="com.ignishers.pojo.Stock"%>
<%@page import="com.ignishers.pojo.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
      <!DOCTYPE html>
      <html lang="en">

      <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Markets | VestaTrade</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" />
        <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />
        <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@300;400;500;600;700&family=DM+Mono:wght@300;400;500&display=swap" />
        <link rel="stylesheet" href="css/style.css" />
      </head>

      <body>

        <%@include file="headeradmin.jsp" %>


          <div class="container py-5">

            <div class="d-grid gap-2 col-6 mx-auto">
              <h2 class="heading">Stock Details</h2>
              <% Stock stk=(Stock)request.getAttribute("stk"); %>
              
                  <form action="amdstock" method="post">

<div class="mb-3">
<label>Symbol</label>
<input type="text" name="symbol" class="form-control" value="<%=stk.getSymbol() %>" required>
</div>

<div class="mb-3">
<label>Company Name</label>
<input type="text" name="companyName" value="<%=stk.getCompanyName() %>" class="form-control">
</div>

<div class="mb-3">
<label>Price per stock</label>
<input type="number" step="0.01" name="price" value="<%=stk.getCurrentPrice() %>" class="form-control">
</div>

<div class="mb-3">
<label>Total Shares</label>
<input type="number" name="shares" value="<%=stk.getAvailableShares() %>" class="form-control">
</div>

</form>

                <a href="adminstock" class="btn btn-info btn-lg mt-3">Back to Menu</a>
            </div>
          </div>
          </div>

          <%@include file="footer.jsp" %>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            <script src="js/main.js"></script>
      </body>

      </html>