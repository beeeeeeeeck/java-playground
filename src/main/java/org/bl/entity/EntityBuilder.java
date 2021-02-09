package org.bl.entity;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author beckl
 */
public class EntityBuilder<T> {
    private final Field[] fieldArray;
    private final Class<T> className;
    private final T entityObj;

    public EntityBuilder(Class<T> className) throws Exception {
        this.fieldArray = className.getDeclaredFields();
        this.className = className;
        Constructor<T> constructor = className.getDeclaredConstructor();
        constructor.setAccessible(true);
        this.entityObj = constructor.newInstance();
    }

    public EntityBuilder<T> setValue(String paramName, Object paramValue) throws Exception {
        for (Field field : fieldArray) {
            if (field.getName().equals(paramName)) {
                PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), className);
                Method method = descriptor.getWriteMethod();
                method.invoke(entityObj, paramValue);
            }
        }
        return this;
    }

    public T build() {
        return entityObj;
    }
}
