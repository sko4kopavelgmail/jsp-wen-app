<html>
<body>

<form method="post" action="/main" enctype="multipart/form-data">
    <h4>Привет ${user.name}</h4>
    <div>
        <div>
            <#if message??>
                ${message}
            </#if>
        </div>
        <div>Время захода на сайт: ${time}</div>
        <h5>Ваши данные:</h5>
        <input placeholder="${user.getUserName()}" name="userName"><br/>
        <small>Данное поле выводит ваш логин. Если вы ходите поменять его, сделайте это тут</small>
        <br/><br/>
        <input placeholder="${user.getName()}" name="name"><br/>
        <small>Данное поле выводит ваше имя. Если вы ходите поменять его, сделайте это тут</small>
        <br/><br/>
        <input placeholder="${user.getLastName()}" name="lastName"><br/>
        <small>Данное поле выводит вашу фамилию. Если вы ходите поменять ее, сделайте это тут</small>
        <br/><br/>
        <input placeholder="${user.getPassword()}" name="password"><br/>
        <small>Данное поле выводит ваш пароль. Если вы ходите поменять его, сделайте это тут</small>
        <br/><br/>
        <input type="submit" value="Сохранить">
        <p>количество посещений = ${user.getCount()}</p><br/>

        <h4>То, о чем вы мечтаете:</h4>
        <div>
            <#list nodes as node>
                <div>
                    <div>Ваша мечта:  ${node.getDream().getDescription()}</div>
                    <div>Вы начали мечтать об этом ${node.getDream().getStartYear()} год(а) назад</div>
                <#if node.getDream().isDone()>
                <div>И вы достигли ващей мечты!</div>
                <#else>
                <div>К сожалению, вы не достигли вашей мечты :(</div>
                </#if>
                <#if node.getDream().getFilename()??>
                     <img width="200" height="160" src="../img/${node.getDream().getFilename()}">
                </#if>

                </div>
            </#list>
        </div>
    </div>
</form>
<h1>-----------------------</h1>
<form method="post" action="/addDream" enctype="multipart/form-data">
    <div>
        <input type="text" placeholder="Раскажите о вашей мечте" name="description">
    </div>
    <br/>
    <div>
        <input type="text" placeholder="Сколько вы уже мечтаете об этом" name="Year">
    </div>
    <br/>
    <div>
        <label>
            <input type="checkbox" name="isDone">
            Исполнилась ли ваша мечта?
        </label>
    </div>
    <br/>
    <label>Загрузите фото, которое символизирует вашу мечту</label>
    <input type="file" name="file"><br/>
    <input type="submit" value="Рассказать">
</form>

</body>
</html>