public class Example implements Сравнимое<Example> {
    private int value;

    public Example(int value) {
        this.value = value;
    }

    public int setValue(int value){
        this.value = value;
        return this.value;
    }

    public int getValue() {
        return this.value;
    }

    //метод для сравнения
    public int сравнить(Example other) {
        if (this.value > other.value) {
            return 1; //текущий больше
        } else if (this.value < other.value) {
            return -1; //текущий меньше
        } else {
            return 0;  //равны
        }
    }

    public String toString() {
        return "значение: " + value;
    }

}
