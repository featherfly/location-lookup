package cn.featherfly.common.location.impl;

import cn.featherfly.common.location.*;
import cn.featherfly.common.structure.HashChainMap;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * LocationMobileAddressApiBaiduImpl.
 * @author zhongj
 * @since 1.0
 * @version 1.0
 */
public class LocationMobileAddressApiBaiduImpl extends AbstractLocationAddressHttpApi implements LocationMobileAddressApi {
    @Override
    public LocationMobileAddress getLocationAddress(String target) throws Exception {
        String result = request("http://opendata.baidu.com/api.php", new HashChainMap<String, String>()
                .putChain("resource_name", "guishudi")
                .putChain("oe", "utf-8")
                .putChain("query", target)
        );
        System.out.println(result);

        JsonNode jsonNode = MAPPER.readTree(result);
        if(jsonNode != null && jsonNode.get("status").asInt() == 0){
            JsonNode node = jsonNode.get("data").get(0);
            LocationMobileAddress locationMobileAddress = new LocationMobileAddress();
            locationMobileAddress.setOperator(node.get("type").asText());
            locationMobileAddress.setCity(node.get("city").asText());
            locationMobileAddress.setProvince(node.get("prov").asText());
            locationMobileAddress.setAddress(locationMobileAddress.getProvince() + locationMobileAddress.getCity() + " " + locationMobileAddress.getOperator());
            return locationMobileAddress;
        }
        return null;
    }
}
