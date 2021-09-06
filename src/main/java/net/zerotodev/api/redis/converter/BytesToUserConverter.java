package net.zerotodev.api.redis.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.zerotodev.api.redis.domain.User;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Component
@ReadingConverter
public class BytesToUserConverter implements Converter<byte[], User> {

    private final Jackson2JsonRedisSerializer<User> serializer;

    public BytesToUserConverter() {
        this.serializer = new Jackson2JsonRedisSerializer<User>(User.class);
        serializer.setObjectMapper(new ObjectMapper());
    }

    @Override
    public User convert(byte[] source) {
        return serializer.deserialize(source);
    }
}
