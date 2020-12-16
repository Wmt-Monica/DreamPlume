package homework.strStack;

public interface Queue {
    public boolean isEmpty();
    public boolean isFull();
    public int getSize();
    public void putSize(int maxSize);
    public void add(int value);
    public void delete();
    public int getValue(int num);
    public void showAll();
}
