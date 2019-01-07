<html>
<head>
    <link rel="stylesheet" href="../static/index.css">
</head>

<body>
<h4>Привет, это моя курсовая. Пожалуйста, авторизуйтесь:</h4>
<form method="post">
    <input type="text" placeholder="Username" name="userName"/>
    <input type="password" placeholder="Password" name="password"/>
    <button type="submit">Let me in</button>
    <br/>
    <a>${message!}</a>
</form>
<a href="/registration">Или вы можете зарегистрироваться</a>
<br/>
<a>Если вы уже залогинились, ссылка на </a><a href="/main">профиль</a>
</body>

</html>