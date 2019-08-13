package com.bokecc.cloud.wowza.utils;

import com.bokecc.cloud.wowza.enums.ResponseEnum;
import com.bokecc.cloud.wowza.result.ServiceResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 非法参数校验工具类
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 * @author Daniel Zhou / zzx
 *
 **/
public class ParamsCheckUtils {

    private static final Logger logger = LoggerFactory.getLogger(ParamsCheckUtils.class);

    /**
     * 参数非空检验
     * @param paramsMap     Request params with map
     * @param excludeParams Abandon the parameter name of the check
     * @return response
     */
    public static ServiceResponse isParamsEmpty(Map<String, Object> paramsMap, Object... excludeParams) {
        ServiceResponse response = new ServiceResponse();
        paramsMap.forEach((k, v) -> {
            if (Arrays.asList(excludeParams) == null
                || Arrays.asList(excludeParams).stream().filter(e -> e.equals(k)).collect(Collectors.toList()).size() == 0) {
                if (StringUtils.isEmpty(String.valueOf(v).trim())) {
                    String info = "Request param  " + k + " cannot be null";
                    logger.error(info);
                    response.setCode(ResponseEnum.INVALID_PARAM.getCode());
                    response.setMessage(info);
                }
            }
        });
        return response;
    }
}
