package org.bl.singleton;

/**
 * @author beckl
 */
public enum SthSingleton {
    INSTANCE;
    public void doSth() {
        throw new RuntimeException("nothing implemented");
    }
}
