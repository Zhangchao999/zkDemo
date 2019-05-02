package cn.geoary;

/**
 * @program: zkdemo
 * @description: 常量定义
 * @author: geoary
 * @create: 2019-05-01 20:58
 **/
public class ZKConstant {
    /**
      *  zookeeper集群地址
      */
    public static final String ZK_CONNECT_URL_ADDRESS = "192.168.80.11:2181,192.168.80.11:2182,192.168.80.11:2183,192.168.80.11:2184,192.168.80.11:2185";

    /**
     *  zookeeper session 时间(ms)
     */
    public static final int ZK_SESSION_TIMEOUT=5000;
}

