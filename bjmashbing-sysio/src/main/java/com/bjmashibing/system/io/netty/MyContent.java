package com.bjmashibing.system.io.netty;

import java.io.Serializable;

/**
 * description: MyContent date: 2021/5/8 6:38 下午
 *
 * @author: guizhenyu
 */
public class MyContent implements Serializable {

  String name;
  String methodName;
  public Class<?>[] parameterTypes;
  Object[] args;
  String res;

  public String getRes() {
    return res;
  }

  public void setRes(String res) {
    this.res = res;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public Class<?>[] getParameterTypes() {
    return parameterTypes;
  }

  public void setParameterTypes(Class<?>[] parameterTypes) {
    this.parameterTypes = parameterTypes;
  }

  public Object[] getArgs() {
    return args;
  }

  public void setArgs(Object[] args) {
    this.args = args;
  }
}
