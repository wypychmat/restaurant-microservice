package com.wypychmat.basketservice.basket;

import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CacheRemovalListener<K, V> implements RemovalListener<K, V> {

    private static final Logger log = LoggerFactory.getLogger(CacheRemovalListener.class);

    @Override
    public void onRemoval(@Nullable K key, @Nullable V value, @NonNull RemovalCause cause) {
        log.info("Cache identified by id: {},  removed due to: {}", key, cause);
    }
}
