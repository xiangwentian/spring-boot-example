package com.workman.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class ZkClientTest {

    public static void main(String[] args) {
        ZkClientTest zk = new ZkClientTest();
        try {
            zk.zkTest();
        } catch (InterruptedException e) {
            log.error("InterruptedException:", e);
        } catch (KeeperException e) {
            log.error("KeeperException:", e);
        } catch (IOException e) {
            log.error("IOException:", e);
        }
    }

    public void zkTest() throws InterruptedException, KeeperException, IOException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zk = new ZooKeeper("192.168.118.130:2181", 3000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    countDownLatch.countDown();
                }
                log.info("Watch=>{}" + event.getType());
            }
        });
        countDownLatch.await();
        log.info("zk state:{}", zk.getState());
        String node = "/app1";
        Stat state = zk.exists(node, false);
        if (state == null) {
            log.info("创建节点");
            String createResult = zk.create(node, "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            log.info("createResult:{}", createResult);
        }

        byte[] b = zk.getData(node, false, state);
        log.info("获取data值：{}", new String(b));

        state = zk.setData(node, "1".getBytes(), state.getVersion());
        log.info("after update,version changed to：{}", state.getVersion());
        zk.delete(node, state.getVersion());

        zk.close();

    }
}
