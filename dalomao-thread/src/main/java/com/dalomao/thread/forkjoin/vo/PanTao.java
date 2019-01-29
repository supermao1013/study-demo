package com.dalomao.thread.forkjoin.vo;

/**
 * 蟠桃的实体类
 */
public class PanTao {

    private final Color color;
    private final Size size;
    private final int Year;

    public PanTao(Color color, Size size, int year) {
        this.color = color;
        this.size = size;
        Year = year;
    }

    public Color getColor() {
        return color;
    }

    public Size getSize() {
        return size;
    }

    public int getYear() {
        return Year;
    }

    @Override
    public String toString() {
        return "PanTao{" +
                "color=" + color +
                ", size=" + size +
                ", Year=" + Year +
                '}';
    }
}
