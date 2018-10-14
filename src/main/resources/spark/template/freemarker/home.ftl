<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <title>${title} | Web Checkers</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
  <div class="page">
  
    <h1>Web Checkers</h1>
    
    <div class="navigation">
      <a href="/">Home</a>
        <a href="/signIn">Sign-In</a>
    </div>
    
    <div class="body">
      <p>Welcome to the world of online Checkers.</p>
        <p>Please sign-in with the button above.</p>

    <#if signedin??>
      <div class="message ${messageType}">${signedin}</div>
    </#if>

    </div>
    
  </div>
</body>
</html>
