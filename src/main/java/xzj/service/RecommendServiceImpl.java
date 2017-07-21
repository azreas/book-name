package xzj.service;

import org.springframework.stereotype.Service;

/**
 * @Author xzj
 * @Date 2017/7/21.
 */
@Service
public class RecommendServiceImpl implements RecommendService {
    @Override
    public String getRecommend(Long productId) {
        return "推荐词";
    }

    @Override
    public String getHotWord(Long productId) {
        return "热词";
    }
}
