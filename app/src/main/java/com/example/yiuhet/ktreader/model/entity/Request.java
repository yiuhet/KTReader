package com.example.yiuhet.ktreader.model.entity;

public class Request<T> {
    private T data;

    @Override
    public String toString() {
        return "Request{" +
                "data=" + data +
                '}';
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
