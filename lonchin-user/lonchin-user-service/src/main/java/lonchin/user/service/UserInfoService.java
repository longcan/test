package lonchin.user.service;


import lonchin.user.common.model.AuthTokenDetail;
import lonchin.user.dal.dto.UserInfoBriefDTO;
import lonchin.user.dal.model.UserAccount;

/**
 * 用户信息服务接口类
 * @author: chenlc
 * @date: 2021/4/10
 * @version: V1.0
 */
public interface UserInfoService {


    /**
     * 功能描述: 获取用户认证信息
     * @author chenlc
     * @date 2021/4/10
     * @param userId
     * @return lonchin.user.common.model.AuthTokenDetail
     */
    UserAccount queryById(Long userId);

    /**
     * 功能描述: 获取用户token信息
     * @author chenlc
     * @date 2021/4/10
     * @param tokenHeader
     * @return lonchin.user.common.model.AuthTokenDetail
     */
    AuthTokenDetail queryAuthDetail(String tokenHeader);

    /**
     * 功能描述: 获取用户简要信息
     * @author chenlc
     * @date 2021/4/10
     * @param userId
     * @return lonchin.user.dal.dto.UserInfoBriefDTO
     */
    UserInfoBriefDTO queryInfoBrief(Long userId);
}
