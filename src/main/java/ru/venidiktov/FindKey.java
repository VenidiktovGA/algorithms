package ru.venidiktov;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Найти ключ в коробке с коробками, реализация двух подходов с рекурсией и без
 */
public class FindKey {

    /**
     * Поиск ключа в коробках в коробках, без использования рекурсии.
     * Для поиска используется дополнительная память
     *
     * @param box коробка с содержимым
     * @return ключ
     */
    public static Key findKeyUseNoneRecursive(Box box) {
        List<Box> pile = new ArrayList<>();
        pile.add(box);
        Key key = null;

        while (!pile.isEmpty()) {
            Box currentBox = pile.getFirst();
            System.out.printf("------------------Начат осмотр коробки: '%s'------------------%n", currentBox.name);
            List<Item> items = currentBox.items;
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                if (Type.BOX == item.type) {
                    System.out.printf("Найдена коробка: '%s', добавляем ее в кучу%n", item.name);
                    pile.add((Box) item);
                } else if (Type.THING == item.type) {
                    System.out.printf("Найдена вещь: '%s', пропускаем ее%n", item.name);
                } else if (Type.KEY == item.type) {
                    System.out.printf("Найден ключ с именем '%s', заканчиваем поиск%n", item.name, item.name);
                    key = (Key) item;
                    pile.clear();
                    break;
                }
            }
            System.out.printf("------------------Закончен осмотр коробки: '%s'------------------%n", currentBox.name);
            pile.remove(currentBox);
        }
        return key;
    }


    /**
     * Поиск ключа в коробках в коробках, используя рекурсию.
     *
     * @param box коробка с содержимым
     * @return ключ
     */
    public static Key findKeyUseRecursive(Box box) {
        return findKeyUseRecursive(box, 0);
    }

    /**
     * Поиск ключа в коробках в коробках, используя рекурсию.
     *
     * @param box   коробка с содержимым
     * @param depth начальная глубина отступа
     * @return ключ
     */
    private static Key findKeyUseRecursive(Box box, int depth) {
        Key key = null;
        List<Item> items = box.items;
        String indent = "  ".repeat(depth);
        System.out.printf("->%s Начат осмотр коробки: '%s'%s-%n", indent, box.name, indent);

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (Type.BOX == item.type) {
                Key foundKey = findKeyUseRecursive((Box) item, depth + 1);
                if (foundKey != null) {
                    key = foundKey;
                    break;
                }
            } else if (Type.KEY == item.type) {
                System.out.printf("Найден ключ с именем '%s', заканчиваем поиск%n", item.name, item.name);
                key = (Key) item;
                break;
            }
        }

        System.out.printf("%s-> Закончен осмотр коробки: '%s'%s-%n", indent, box.name, indent);
        return key;
    }


    public enum Type {
        BOX, THING, KEY
    }

    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        public Type type;
        public String name;
    }

    @Setter
    @SuperBuilder
    @NoArgsConstructor
    public static class Box extends Item {
        public List<Item> items;
    }

    @Setter
    @SuperBuilder
    @NoArgsConstructor
    public static class Key extends Item {
    }

    @Setter
    @SuperBuilder
    @NoArgsConstructor
    public static class Thing extends Item {
    }
}
