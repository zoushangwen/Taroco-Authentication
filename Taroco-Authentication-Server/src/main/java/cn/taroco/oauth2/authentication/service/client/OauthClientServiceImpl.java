package cn.taroco.oauth2.authentication.service.client;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.taroco.oauth2.authentication.entity.OauthClient;
import cn.taroco.oauth2.authentication.exception.BusiException;
import cn.taroco.oauth2.authentication.mapper.OauthClientMapper;
import cn.taroco.oauth2.authentication.vo.OauthClientVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 客户端 Service
 *
 * @author liuht
 * 2019/7/5 14:56
 */
@SuppressWarnings("unchecked")
@Service
public class OauthClientServiceImpl extends ServiceImpl<OauthClientMapper, OauthClient> implements OauthClientService {

    @Override
    public OauthClientVo getVo(final String clientId) {
        final OauthClient client = this.getById(clientId);
        return new OauthClientVo(client);
    }

    @Override
    public Boolean update(final OauthClientVo vo) {
        if (StrUtil.isEmpty(vo.getClientId())) {
            throw new BusiException("不存在客户端ID");
        }
        return this.updateById(new OauthClient(vo));
    }

    @Override
    public Boolean add(final OauthClientVo vo) {
        if (StrUtil.isNotEmpty(vo.getClientId())) {
            throw new BusiException("存在客户端ID,无法新增");
        }
        vo.setClientId(IdUtil.objectId());
        return this.save(new OauthClient(vo));
    }

    @Override
    public Boolean delete(final String clientId) {
        if (this.getById(clientId) == null) {
            throw new BusiException("不存在的客户端ID: " + clientId);
        }
        return this.removeById(clientId);
    }

    @Override
    public IPage<OauthClientVo> selectPageVo(final Page page) {
        final IPage<OauthClient> iPage = this.page(page);
        return iPage.convert(OauthClientVo::new);
    }
}
