<%--
  Created by IntelliJ IDEA.
  User: sam
  Date: 2018/7/14
  Time: 上午10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title> login </title>
    <style>
        .col-center-block {
            float: none;
            display: block;
            margin-left: auto;
            margin-right: auto;
            margin-top: 5%;
        }
    </style>
</head>

<body>
<div class="container">
    <div class="row myCenter">
        <div class="col-xs-6 col-md-5 col-center-block">
            <form class="form-signin" action="/check" method="post">
                <h2 class="form-signin-heading">请登录</h2>
                <div class="form-group">
                    <label for="username" class="sr-only">用户名 </label>
                    <input type="text" id="username" name="nickname" value="${nickname}" class="form-control" placeholder="用户名" required autofocus>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="sr-only">密码 </label>
                    <input type="password" id="inputPassword" name="password" value="${password}" class="form-control" placeholder="密码" required>
                </div>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me">
                        记住我 </label>
                    <a href="/admin/users/add" style="float: right">还没有账号？</a>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>

            </form>
        </div>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>

