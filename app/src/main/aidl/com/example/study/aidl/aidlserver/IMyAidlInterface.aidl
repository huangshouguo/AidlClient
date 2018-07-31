// IMyAidlInterface.aidl
package com.example.study.aidl.aidlserver;

// Declare any non-default types here with import statements
import com.example.study.aidl.demo.User;

interface IMyAidlInterface {
    String getName();
    User getUser();
}
