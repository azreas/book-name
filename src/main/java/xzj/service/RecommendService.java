package xzj.service;

/**
 * @Author xzj
 * @Date 2017/7/21.
 */
public interface RecommendService {
    String getRecommend(Long productId);

    String getHotWord(Long productId);
}
