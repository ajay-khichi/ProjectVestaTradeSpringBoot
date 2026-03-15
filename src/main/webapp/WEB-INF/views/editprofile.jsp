<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ignishers.pojo.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/><meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Edit Profile | VestaTrade</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"/>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@300;400;500;600;700&family=DM+Mono:wght@300;400;500&display=swap"/>
  <link rel="stylesheet" href="css/style.css"/>
  <style>
    .profile-card {
      border: none;
      border-radius: 16px;
      box-shadow: 0 4px 24px rgba(0,0,0,0.10);
      overflow: hidden;
      transition: box-shadow 0.2s;
    }
    .profile-card:hover {
      box-shadow: 0 8px 32px rgba(0,0,0,0.16);
    }
    .card-header-pwd {
      background: linear-gradient(135deg, #1a73e8, #0d47a1);
      color: white;
      padding: 1.2rem 1.5rem;
    }
    .card-header-dp {
      background: linear-gradient(135deg, #00897b, #004d40);
      color: white;
      padding: 1.2rem 1.5rem;
    }
    .card-header-pwd h5, .card-header-dp h5 {
      margin: 0;
      font-weight: 600;
      font-size: 1.1rem;
      letter-spacing: 0.3px;
    }
    .dp-preview-wrap {
      display: flex;
      flex-direction: column;
      align-items: center;
      gap: 12px;
      margin-bottom: 1rem;
    }
    #dpPreview {
      width: 90px;
      height: 90px;
      border-radius: 50%;
      object-fit: cover;
      border: 3px solid #00897b;
      background: #f0f0f0;
      display: none;
    }
    .dp-placeholder {
      width: 90px;
      height: 90px;
      border-radius: 50%;
      background: linear-gradient(135deg, #00897b33, #1a73e822);
      border: 3px dashed #00897b;
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 2rem;
      color: #00897b;
    }
    #imageError {
      display: none;
      font-size: 0.85rem;
    }
    #imageSuccess {
      display: none;
      font-size: 0.85rem;
    }
    .size-bar-wrap {
      display: none;
      margin-top: 8px;
    }
    .section-divider {
      height: 32px;
    }
    .notice-box {
      background: #fff8e1;
      border-left: 4px solid #f9a825;
      border-radius: 8px;
      padding: 12px 16px;
      font-size: 0.85rem;
      color: #795548;
      margin-top: 2rem;
    }
  </style>
</head>
<body>

<%@include file="headeruser.jsp" %>

<%
  User cst = (User) session.getAttribute("user");
  String currentDp = (cst != null && cst.getImgPath() != null) ? cst.getImgPath() : "defaultImage.png";
%>

