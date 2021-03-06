# Пример использования библиотеки JaVers

Этот проект &mdash; маленькое демо-приложение, которое показывает, как можно
использовать библиотеку **JaVers** при сравнении сложных объектов. Здесь демонстрируется
одна из возможностей библиотеки &mdash; сравнение разных версий объектов и получение
изменений.

### Что делается в приложении

В приложении есть несколько классов, моделирующих данные. Это физические лица,
описанные в классе `Person`, они имеют некоторые свойства, такие как имя, фамилия, отчество,
а также набор атрибутов, заданных классом `PersonAttribute`.  Атрибуты - это некоторые признаки физического лица, например, образование.

Все полученные изменения анализируются, и затем собирается некоторая выборка из
"исторических" записей, в каждой такой записи запоминается, для какого класса, поля
и значений случилось то или иное изменение.

В конце работы все "исторические" записи выводятся в лог.