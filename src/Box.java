public class Box <T>{

    private T item; //хранимый объект

    public Box() {
        this.item = null;
    }

    //добавление объекта в коробку
    public void putItem(T item) {
        if (item == null) {
            throw new IllegalStateException("ошибка, введите ненулевое значение");
        }
        if (this.item != null) {
            throw new IllegalStateException("коробка уже заполнена");
        }
        this.item = item;
    }

    //извлечение объекта из коробки
    public T getItem() {
        T temp = this.item;
        if (this.item == null) {
            throw new IllegalStateException("коробка пуста");
        }
        this.item = null; //обнуление ссылки после извлечения
        return temp;
    }

    //проверка на заполненность
    public boolean isFull() {
        return this.item != null;
    }

    public String toString() {
        return isFull() ? "коробка заполнена: " + item : "коробка пуста";
    }
}
