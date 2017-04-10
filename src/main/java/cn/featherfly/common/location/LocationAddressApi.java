package cn.featherfly.common.location;

/**
 * @author zhongj
 * @since 1.0
 * @version 1.0
 */
public interface LocationAddressApi<T, L extends LocationAddress> {
    /**
     * 获取目标信息对应的地址信息
     * @param target 目标信息
     * @return 地址信息
     */
    L getLocationAddress(T target) throws Exception;
}
