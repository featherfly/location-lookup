package cn.featherfly.common.location.impl;

import cn.featherfly.common.location.LocationIpAddress;
import cn.featherfly.common.location.LocationIpAddressApi;
import cn.featherfly.common.structure.HashChainMap;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * LocationIpAdressApiTaobaoImpl.
 * @author zhongj
 * @since 1.0
 * @version 1.0
 */
public class LocationIpAdressApiTaobaoImpl extends AbstractLocationAddressHttpApi implements LocationIpAddressApi {

    @Override
    public LocationIpAddress getLocationAddress(String target) throws Exception {
        String result = request("http://ip.taobao.com/service/getIpInfo.php", new HashChainMap<String, String>()
                .putChain("ip", target)
        );
        JsonNode jsonNode = MAPPER.readTree(result);
        if(jsonNode != null && jsonNode.get("code").asInt() == 0){
            JsonNode node = jsonNode.get("data");
            LocationIpAddress locationIpAddress = new LocationIpAddress();
            locationIpAddress.setOperator(node.get("isp").asText());
            locationIpAddress.setProvince(node.get("region").asText());
            locationIpAddress.setCity(node.get("city").asText());
            locationIpAddress.setAddress(locationIpAddress.getProvince() + locationIpAddress.getCity() + " " + locationIpAddress.getOperator());
            return locationIpAddress;
        }
        return null;
    }


}
