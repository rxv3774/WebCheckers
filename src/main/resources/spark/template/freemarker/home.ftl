<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <meta http-equiv="refresh" content="10">
    <title>${title} | Web Checkers</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div class="page">

    <h1>Web Checkers</h1>

    <div class="navigation">
        <a href="/">Home</a>

        <#if signedin?? == false>
            <a href="/signIn">Sign-In</a>
        </#if>
    </div>

    <div class="body">
        <p>Welcome to the world of online Checkers.</p>
        <p>Please sign-in with the button above.</p>

        <#if signedin??>
            <div class="message ${messageType}">${signedin}</div>
        </#if>

        <#if playerLst??>
            <div class="message ${messageType}">${playerLst}</div>
        </#if>

        <#if errorMessage??>
            <div class="message ${messageType}">${errorMessage}</div>
        </#if>

        <#if signedin?? && showGameButton == true>
            <form action='/game' method='get'>
                <label for='name'>Name of other player:</label>
                <input name='name' placeholder='Enter a name'/>
                <button type='submit'>Start a game</button>
            </form>
        </#if>
    </div>
</div>
</body>
</html>