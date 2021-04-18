package lonchin.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lonchin.user.common.model.AuthTokenDetail;
import lonchin.user.common.utils.AssertUtil;
import lonchin.user.dal.dto.UserInfoBriefDTO;
import lonchin.user.dal.mapper.UserAccountMapper;
import lonchin.user.dal.mapper.UserInfoMapper;
import lonchin.user.dal.model.UserAccount;
import lonchin.user.dal.model.UserInfo;
import lonchin.user.jwt.JsonWebTokenUtility;
import lonchin.user.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 用户信息服务实现类
 * @author: chenlc
 * @date: 2021/4/10
 * @version: V1.0
 */
@Service
@AllArgsConstructor
@Transactional(rollbackFor = RuntimeException.class)
public class UserInfoServiceImpl implements UserInfoService {

    private final UserAccountMapper userAccountMapper;
    private final UserInfoMapper userInfoMapper;
    private final JsonWebTokenUtility tokenService;

    @Override
    public UserAccount queryById(Long userId) {
        LambdaQueryWrapper<UserAccount> lambda = Wrappers.lambdaQuery();
        lambda.eq(UserAccount::getUserId, userId);
        return userAccountMapper.selectOne(lambda);
    }

    @Override
    public AuthTokenDetail queryAuthDetail(String tokenHeader) {
        AuthTokenDetail detail = tokenService.parseAndValidate(tokenHeader);
        if (Objects.isNull(detail)) {
            throw new AccountExpiredException("token 已过期");
        }
        return detail;
    }

    @Override
    public UserInfoBriefDTO queryInfoBrief(Long userId) {
        LambdaQueryWrapper<UserInfo> userInfoQuery = Wrappers.lambdaQuery();
        userInfoQuery.eq(UserInfo::getId, userId);
        UserInfo userInfo = userInfoMapper.selectOne(userInfoQuery);
        AssertUtil.assertNull(userInfo, "The account does not exist or has been deleted. Please login again.");

        UserInfoBriefDTO response = new UserInfoBriefDTO();
        BeanUtils.copyProperties(userInfo, response);
        return response;
    }
}