<div class="container py-5">
  <div class="row justify-content-center g-4">

    <!-- ===== CARD 1: Change Password ===== -->
    <div class="col-md-5">
      <div class="profile-card card">
        <div class="card-header-pwd">
          <h5><i class="bi bi-shield-lock me-2"></i>Change Password</h5>
        </div>
        <div class="card-body p-4">

          <!-- Password form success/error message -->
          <%
            String pwdMsg = (String) request.getAttribute("pwdMsg");
            if (pwdMsg != null) {
          %>
            <div class="alert alert-success alert-dismissible fade show py-2" role="alert">
              <i class="bi bi-check-circle me-1"></i> <%=pwdMsg%>
              <button type="button" class="btn-close py-2" data-bs-dismiss="alert"></button>
            </div>
          <% } %>

          <form id="pwdForm" action="/updatePassword" method="post">
            <div class="mb-3">
              <label for="newPassword" class="form-label fw-semibold">New Password</label>
              <div class="input-group">
                <span class="input-group-text"><i class="bi bi-lock"></i></span>
                <input type="password" class="form-control" name="password" id="newPassword"
                       placeholder="Enter new password" required minlength="6">
                <button class="btn btn-outline-secondary" type="button" id="togglePwd"
                        title="Show/hide password">
                  <i class="bi bi-eye" id="eyeIcon"></i>
                </button>
              </div>
              <div class="form-text text-muted">Minimum 6 characters.</div>
            </div>

            <div class="mb-4">
              <label for="confirmPassword" class="form-label fw-semibold">Confirm Password</label>
              <div class="input-group">
                <span class="input-group-text"><i class="bi bi-lock-fill"></i></span>
                <input type="password" class="form-control" name="confirmPassword" id="confirmPassword"
                       placeholder="Re-enter password" required>
              </div>
              <div id="pwdMismatch" class="text-danger small mt-1" style="display:none;">
                <i class="bi bi-exclamation-circle"></i> Passwords do not match.
              </div>
            </div>

            <div class="d-grid">
              <button class="btn btn-primary btn-lg" type="submit" id="pwdSubmitBtn">
                <i class="bi bi-floppy me-1"></i> Update Password
              </button>
            </div>
          </form>

        </div>
      </div>
    </div>

    <!-- ===== CARD 2: Update Profile Picture ===== -->
    <div class="col-md-5">
      <div class="profile-card card">
        <div class="card-header-dp">
          <h5><i class="bi bi-person-circle me-2"></i>Update Profile Picture</h5>
        </div>
        <div class="card-body p-4">

          <!-- DP form success/error message -->
          <%
            String dpMsg = (String) request.getAttribute("dpMsg");
            if (dpMsg != null) {
              boolean isDpError = dpMsg.toLowerCase().contains("select") || dpMsg.toLowerCase().contains("failed");
          %>
            <div class="alert <%=isDpError ? "alert-danger" : "alert-success"%> alert-dismissible fade show py-2" role="alert">
              <i class="bi <%=isDpError ? "bi-exclamation-circle" : "bi-check-circle"%> me-1"></i> <%=dpMsg%>
              <button type="button" class="btn-close py-2" data-bs-dismiss="alert"></button>
            </div>
          <% } %>

          <form id="dpForm" action="/updateDP" method="post" enctype="multipart/form-data">

            <!-- Current / Preview image -->
            <div class="dp-preview-wrap">
              <div class="dp-placeholder" id="dpPlaceholder">
                <i class="bi bi-person-fill"></i>
              </div>
              <img id="dpPreview" src="/dp/<%=currentDp%>" alt="Preview"/>
              <span class="text-muted small" id="dpFileName">No file chosen</span>
            </div>

            <div class="mb-2">
              <label for="dpInput" class="form-label fw-semibold">Select Image</label>
              <input type="file" class="form-control" name="dp" id="dpInput"
                     accept="image/*" required onchange="validateImage(this)">
            </div>

            <!-- Size progress bar -->
            <div class="size-bar-wrap" id="sizeBarWrap">
              <div class="d-flex justify-content-between small mb-1">
                <span>File size</span>
                <span id="sizeLabel">0 KB</span>
              </div>
              <div class="progress" style="height:6px;">
                <div class="progress-bar" id="sizeBar" role="progressbar"
                     style="width:0%; transition: width 0.3s;"></div>
              </div>
            </div>

            <!-- JS validation messages -->
            <div class="alert alert-danger py-2 mt-2" id="imageError" role="alert">
              <i class="bi bi-x-circle me-1"></i>
              <span id="imageErrorText"></span>
            </div>
            <div class="alert alert-success py-2 mt-2" id="imageSuccess" role="alert">
              <i class="bi bi-check-circle me-1"></i>
              <span id="imageSuccessText"></span>
            </div>

            <div class="d-grid mt-3">
              <button class="btn btn-lg text-white" id="dpSubmitBtn"
                      style="background: linear-gradient(135deg,#00897b,#004d40);" type="submit">
                <i class="bi bi-cloud-upload me-1"></i> Upload Picture
              </button>
            </div>
          </form>

        </div>
      </div>
    </div>

  </div><!-- row -->

  <!-- Notice -->
  <div class="row justify-content-center">
    <div class="col-md-10">
      <div class="notice-box">
        <i class="bi bi-info-circle me-1"></i>
        <strong>Notice:</strong> KYC details like PAN, Aadhar, Email, DOB, and Bank details cannot be updated online
        due to regulatory requirements. Please contact support.
      </div>
    </div>
  </div>

</div>

