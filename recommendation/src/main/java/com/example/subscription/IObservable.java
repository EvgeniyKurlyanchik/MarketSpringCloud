package com.example.subscription;

public interface IObservable {
    void addObserver(IObserver o);
    void  removeObserver(IObserver o);
    void updateNotify();

}
