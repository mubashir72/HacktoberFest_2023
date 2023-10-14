class ListItem {
    int value;
    ListItem nextItem;

    public ListItem(int value) {
        this.value = value;
        this.nextItem = null;
    }
}

public class CustomLinkedList {
    ListItem headItem;

    public CustomLinkedList() {
        headItem = null;
    }

    // Add element to the tail
    public void addToTail(int value) {
        ListItem newItem = new ListItem(value);
        if (headItem == null) {
            headItem = newItem;
            return;
        }

        ListItem temp = headItem;
        while (temp.nextItem != null) {
            temp = temp.nextItem;
        }
        temp.nextItem = newItem;
    }

    // Add element to the head
    public void addToHead(int value) {
        ListItem newItem = new ListItem(value);
        newItem.nextItem = headItem;
        headItem = newItem;
    }

    // Remove an element with the specified value
    public void removeValue(int value) {
        if (headItem == null) return;

        if (headItem.value == value) {
            headItem = headItem.nextItem;
            return;
        }

        ListItem temp = headItem;
        while (temp.nextItem != null && temp.nextItem.value != value) {
            temp = temp.nextItem;
        }

        if (temp.nextItem != null) {
            temp.nextItem = temp.nextItem.nextItem;
        }
    }

    // Display the list items
    public void showList() {
        ListItem temp = headItem;
        while (temp != null) {
            System.out.print(temp.value + " -> ");
            temp = temp.nextItem;
        }
        System.out.println("End");
    }

    public static void main(String[] args) {
        CustomLinkedList myList = new CustomLinkedList();
        myList.addToTail(5);
        myList.addToTail(10);
        myList.addToTail(15);
        myList.addToHead(2);

        System.out.println("Current List:");
        myList.showList();

        myList.removeValue(10);
        System.out.println("After removing 10:");
        myList.showList();
    }
}
