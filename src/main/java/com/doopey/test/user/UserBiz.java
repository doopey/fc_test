package com.doopey.test.user;

import com.xiaomi.common.perfcounter.PerfCounter;
import com.xiaomi.data.recommend.common.model.User;
import com.xiaomi.data.recommend.user.*;
import com.xiaomi.data.spec.platform.profile.Common;
import com.xiaomi.data.spec.platform.profile.RecommendUserInfo;
import com.xiaomi.miliao.thrift.ClientFactory;
import org.apache.thrift.TException;

import java.util.List;

/**
 * Created by doopey on 16-11-16.
 */
public class UserBiz {
//    private final RecDebugXlogger LOGGER = new RecDebugXlogger(UserBiz.class);
    private final static int TIMEOUT = 800;
    private final static int STAGING_TIMEOUT = 5000;
    final static int ADD_EXPOSE_HISTORY_ATTEMPT_TIMES = 3;
    final static long BASE_UNIT = 1000 * 1000;
    final static int DEFAULT_HISTORY_LEN = 4000;

    final static int corePoolSize = 8;

    static RecommendUserInfoService.Iface recommendUserInfoService;
    static {
        int timeout = TIMEOUT;
//        if (ZKFacade.getZKSettings().getEnvironmentType() == EnvironmentType.STAGING) {
//            timeout = STAGING_TIMEOUT;
//        }
        recommendUserInfoService = ClientFactory.getClient(RecommendUserInfoService.Iface.class, timeout);
    }

    /**
     * 获取用户数据
     *
     * @param imei
     * @return
     */
    public User getUser(String imei, List<AppName> apps) {
        final User user = new User();
        user.setId(imei);
        final RecommendUserInfo userInfo = getRealTimeUserProfile(apps, GetType.BATCH, imei);
        user.setRecUserInfo(userInfo);
        return user;
    }

    /**
     * 获取用户实时的画像数据
     * @param apps
     * @param getType
     * @param uid
     * @return
     */
    public RecommendUserInfo getRealTimeUserProfile(List<AppName> apps, GetType getType, String uid) {
        GetRequest getRequest = new GetRequest();
        getRequest.setApps(apps);
        getRequest.setGetType(getType);
        getRequest.setUserId(uid);

        long startTime = System.currentTimeMillis();
        GetResponse getResponse = null;
        try {

            getResponse = recommendUserInfoService.get(getRequest);
            //LOGGER.debug("getRequest {}, getResponse:{}", getRequest, getResponse);
        } catch (TException e) {
//            LOGGER.error("get realtime user profile error: {}, {}", getRequest, ExceptionUtils.getFullStackTrace(e));
            PerfCounter.setCounterCount("get-realtime-user-profile-failure", 1);
        }
        PerfCounter.setTimerValue("get-realtime-user-profile-time", System.currentTimeMillis() - startTime);
//        LOGGER.debug("get-realtime-user-profile-time {}", System.currentTimeMillis() - startTime);

        if(null == getResponse) {
//            LOGGER.error("get realtime user profile empty: {}", getRequest);
        } else if(!getResponse.isSuccess) {
//            LOGGER.error("get realtime user profile fail: {}, message: {}", getRequest, getResponse.getMessage());
        } else if(null != getResponse.getResult()
                && getResponse.getResult().isSetCommon()){
            PerfCounter.setCounterCount("get-realtime-user-profile-success", 1);
//            LOGGER.debug("Result: {}", getResponse.getResult());
            RecommendUserInfo userInfo = getResponse.getResult(); //make sure common has uid
            userInfo.getCommon().setId(uid);
            return userInfo;
        }

        RecommendUserInfo userInfo = new RecommendUserInfo();
        Common common = new Common();
        common.setId(uid);
        userInfo.setCommon(common);
        return userInfo;
    }
}
