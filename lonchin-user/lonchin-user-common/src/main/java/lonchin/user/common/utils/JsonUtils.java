package lonchin.user.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * JSON工具类
 *
 * @Author chenlc
 * @Date 2019/9/22 18:09
 * @Version 1.0
 */
@Slf4j
public class JsonUtils {

    public static final ObjectMapper JSON = new ObjectMapper();
    public static final String TIME_ZONE = "GMT+8";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    static {
        JSON.findAndRegisterModules();
        //去掉默认的时间戳格式
        JSON.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);

        JSON.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
        //设置为中国上海时区
        JSON.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        JSON.configOverride(Collection.class)
                .setInclude(JsonInclude.Value.construct(JsonInclude.Include.NON_NULL, null));
        JSON.configOverride(List.class)
                .setInclude(JsonInclude.Value.construct(JsonInclude.Include.NON_NULL, null));
        JSON.configOverride(Map.class)
                .setInclude(JsonInclude.Value.construct(JsonInclude.Include.NON_NULL, null));
        //空值不序列化
        JSON.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //序列化时，日期的统一格式
        JSON.setDateFormat(new SimpleDateFormat(DATETIME_FORMAT));
        JSON.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        JSON.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JSON.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        //单引号处理
        JSON.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    private JsonUtils() {
    }

    public static String toJson(Object obj) {
        try {
            return JSON.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("{}", e.getMessage());
        }

        return null;
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return JSON.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static <T> T toCollection(String json, TypeReference<T> typeReference) {
        try {
            return JSON.readValue(json, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
