package by.bseu.pp.lab05;

public class Application {
    public static void main(String[] args) {
        LoggingSetup.configure();
        OrderService service = new OrderService();
        runScenario("Создать O01 на 1200", () -> service.createOrder("O01", 1200));
        runScenario("Создать O02 на 500", () -> service.createOrder("O02", 500));
        runScenario("Создать заказ с пустым id", () -> service.createOrder(" ", 100));
        runScenario("Создать O03 с отрицательной суммой", () -> service.createOrder("O03", -100));
        runScenario("Повторно создать O01", () -> service.createOrder("O01", 1200));
        runScenario("Оплатить O02 суммой 400", () -> service.payOrder("O02", 400));
        runScenario("Оплатить O01 суммой 1200", () -> service.payOrder("O01", 1200));
        runScenario("Повторно оплатить O01", () -> service.payOrder("O01", 1200));
        runScenario("Отменить оплаченный O01", () -> service.cancelOrder("O01"));
        runScenario("Отменить O02", () -> service.cancelOrder("O02"));
        runScenario("Оплатить отменённый O02", () -> service.payOrder("O02", 500));
        runScenario("Найти отсутствующий O99", () -> service.findOrder("O99"));
        runScenario("Найти O01", () -> service.findOrder("O01"));
        runScenario("Найти O02", () -> service.findOrder("O02"));

        System.out.println("Итог O01: " + service.findOrder("O01").getStatus());
        System.out.println("Итог O02: " + service.findOrder("O02").getStatus());
        System.out.println("Количество заказов: " + service.getOrderCount());
    }

    /** Обрабатывает ожидаемые ошибки одной операции; следующие сценарии продолжаются. */
    public static void runScenario(String title, Runnable action) {
        System.out.println("Сценарий: " + title);
        // TODO 7: выполните action.run() внутри try; обработайте два типа исключений.
        // Успех: "Операция выполнена". Ошибки: префикс из методички + ex.getMessage().
        try {
            action.run();

            System.out.println("Операция выполнена");

        } catch (ValidationException ex) {
            System.out.println("Ошибка данных: " + ex.getMessage());

        } catch (OrderOperationException ex) {
            System.out.println("Операция отклонена: " + ex.getMessage());
        }
    }
}
