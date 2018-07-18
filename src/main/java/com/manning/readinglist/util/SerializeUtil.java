package com.manning.readinglist.util;

import com.manning.readinglist.entity.Book;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by wangcheng  on 2018/7/18.
 */
public class SerializeUtil implements RedisSerializer<Object> {

    public  byte[] serialize(Object object) throws SerializationException {

        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            throw new SerializationException("Cannot serialize", e);
        }
    }

    public  Object deserialize(byte[] bytes) throws SerializationException{
        if (isEmpty(bytes)) {
            return null;
        }
        ByteArrayInputStream bais = null;
        try {
            //反序列化

            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            throw new SerializationException("Cannot deserialize", e);
        }
    }
    private boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }
}
