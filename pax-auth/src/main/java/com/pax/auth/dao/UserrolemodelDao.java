package com.pax.auth.dao;

import java.util.List;
import java.util.Map;

/**
 * @author :   zhuxl@paxsz.com
 * @time :   2017/6/14
 * @description:
 */
public interface UserrolemodelDao {

    int save(Map userrolemodel);

    int deleteByUserIds(List<Integer> userIds);

    Map findByTag(String tag_store);
}