<%@include file="footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/main.js"></script>
<script>
  /* ── Password toggle ── */
  document.getElementById('togglePwd').addEventListener('click', function () {
    const input = document.getElementById('newPassword');
    const icon  = document.getElementById('eyeIcon');
    if (input.type === 'password') {
      input.type = 'text';
      icon.className = 'bi bi-eye-slash';
    } else {
      input.type = 'password';
      icon.className = 'bi bi-eye';
    }
  });

  /* ── Password match check ── */
  document.getElementById('pwdForm').addEventListener('submit', function (e) {
    const pwd  = document.getElementById('newPassword').value;
    const conf = document.getElementById('confirmPassword').value;
    const mismatch = document.getElementById('pwdMismatch');
    if (pwd !== conf) {
      e.preventDefault();
      mismatch.style.display = 'block';
    } else {
      mismatch.style.display = 'none';
    }
  });
  document.getElementById('confirmPassword').addEventListener('input', function () {
    const pwd  = document.getElementById('newPassword').value;
    const conf = this.value;
    document.getElementById('pwdMismatch').style.display = (pwd !== conf && conf.length > 0) ? 'block' : 'none';
  });

  /* ── Image validation: max 1 MB, show preview & size bar ── */
  function validateImage(input) {
    const errorDiv   = document.getElementById('imageError');
    const errorText  = document.getElementById('imageErrorText');
    const successDiv = document.getElementById('imageSuccess');
    const successText= document.getElementById('imageSuccessText');
    const sizeBarWrap= document.getElementById('sizeBarWrap');
    const sizeBar    = document.getElementById('sizeBar');
    const sizeLabel  = document.getElementById('sizeLabel');
    const submitBtn  = document.getElementById('dpSubmitBtn');
    const preview    = document.getElementById('dpPreview');
    const placeholder= document.getElementById('dpPlaceholder');
    const fileName   = document.getElementById('dpFileName');

    errorDiv.style.display   = 'none';
    successDiv.style.display = 'none';

    if (!input.files || input.files.length === 0) return;

    const file      = input.files[0];
    const maxBytes  = 1 * 1024 * 1024; // 1 MB
    const sizeKB    = (file.size / 1024).toFixed(1);
    const sizeMB    = (file.size / (1024 * 1024)).toFixed(2);
    const pct       = Math.min((file.size / maxBytes) * 100, 100);

    // File name label
    fileName.textContent = file.name + ' (' + (file.size >= 1024*1024 ? sizeMB + ' MB' : sizeKB + ' KB') + ')';

    // Size bar
    sizeBarWrap.style.display = 'block';
    sizeBar.style.width = pct + '%';

    if (file.size > maxBytes) {
      // Too large
      sizeBar.className = 'progress-bar bg-danger';
      errorText.textContent = 'Image is too large (' + sizeMB + ' MB). Maximum allowed size is 1 MB. Please choose a smaller image.';
      errorDiv.style.display   = 'block';
      successDiv.style.display = 'none';
      submitBtn.disabled = true;

      // Clear preview
      preview.style.display      = 'none';
      placeholder.style.display  = 'flex';
      return;
    }

    // Valid size
    sizeBar.className = 'progress-bar bg-success';
    successText.textContent = 'Image looks good! (' + sizeKB + ' KB) — ready to upload.';
    successDiv.style.display = 'block';
    submitBtn.disabled = false;

    // Live preview
    const reader = new FileReader();
    reader.onload = function (e) {
      preview.src            = e.target.result;
      preview.style.display  = 'block';
      placeholder.style.display = 'none';
    };
    reader.readAsDataURL(file);
  }

  /* ── Show current DP on load if exists ── */
  window.addEventListener('load', function () {
    const preview    = document.getElementById('dpPreview');
    const placeholder= document.getElementById('dpPlaceholder');
    if (preview.src && !preview.src.endsWith('null') && !preview.src.endsWith('undefined')) {
      preview.onload = function () {
        if (this.naturalWidth > 0) {
          preview.style.display     = 'block';
          placeholder.style.display = 'none';
        }
      };
      preview.onerror = function () {
        preview.style.display     = 'none';
        placeholder.style.display = 'flex';
      };
    }
  });
</script>
</body>
</html>
