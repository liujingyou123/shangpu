package com.finance.winport.home.model;

import com.finance.winport.base.BaseResponse;

/**
 * Created by liuworkmac on 17/5/26.
 */

public class AliTokenResponse extends BaseResponse{

    /**
     * accessKeyId : STS.JxFi7jmtohrhUJFwKtitayHUu
     * accessKeySecret : DkLvRVHtpLakyGuo4zBZGY6WHx5H86JzL3Z9i7s4XsVL
     * expiration : 2017-05-26 17:07:10
     * securityToken : CAISswJ1q6Ft5B2yfSjIq7vzIo3egKtO37CDV2z3k0shZftNlo3+lzz2IH5LfHJsBOEbsfw0m29Q7/gYlrNsU9pMXVSBZtZr8pNS/BnkaozNocu04flf0sURlIlbp12psvXJasDVEfnIGJ70GUCm+wZ3xbzlD2vAO3WuLZyOj7N+c90TRXPWRDFaBdBQVH0AzcgBLinpKOqKOBzniXayaU1zoVhThH9Y46ayydHmtXi4tlDhzfIPrIncO4Wta9IWXK1ySNCoxud7BNmjoSdb8EpN77wkzv4Gqyjf+9aGGBxf4hGLdKj2ioQwclMoOfNrQvQZ9Kmhy6RC17aNx9it+XFkJvpIVinTfoekzfbfFfmhXtRDLuikYy+Rj4HSacGu7Fx8OCxBLmxVY587LXt9GY3c/cFy5VbMGoABImuzDxmlOCDSNTPhUVVoS2hWQ+FEqE3DS2D2o1d1de4wjhaj/YF6KoPP/7YR/fn/D1G42fwqA5rb5ha9oQiopSb4/qCOw+TSd0dlvmL5L+XvIek9ghWQqE1P5+LeTrdfchQ+Hul9Dc8AaOte9R+qnnwq04uxLEl01imozne0L1E=
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String accessKeyId;
        private String accessKeySecret;
        private String expiration;
        private String securityToken;

        public String getAccessKeyId() {
            return accessKeyId;
        }

        public void setAccessKeyId(String accessKeyId) {
            this.accessKeyId = accessKeyId;
        }

        public String getAccessKeySecret() {
            return accessKeySecret;
        }

        public void setAccessKeySecret(String accessKeySecret) {
            this.accessKeySecret = accessKeySecret;
        }

        public String getExpiration() {
            return expiration;
        }

        public void setExpiration(String expiration) {
            this.expiration = expiration;
        }

        public String getSecurityToken() {
            return securityToken;
        }

        public void setSecurityToken(String securityToken) {
            this.securityToken = securityToken;
        }
    }
}
