package edu.csus.yaam.client.api.event;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Ryan R
 * @date 4/9/2018
 */
@RequiredArgsConstructor
@Accessors(fluent = true)
public class BindableProxy<T> {
    @NonNull
    private final Class<? extends T> type;

    private final Map<String, List<?>> callbacks = new HashMap<>();


    private Object invoke(Object meta, Object proxy, Method method, Object[] args) {
        return null;
    }


    public T proxy(Object meta) {
        return (T) Proxy.newProxyInstance(BindableProxy.class.getClassLoader(), new Class[] {type}, (proxy, method, args) -> invoke(meta, proxy, method, args));
    }


    public void bind(String methodName, Consumer consumer) {
        throw new UnsupportedOperationException();
    }
}