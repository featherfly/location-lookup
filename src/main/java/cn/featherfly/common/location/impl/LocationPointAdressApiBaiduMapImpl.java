package cn.featherfly.common.location.impl;

import cn.featherfly.common.location.LocationPoint;
import cn.featherfly.common.location.LocationPointAddress;
import cn.featherfly.common.location.LocationPointAddressApi;
import cn.featherfly.common.structure.HashChainMap;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * LocationIpAdressApiTaobaoImpl.
 * @author zhongj
 * @since 1.0
 * @version 1.0
 */
public class LocationPointAdressApiBaiduMapImpl extends AbstractLocationAddressHttpApi implements LocationPointAddressApi {

    private String ak;

    private String apiUrl = "http://api.map.baidu.com/geocoder/v2/";

    @Override
    public LocationPointAddress getLocationAddress(LocationPoint locationPoint) throws Exception {
        String result = request(apiUrl, new HashChainMap<String, String>()
                .putChain("ak", ak)
                .putChain("location", locationPoint.toString())
                .putChain("output", "json")
        );
        System.out.println(result);
        JsonNode jsonNode = MAPPER.readTree(result);
        if(jsonNode != null && jsonNode.get("status").asInt() == 0){
            JsonNode resultNode = jsonNode.get("result");
            JsonNode addressNode = resultNode.get("addressComponent");

            LocationPointAddress locationAddress = new LocationPointAddress();
            locationAddress.setAddress(resultNode.get("formatted_address").asText());

            locationAddress.setProvince(addressNode.get("province").asText());
            locationAddress.setCity(addressNode.get("city").asText());
            locationAddress.setDistrict(addressNode.get("district").asText());
            locationAddress.setDistrictCode(addressNode.get("adcode").asText());
            locationAddress.setStreet(addressNode.get("street").asText());

            return locationAddress;
        }
        return null;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}
