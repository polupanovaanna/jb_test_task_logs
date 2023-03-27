package jb_test;

public class StringWrapper implements CharSequence {
    CharSequence data;

    public StringWrapper(CharSequence data) {
        super();
        this.data = data;
    }

    @Override
    public char charAt(int index) {
        if (Thread.currentThread().isInterrupted()) {
            throw new RuntimeException("Error: pattern matching was interrupted");
        }
        return data.charAt(index);
    }

    @Override
    public int length() {
        return data.length();
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new StringWrapper(data.subSequence(start, end));
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
