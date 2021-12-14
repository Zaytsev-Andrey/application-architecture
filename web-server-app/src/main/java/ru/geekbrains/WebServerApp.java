package ru.geekbrains;

import ru.geekbrains.loader.ApplicationLoaderWithScanningControllers;

/**
 * За основу архитектуры Вэб сервера взят распространенный архитектурный шаблон MVC, а именно его реализация встречаемая
 * в некоторых источниках под названием "Модель 2" с централизованной логикой управления (единой точкой входа).
 * Единая точка входа реализована в классе ServerHandler, в задачи которого входит:
 * 1) чтение запроса из входящего сетевого потока;
 * 2) получение контроллера, соответствующего полученному запросу, из отображения контроллеров (реализация интерфейса
 * ControllerMapper);
 * 3) передача управления контроллеру;
 * 4) получение имени представления от контроллера;
 * 5) отображение имени представления в путь к файлу;
 * 6) запись ответа в исходящий сетевой поток.
 *
 * При составлении архитектурны применены следующие принципы SOLID:
 * 1) Single Responsibility Principle. Все классы наделены минимальными функциями:
 * 	- реализации интерфейса RequestController отвечают только за возврат представления соответствующего запросу, в
 * 	будущем могут быть дополнены взаимодействием с уровнем бизнес логики или непосредственно с уровнем данных для
 * 	получения дополнительных данных необходимых представлению;
 * 	- реализация интерфейса ControllerMapper отвечает только за хранние контроллеров и их возврат в соответствии
 * 	параметру запроса;
 * 	- реализация интерфейса TemplateResolver отвечает за отображение имени представления в непосредственный путь к файлу;
 * 	- основная роль ServerHandler последовательное взаимодействие с реализациями ControllerMapper, RequestController,
 * 	TemplateResolver.
 * 	- реализация интерфейса Server отвечает за установление сетевого соединения и передачу управления им ServerHandler'у
 * 	в новом потоке
 * 2) Open/Closed Principle. Все взаимодействи между классами выполнено на уровне интерфейсов. ServerHandler известно
 * что обработкой запроса должен замиматься класс реализующий интерфейс RequestController, но ничего не известно о
 * количестве таких классов. Добавление нового контроллера ни как не сказывается на работе ServerHandler.
 * 3) Liskov Substitution Principle. Взаимодействи между классами на уровне интерфейсов заставляет следовать единому
 * контракту с соблюдением пред и пост условий и обеспечивает замену текущих реализаций на новые не внося изменения в
 * зависимые классы.
 * 4) Interface Segregation Principle. Все интерфейсы наделены минимом функций:
 * 	- RequestController - только методом управления запросом (handleRequest());
 * 	- ControllerMapper - только методом размещения контроллера в хранилище (addRequestController()) и методом извлечения
 * 	контроллера из хранилища (mapControllerToRequest());
 * 	- TemplateResolver - только методом разрешения имени представления (resolveTemplatePathByName());
 * 	- Server - запуск (start()) и остановка (stop()) сервера.
 * 4) Dependency Inversion Principle. Создание экземляров классов и установки зависимостей между ними (кроме создания
 * экземпляров ServerHandler) вынесено в отдельный абстрактный класс загрузчик AbstractApplicationLoader, реализованный
 * в виде паттерна "Шаблонный метод". В задачи загрузчика входит:
 * 	- загрузка параметров и .properties;
 * 	- создание экземпляров контроллеров (RequestController) и помещение их в хранилище с отображением типу запроса
 * 	(ControllerMapper);
 * 	- создание экземпляра TemplateResolver с установкой через конструктор значений свойств prefix и suffix;
 * 	- создание экземпляра SocketHandlerManager с передачей через параметры конструктора экземпляров ControllerMapper и
 * 	TemplateResolver. Основной задачей реализации SocketHandlerManager является создание новых объектов ServerHandler во
 * 	время работы сервера;
 * 	- создание экземпляра Server и передача ему реализации SocketHandlerManager через конструктор.
 * 	- запуск сервера.
 * Все методы создания экземпляров классов выполненных в виде абстракных методов для переопределения их непосредственной
 * реализации в классах наследниках. Для тестирования работы сервера реализован класс SimpleApplicationLoader в котором
 * создаются все имеющиеся классы с установкой зависимостей между ними. В качестве развития создан класс
 * ApplicationLoaderWithScanningControllers расширяющий SimpleApplicationLoader с переопределенным методом загрузки
 * контроллеров реализующих интерфейс RequestController используя сканирование классов помеченных анотацией
 * RequestMappingController значение параметра которой является пареметром обрабатываемого запроса.
 */
public class WebServerApp {

    public static void main(String[] args) {
        new ApplicationLoaderWithScanningControllers().load(args);
    }
}
