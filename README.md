
Конфигурация проекта в Intellij IDEA:

SDK - 1.8

Language level - 8


Варианты запуска:

mvn clean test

mvn clean test -P local,all


Запуск аллюр отчета:
allure serve target/allure-results(абсолютный путь)
