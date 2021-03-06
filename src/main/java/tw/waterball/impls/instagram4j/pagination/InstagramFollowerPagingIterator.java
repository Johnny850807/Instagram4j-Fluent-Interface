/*
 * Copyright 2019 johnny850807
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tw.waterball.impls.instagram4j.pagination;

import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowersRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import tw.waterball.api.InstagramUser;
import tw.waterball.api.pagination.Page;
import tw.waterball.api.pagination.PagingIterator;
import tw.waterball.exceptions.InstagramException;
import tw.waterball.impls.instagram4j.AdapterWrapping;
import tw.waterball.impls.instagram4j.Instagram4JAdapter;

import java.util.Iterator;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class InstagramFollowerPagingIterator extends PagingIterator<InstagramUser> {
    private Instagram4JAdapter ig;
    private long userPk;
    private InstagramGetUserFollowersResult result;
    private String nextMaxId;

    public InstagramFollowerPagingIterator(Instagram4JAdapter ig, long userPk) {
        this.ig = ig;
        this.userPk = userPk;
    }

    public InstagramFollowerPagingIterator(int limit, Instagram4JAdapter ig, long userPk) {
        super(limit);
        this.ig = ig;
        this.userPk = userPk;
    }

    @Override
    protected boolean hasNextPage() {
        return result == null /*first fetch*/ || nextMaxId != null;
    }

    @Override
    protected Page<InstagramUser> getNextPage() {
        this.result = ig.sendRequest(new InstagramGetUserFollowersRequest(userPk, nextMaxId));
        if (result.getUsers() == null)
            throw new InstagramException("Get user followers");
        nextMaxId = result.getNext_max_id();
        return new Page<>(AdapterWrapping.wrap4JUserSummaries(ig, result.getUsers()));
    }
}
