package com.desk.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author CP_lixiaolong
 * @date 2020/12/2 14:07
 */
public class DifferenceUtil<T> {

    public List<T> different(List<T> list1, List<T> list2, Class clazz, String[] unless){
        List<T> returnList = new ArrayList<>();
        List<T> first = list1.stream().collect(Collectors.toList());
        List<T> second = list2.stream().collect(Collectors.toList());
        Field[] declaredFields = clazz.getDeclaredFields();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        List<Method> methods = Arrays.asList(declaredMethods);
        Map<String, List<Method>> methodMap = methods.stream().filter(method -> {
            return method.getName().startsWith("get") && method.getParameterCount() == 0;
        }).collect(Collectors.groupingBy(Method::getName));
        List<Field> fields = Arrays.asList(declaredFields);
        List<String> collect = fields.stream().map(Field::getName).collect(Collectors.toList());
        collect.removeAll(Arrays.asList(unless));
        for (int i = 0; i < collect.size(); i++) {
            String field = collect.get(i);
            field = "get"+CharUtil.captureName(field);
            Method method = methodMap.get(field).get(0);
            Iterator<T> iterator1 = first.iterator();
            while(iterator1.hasNext()){
                T next1 = iterator1.next();
                Object o1 = this.goInvoke(method, next1, null);
                String obj1 = Optional.ofNullable(o1).isPresent()?o1.toString():"";
                boolean state = false;
                Iterator<T> iterator2 = second.iterator();
                while(iterator2.hasNext()){
                    T next2 = iterator2.next();
                    Object o2 = this.goInvoke(method, next2, null);
                    String obj2 = Optional.ofNullable(o2).isPresent()?o2.toString():"";
                    if(obj1 != null && obj2 != null && obj1.equals(obj2)){
                        state = true;
                    }
                }
                if(!state){
                    returnList.add(next1);
                    iterator1.remove();
                }
            }
        }
        return returnList;
    }

    private Object goInvoke(Method m, T t, Object... args){
        Object invoke = null;
        try {
            invoke = m.invoke(t, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return invoke;
    }


}
