package View;

import java.util.List;

import Model.BasePet;

public class ListView {
    public void printList(List<? extends BasePet> list) {
        System.out.println("----------Список всех животных----------");
        for (BasePet item : list) {
            System.out.println(item);
        }
    }

    public void printCommands(List<String> list) {
        if ((list == null) || list.isEmpty()) {
            System.out.println("----------Животное не знает ни одной команды----------");
        } else {
            System.out.println("----------Животное знает команды:----------");
            for (String item : list) {
                System.out.println(item);
            }
        }
    }
}
