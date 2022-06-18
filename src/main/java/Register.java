

public class Register extends Bot {
    String wordForClass;
    int count;

    Register(String wordForClass, int count) {
        this.count = count;
        this.wordForClass = wordForClass;
        register();
    }

    public void register() {
        System.out.println("Вошел в метод");

        System.out.println(wordForClass);
        count =0;

    }
}
