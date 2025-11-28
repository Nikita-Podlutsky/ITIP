package com.example;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.atomic.AtomicInteger;

public class WarehouseLoader {
    // Лимит веса для одной отправки
    private static final int MAX_WEIGHT = 150;
    // Текущий вес набранных товаров
    private static final AtomicInteger currentWeight = new AtomicInteger(0);
    // Флаг, сообщающий грузчикам, что пора остановиться и ждать разгрузки
    private static volatile boolean isTruckFull = false;

    public static void main(String[] args) {
        // 1. Создаем склад с товарами (вес от 10 до 60 кг)
        Queue<Integer> warehouse = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 20; i++) {
            warehouse.add((int) (Math.random() * 50) + 10);
        }
        System.out.println("Товары на складе: " + warehouse);

        // 2. Создаем CyclicBarrier на 3 потока (грузчика)
        // Второе действие (Runnable) выполняется, когда все 3 потока дойдут до барьера
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println(">> ГРУЗОВИК ЗАПОЛНЕН (" + currentWeight.get() + " кг). ОТПРАВЛЯЕМСЯ НА ДРУГОЙ СКЛАД...");
            try {
                Thread.sleep(1000); // Имитация времени поездки
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(">> Груз доставлен. Возвращаемся пустыми.\n");
            currentWeight.set(0); // Обнуляем вес
            isTruckFull = false;  // Снимаем флаг переполнения
        });

        // 3. Запускаем 3 грузчиков
        for (int i = 1; i <= 3; i++) {
            new Thread(new Loader(i, warehouse, barrier)).start();
        }
    }

    // Класс Грузчика
    static class Loader implements Runnable {
        private final int id;
        private final Queue<Integer> warehouse;
        private final CyclicBarrier barrier;

        public Loader(int id, Queue<Integer> warehouse, CyclicBarrier barrier) {
            this.id = id;
            this.warehouse = warehouse;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // Если объявлено, что грузовик полон — идем к барьеру (ждем отправки)
                    if (isTruckFull) {
                        barrier.await();
                        continue; // После барьера проверяем товары снова
                    }

                    // Берем товар
                    Integer itemWeight = warehouse.poll();
                    if (itemWeight == null) {
                        // Товары кончились — выходим. 
                        // В реальном приложении нужно аккуратно завершить работу других потоков,
                        // чтобы они не ждали на барьере вечно, но для лабы достаточно break.
                        System.out.println("Грузчик " + id + " закончил работу (склад пуст).");
                        break;
                    }

                    // Проверяем, влезет ли товар
                    // Используем synchronized блок, чтобы проверка и добавление были атомарны
                    boolean added = false;
                    synchronized (currentWeight) {
                        if (currentWeight.get() + itemWeight <= MAX_WEIGHT) {
                            currentWeight.addAndGet(itemWeight);
                            System.out.println("Грузчик " + id + " погрузил товар " + itemWeight + " кг. Всего: " + currentWeight.get());
                            added = true;
                        } else {
                            // Если не влезает — объявляем, что грузовик полон
                            isTruckFull = true;
                            System.out.println("Грузчик " + id + " обнаружил перевес! (Товар " + itemWeight + " не влезает). Ждем остальных...");
                        }
                    }

                    if (!added) {
                        // Если товар не влез, возвращаем его обратно в начало очереди (или держим в руках)
                        // В данном примере вернем в очередь, чтобы попытаться положить в следующий раз
                        ((ConcurrentLinkedQueue<Integer>)warehouse).add(itemWeight); // упрощенно добавляем в конец
                        // И идем к барьеру ждать разгрузки
                        barrier.await();
                    } else {
                        // Имитация работы
                        Thread.sleep(300);
                    }
                }
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}