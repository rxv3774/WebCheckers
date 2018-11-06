<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <meta http-equiv="refresh" content="10">
    <title>${title} | Web Checkers</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div class="page">

    <h1>Web Checkers Sign-In</h1>

    <div class="navigation">
        <a href="/">Home</a>
    </div>

    <div class="body">
        <p>Sign-In Screen!</p>
    </div>


    <#if showErrorMessage??>
        <div class="${messageType}">
            ${showErrorMessage}
        </div>
    </#if>

    <div>
        <form class="sign-in" action="/signIn" method="post">
            username: <input type="text" name="playerName"><br>
            <input type="submit" value="Submit">
        </form>
    </div>

</div>
</body>
</html>