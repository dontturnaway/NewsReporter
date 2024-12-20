Run:
http://127.0.0.1:8080/index

Supports:
1. VZ.ru: https://vz.ru/news/2024/12/5/1301716.html
2. Lenta.ru: https://lenta.ru/news/2024/12/05/v-moskovskom-vuze-otvetili-na-obvineniya-v-ugroze-uvolit-prepodavatelya-iz-za-tsveta-volos/
3. IZ.ru: https://iz.ru/1802157/evgeniia-pertceva/vino-i-liubov-kakim-budet-vinnyi-gorod-belyi-mys-v-gelendzhike
4. Sample of non-supported site: "https://www.vedomosti.ru/society/news/2024/12/05/1079628-sputnitse-durova-vernuli-tehniku?from=newsline"


### Initial task ###
___________________

## Чистая архитектура 1: система медиамониторинга

Задача: вам нужно разработать REST API (только backend!) для системы, которая поможет собирать информацию и готовить сводные отчёты о новостных публикациях в Интернете.

### Пользовательские сценарии:

Сценарий 1. Добавление новости. В систему передаётся URL новостного материала в Интернете. Система скачивает HTML по этому URL и создаёт на его основе сущность со следующими полями:

- дата (текущая дата)
- URL (нам его передали в запросе)
- название новости (его проще всего взять из тега title)

В ответ возвращается ID сущности.

Сценарий 2. Получение списка новостей. Система возвращает список (массив) ранее созданных сущностей с полями:

- ID
- дата
- URL
- название новости

Сценарий 3. Формирование сводного отчёта. В систему передаётся массив из нескольких ID. Система формирует и сохраняет на диск простой HTML-файл со списком примерно такого вида:

<ul>
  <li><a href="...">Заголовок новости 1</a><li>
  <li><a href="...">Заголовок новости 2</a><li>
</ul>

В ответ возвращается ссылка на этот файл.

### Важные замечания:

- Вам нужно самостоятельно продумать всё, что касается архитектуры этого приложения. Если будут вопросы — задавайте. Не бойтесь допускать ошибки: весь смысл домашки в том, чтобы потом их обсудить и исправить.
- Вы можете взять за основу любой фреймворк, но обращаться к методам и классам фреймворка можно только на слое Infrastructure. Другими словами, ни на слое Domain, ни на слое Application не должно использоваться ничего, кроме написанных вами классов.
- Для хранения сущностей вы можете использовать БД или файловую систему, это не принципиально, но — см. предыдущее замечание.

