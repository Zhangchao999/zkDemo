package cn.geoary.zookeeper;

import cn.geoary.ZKConstant;
import org.apache.log4j.Logger;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: zkdemo
 * @description: 测试
 * @author: geoary
 * @create: 2019-05-01 21:02
 **/
public class ZKTest {
    Logger logger = Logger.getLogger(ZKTest.class);
    ZooKeeper zkClient;

    /**
     * @description 创建连接
     * @param
     * @return
     * @author geoary
     * @date 2019/5/1
     */
    @Before
    public void init() throws Exception {
        zkClient = new ZooKeeper(ZKConstant.ZK_CONNECT_URL_ADDRESS, ZKConstant.ZK_SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        });
        logger.info(zkClient);
    }

    /**
     * @description 创建节点
     * @param
     * @return
     * @author Zhangc
     * @date 2019/5/1
     */
    @Test
    public void createNode() throws Exception{
        String nodePath = "/geoary";
        String data = "我是添加的数据";
        ArrayList<ACL> acl = ZooDefs.Ids.OPEN_ACL_UNSAFE;
        CreateMode mode = CreateMode.PERSISTENT;
        String newPath = zkClient.create(nodePath, data.getBytes(), acl, mode);
        logger.info(newPath);
    }

    /**
     * @description 获取节点数据 
     * @param
     * @return  
     * @author Zhangc
     * @date 2019/5/1 
     */
    @Test
    public void getNode() throws Exception{
        String nodePath = "/geoary";
        byte[] nodeDataByte = zkClient.getData(nodePath, false, null);
        String nodeData = new String(nodeDataByte);
        logger.info(nodeData);
    }

    /**
     * @description 修改节点数据
     * @param
     * @return
     * @author Zhangc
     * @date 2019/5/1
     */
    @Test
    public void setNode() throws Exception{
        String nodePath = "/geoary";
        String newDate = "我是修改后的数据";
        byte[] beforeNodeDataByte = zkClient.getData(nodePath, false, null);
        logger.info("修改前的数据:"+new String(beforeNodeDataByte));
        // -1 忽略版本
        Stat stat = zkClient.setData(nodePath, newDate.getBytes(), -1);
        logger.info(stat);
        byte[] aftereNodeDataByte = zkClient.getData(nodePath, false, null);
        logger.info("修改后的数据:"+new String(aftereNodeDataByte));
    }

    /**
     * @description 删除节点
     * @param
     * @return
     * @author Zhangc
     * @date 2019/5/1
     */
    @Test
    public void deleteNode() throws Exception{
        String nodePath = "/geoary";
        zkClient.delete(nodePath, -1);
        List<String> children = zkClient.getChildren(nodePath, null);
        logger.info(children);
    }
}

