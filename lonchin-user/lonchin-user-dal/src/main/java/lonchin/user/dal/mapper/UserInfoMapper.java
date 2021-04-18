package lonchin.user.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lonchin.user.dal.model.UserAccount;
import lonchin.user.dal.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO
 *
 * @author: chenlc
 * @date: 2021/4/13
 * @version: V1.0
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
