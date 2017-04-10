package cn.featherfly.common.location.impl;

import cn.featherfly.common.location.LocationIpAddress;
import cn.featherfly.common.location.LocationMobileAddress;
import cn.featherfly.common.location.LocationPoint;
import cn.featherfly.common.location.LocationPointAddress;

/**
 * Created by zj on 2017/4/10.
 */
public class ApiTest {

    public static void main(String[] args) throws Exception {
        LocationIpAddress ipAddress = new LocationIpAdressApiTaobaoImpl().getLocationAddress("171.214.210.235");
        System.out.println(ipAddress.getAddress());
        System.out.println(ipAddress.getProvinceCode());
        System.out.println(ipAddress.getCityCode());

        LocationMobileAddress mobileAddress = new LocationMobileAddressApiBaiduImpl().getLocationAddress("13258245884");
        System.out.println(mobileAddress.getAddress());

        LocationPointAdressApiBaiduMapImpl locationPointAdressApi = new LocationPointAdressApiBaiduMapImpl();
        locationPointAdressApi.setAk("ttMOCn7aEUwhQvjcyqFkhNdc");
        LocationPointAddress locationAddress = locationPointAdressApi.getLocationAddress(new LocationPoint(30.6634200000d,104.0723290000d));
        System.out.println(locationAddress.getDistrict());
        System.out.println(locationAddress.getDistrictCode());
        System.out.println(locationAddress.getCity());
        System.out.println(locationAddress.getProvince());
        System.out.println(locationAddress.getStreet());
        System.out.println(locationAddress.getAddress());
    }
}
